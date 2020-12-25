package com.zx.util;

import java.util.PriorityQueue;

public class MedianFinder {

    private int count = 0;
    private PriorityQueue<Integer> pqB = new PriorityQueue<>();
    private PriorityQueue<Integer> pqS = new PriorityQueue<>((a,b)->{
        return a>b? -1: 1;
    });
    /** initialize your data structure here. */
    public MedianFinder() {
        pqS.add(Integer.MIN_VALUE);
    }

    public void addNum(int num) {
        count++;
        //奇数加到右边的小根堆，偶数加到左边的大根堆
        if(count%2==1){
            //加到右边时要看是不是比左边的要小
            int n = pqS.peek();
            if(num<n){
                pqS.poll();
                pqS.add(num);
                pqB.add(n);
            }else {
                pqB.add(num);
            }
        }else{
            //加到左边时要看是不是比右边的要大
            int n = pqB.peek();
            if(num>n){
                pqB.poll();
                pqB.add(num);
                pqS.add(n);
            }else {
                pqS.add(num);
            }
        }
    }

    public double findMedian() {
        if(count%2==1){
            return pqB.peek();
        }else {
            return ( (double)pqB.peek() + pqS.peek() )/2;
        }
    }
}
