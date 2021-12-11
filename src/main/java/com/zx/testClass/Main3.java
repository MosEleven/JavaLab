package com.zx.testClass;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] a = new int[n];//0
        int[] b = new int[n];//1
        int[] c = new int[n];//1
        int i = 0;
        while(i<n){
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
            c[i] = sc.nextInt();
            i++;
        }

    }
}
