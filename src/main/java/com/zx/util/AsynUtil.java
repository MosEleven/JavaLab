package com.zx.util;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * AsynUtil简介
 *
 * @author zengxin13
 * @date 2021-03-17 20:37
 */
@Slf4j
public class AsynUtil {

    private static final ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

    private AsynUtil(){}

    public static Work newWork(int workNum) {
        return new Work(workNum);
    }

    public static Work newWork() {
        return new Work();
    }

    public static void submit(Work work) {

        work.initSubmit();
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
        ListenableFuture<List<Object>> resultsFuture = Futures.allAsList(work.resultList);

        try {
            resultsFuture.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            work.onFailure();
            log.error("线程池执行的任务出现错误");
            throw e.getCause();
        } finally {
            System.out.println("Executor pool work is done");
        }
        work.onSuccess();
    }


    public static class Work {

        private final List<Runnable> tasks;
        private boolean submitted = false;
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

        public final void submit(){
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

        protected void onSuccess() {
            // for extension
        }

        protected void onFailure() {
            // for extension
        }

    }

}

