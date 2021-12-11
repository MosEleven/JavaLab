package com.zx.testClass;


import java.util.Scanner;


/*vvv
4 4
1 3 1 3
1 2 0 2
2 4 1 3
4 3 0 4
*/


public class Main2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] black = new int[n][n];//0
        int[][] white = new int[n][n];//1
        while(m-- >0){
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;
            int color = sc.nextInt();
            int w = sc.nextInt();

            black[a][b] = color==0? 1 : w+1;
            black[b][a] = black[a][b];
            white[a][b] = color==1? 1 : w+1;
            white[b][a] = white[a][b];
        }

        int mb = minW(black);
        int mw = minW(white);
        System.out.println(Math.min(mb,mw));

    }
    public static int minW(int[][] route){
        int n = route.length;
        int[] reach = new int[n];
        boolean[] reached = new boolean[n];
        reached[0] = true;
        for(int i=0;i<n;i++ ) reach[i] = -1;
        reach[0] = 0;
        for (int i = 1; i < n; i++) {
            if(route[0][i]>0){
                reach[i] = route[0][i]-1;
            }
        }
        while(!reached[n-1]){
            int nearP = 0;
            int nearW = 200;
            for (int i = 0; i < n; i++) {
                if(reached[i] || reach[i]<0) continue;
                if(nearW>reach[i]){
                    nearW = reach[i];
                    nearP = i;
                }
            }
            reached[nearP] = true;
            for(int i = 0;i<n;i++){
                if(reached[i]) continue;
                if (route[nearP][i]>0){
                    int w = route[nearP][i]-1;
                    if(reach[i]==-1){
                        reach[i] = w;
                    }else {
                        reach[i] = Math.min(reach[i],w+nearW);
                    }
                }
            }

        }
        return reach[n-1];
    }

}
