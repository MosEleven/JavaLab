package com.zx.util;

import java.util.*;

class Solution {

    Deque<Integer> minQ;
    Deque<Integer> maxQ;
    int wlen;
    int target;
    int[] nums;

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        //保存k+1长度内的最大最小值
        //每次往右挪动一格判断是否满足条件

        if (k==0) return t==0;
        this.wlen = k + 1;
        this.target = t;
        this.nums = nums;
        this.minQ = new LinkedList<>();
        this.maxQ = new LinkedList<>();

        int len = nums.length;
        for (int i=0; i < len; i++) {
            if (addQAndJudge(i)){
                return true;
            }
        }
        return false;
    }

    private void addQ(int index) {
        if (index >= wlen){
            removeQ(index - wlen);
        }
        int n = nums[index];
        if (index > 0){
            while (!maxQ.isEmpty() && maxQ.getLast()<n) maxQ.removeLast();
            maxQ.addLast(n);
            while (!minQ.isEmpty() && minQ.getLast()>n) minQ.removeLast();
            minQ.addLast(n);
        }else {
            maxQ.offer(n);
            minQ.offer(n);
        }
    }

    private void removeQ(int index) {
        int n = nums[index];
        if (n==maxQ.getFirst()) {
            maxQ.removeFirst();
        }
        if (n==minQ.getFirst()) {
            minQ.removeFirst();
        }
    }

    private boolean addQAndJudge(int index) {
        addQ(index);
        return (maxQ.getFirst() - minQ.getFirst()) <= target;
    }
}
