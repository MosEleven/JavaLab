package com.zx.games;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cal24 {

    static List<String> process;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        process = new ArrayList<>(3);
        int[] nums = new int[4];
        nums[0] = sc.nextInt();
        nums[1] = sc.nextInt();
        nums[2] = sc.nextInt();
        nums[3] = sc.nextInt();
        calOnce(nums);
    }
    public static void calOnce(int[] nums){
        int len = nums.length;
        if (len == 1) {
            if (nums[0] == 24) System.out.println(process);
            return;
        }
        int[] nextNums = new int[len-1];
        for (int i = 0; i < len - 1; i++) {
            for (int j = 1; j < len - i; j++) {
                int a = nums[i], b = nums[i+j];
                int n = a + b;
                process.add(a+"+"+b);
                calOnce(fillNextNums(nums,nextNums,n,i,i+j));
                process.remove(process.size()-1);

                n = Math.abs(a - b);
                process.add("|"+a+"-"+b+"|");
                calOnce(fillNextNums(nums,nextNums,n,i,i+j));
                process.remove(process.size()-1);

                n = a * b;
                process.add(a+"x"+b);
                calOnce(fillNextNums(nums,nextNums,n,i,i+j));
                process.remove(process.size()-1);

                if (a!=0&&b!=0&&(a%b==0 || b%a==0)) {
                    if(a>=b) n = a/b;
                    else n = b/a;
                    process.add("|"+a+"/"+b+"|");
                    calOnce(fillNextNums(nums,nextNums,n,i,i+j));
                    process.remove(process.size()-1);
                }
            }
        }
    }
    public static int[] fillNextNums(int[] pre, int[] next,int n, int skip1, int skip2) {
        next[0] = n;
        int p = 1;
        for (int i = 0; i < pre.length; i++) {
            if (i==skip1 || i==skip2) continue;
            next[p++] = pre[i];
        }
        return next;
    }
}
