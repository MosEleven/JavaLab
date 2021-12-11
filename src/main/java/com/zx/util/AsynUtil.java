package com.zx.util;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AsynUtil简介
 *
 * @author zengxin13
 * @date 2021-03-17 20:37
 */
@Slf4j
public class AsynUtil {

    //todo 线程池的具体实现要按具体情况分析，可以采用配置的方式来适应不同的项目
    private static final ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

    private AsynUtil(){}

    public static Work newWorks(int workNum) {
        return new Work(workNum);
    }

    public static Work newWorks() {
        return new Work();
    }

    public static void submit(Runnable r){

    }

    private static void submit(Work work) {

        work.initSubmit();
        work.whenStart();
        for (Runnable task : work.tasks) {
            work.addResult(pool.submit(() -> {

                work.beforeExecute();
                task.run();
                work.afterExecute();
            }));
        }
        monitorExecutor(work);
    }

    @SneakyThrows
    private static void monitorExecutor(Work work) {
        ListenableFuture<List<Object>> resultsFuture = getTarget(work);

        try {
            resultsFuture.get();
            work.whenSuccess();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            work.whenFailure();
            log.error("线程池执行的任务出现错误");
            throw e.getCause();
        } finally {
            System.out.println("Executor pool work is done");
            work.whenEnd();
        }
    }

    private static ListenableFuture<List<Object>> getTarget(Work work){
        List<ListenableFuture<?>> futures = work.resultList;
        if (work.allSucceed){
            return Futures.allAsList(futures);
        }
        return new AbstractFuture<List<Object>>() {

            final AtomicInteger failureCount = new AtomicInteger(futures.size());
            final AtomicInteger successCount = new AtomicInteger(0);

            @Override
            public List<Object> get() throws InterruptedException, ExecutionException {
                for (ListenableFuture<?> future : futures) {
                    future.addListener(() -> {
                        try {
                            future.get();
                        }catch (Exception e){
                            if (failureCount.decrementAndGet()==0){
                                this.setException(new RuntimeException("all tasks is failed when at least one needs to succeed"));
                            }
                            return;
                        }
                        onSuccess();
                    },MoreExecutors.directExecutor());
                }
                return super.get();
            }

            private void onSuccess(){
                if (successCount.incrementAndGet()>1){
                    return;
                }
                for (ListenableFuture<?> future : futures) {
                    future.cancel(true);
                }
                System.out.println(Thread.currentThread().getName()+":success!!!!");
                this.set(Lists.newArrayList());
            }

        };
    }


    public static class Work {

        private final List<Runnable> tasks;
        private boolean submitted = false;
        private boolean allSucceed;
        private List<ListenableFuture<?>> resultList;

        public Work(int workNum) {
            tasks = new ArrayList<>(workNum);
        }

        public Work() {
            tasks = Lists.newArrayList();
        }

        public final Work addTask(Runnable r) {
            tasks.add(r);
            return this;
        }

        public final void succeedAll(){
            allSucceed = true;
            submit();
        }

        public final void succeedAny(){
            allSucceed = false;
            submit();
        }

        private void submit(){
            if (submitted || !canSubmit()){
                throw new IllegalStateException("不可提交");
            }
            if (tasks.isEmpty()){
                return;
            }
            AsynUtil.submit(this);
        }

        protected boolean canSubmit(){
            return true;
        }

        private void initSubmit(){
            submitted = true;
            resultList = new ArrayList<>(tasks.size());
        }

        private void addResult(ListenableFuture<?> future){
            resultList.add(future);
        }

        protected void beforeExecute() {
            // for extension
        }

        protected void afterExecute() {
            // for extension
        }

        protected void whenSuccess() {
            // for extension
        }

        protected void whenFailure() {
            // for extension
        }

        protected void whenStart() {
            // for extension
        }

        protected void whenEnd() {
            // for extension
        }

    }

}

