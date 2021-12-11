package com.zx.games;

import java.util.Arrays;
import java.util.Random;

public class DrawCardExpectation {

    public static void main(String[] args) {
        Random r = new Random(System.currentTimeMillis());
        int count = 0;
        int acc = 0;
        long total = 0;
        int times = 100000000;
        int[] statistic = new int[100];
        for (int i = 0; i < times; i++) {
            acc++;
            if (/*acc==60 || */r.nextDouble()<0.036){
                count++;
                total += acc;
                if(acc<=100) statistic[acc-1]++;
                acc = 0;
            }

        }
        String[] sta = new String[100];
        for (int i = 0; i < 100; i++) {
            sta[i] = String.format("%.2f%%",(double) statistic[i] / count * 100);
        }
        System.out.println("count:"+count);
        System.out.printf("freq:%.2f%%%n",(double)count/times*100);
        System.out.printf("avg:%.2f%n",(double)total/count);
        System.out.println(Arrays.toString(sta));
    }
}
