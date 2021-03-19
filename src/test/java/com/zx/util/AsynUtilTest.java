package com.zx.util;

import com.zx.testClass.Fruit;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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
                .submit();

        AsynUtil.Work work = AsynUtil.newWork()
                .addTask(this::longTask)
                .addTask(this::longTask);

                work.addTask(this::longTask)
                .addTask(this::longTask)
                .submit();
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
                .submit();
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

}
