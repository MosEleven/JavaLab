package com.zx.util;

import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.zx.testClass.Fruit;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

class AsynUtilTest {

    private int testNum = 0;

    @Test
    @DisplayName("staticWay")
    void test1(){
        AsynUtil.newWork()
                .addTask(this::longTask)
                .addTask(this::longTask)
                .addTask(this::longTask)
                .addTask(this::longTask)
                .succeedAll();

        AsynUtil.Work work = AsynUtil.newWork()
                .addTask(this::longTask)
                .addTask(this::longTask);

                work.addTask(this::longTask)
                .addTask(this::longTask)
                .succeedAll();
    }

    @Test
    @DisplayName("customWay")
    void test2(){
        AsynUtil.Work w1 = new AsynUtil.Work() {
            @Override
            protected void beforeExecute() {
                System.out.println("sdss");
            }
        };
        w1.addTask(this::longTask)
                .addTask(this::longTask)
                .succeedAll();
    }

    private void longTask(){
        int n = ++testNum;
        System.out.println("this is task "+n);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.printf("test %s is interrupted%n",n);
        }
    }

    @SneakyThrows
    private void wrongTask() {
        Thread.sleep(1000);
        throw new RuntimeException("666");
    }

    @Test
    @DisplayName("")
    void finallyTest(){
        try {
            wrongTask();
        } catch (Exception e) {
            wrongTask();
        }finally {
            //throw new RuntimeException("777");
        }
    }

    @Test
    @DisplayName("")
    void finallyTest2(){
        System.out.println(finallyTestHelper());
    }

    private Fruit finallyTestHelper(){
        Fruit fruit = new Fruit("a", "aa", BigDecimal.ZERO);
        try {
            fruit.setName("A");
            wrongTask();
            return fruit;
        }catch (Exception e){
            fruit.setName("B");
            return fruit;
        }finally {
            fruit = new Fruit("a", "aa", BigDecimal.ZERO);
            //fruit.setName("C");
            //return new Fruit("a", "aa", BigDecimal.ZERO);
        }
    }

    @Test
    @DisplayName("anySuccess")
    void anySuccess() throws InterruptedException {
        long start = System.currentTimeMillis();
        AsynUtil.newWork()
                .addTask(()->successT(5000))
                .addTask(()->successT(9000))
                .addTask(()->successT(6000))
                .succeedAny();

        long end = System.currentTimeMillis();
        System.out.println("end test : " + (end-start));
        Thread.sleep(12000);

    }
    @Test
    @DisplayName("anySuccessOneFail")
    void anySuccessOneFail() throws InterruptedException {
        long start = System.currentTimeMillis();
        AsynUtil.newWork()
                .addTask(()->failureT(5000))
                .addTask(()->successT(9000))
                .addTask(()->successT(6000))
                .succeedAny();

        long end = System.currentTimeMillis();
        System.out.println("end test : " + (end-start));
        Thread.sleep(12000);

    }
    @Test
    @DisplayName("anySuccessAllFail")
    void anySuccessAllFail() throws InterruptedException {
        long start = System.currentTimeMillis();
        AsynUtil.newWork()
                .addTask(()->failureT(5000))
                .addTask(()->failureT(9000))
                .addTask(()->failureT(6000))
                .succeedAny();

        long end = System.currentTimeMillis();
        System.out.println("end test : " + (end-start));
        Thread.sleep(12000);

    }

    private void successT(int t){
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            System.out.println("successT" + t + " is interrupted");
            return;
        }
        System.out.println("successT" + t + " succeed");
    }

    private void failureT(int t){
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            System.out.println("failureT" + t + " is interrupted");
            return;
        }
        System.out.println("failureT" + t + " is failure");
        throw new RuntimeException("failureT" + t + " is failure");
    }

    @Test
    @DisplayName("监听")
    void whenAddListener() throws ExecutionException, InterruptedException {

        ListenableFuture<?> a1 = new AbstractFuture<Object>(){};
        ListenableFuture<?> abstractFuture = new AbstractFuture<Object>() {
            @Override
            public Object get() throws ExecutionException, InterruptedException {
                a1.addListener(this::nothing, MoreExecutors.directExecutor());
                nothing();
                return super.get();
            }

            private void nothing(){
                set(null);
            }
        };
        abstractFuture.get();
    }

}
