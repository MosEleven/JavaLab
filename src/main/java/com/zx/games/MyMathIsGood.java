package com.zx.games;

import java.util.*;

public class MyMathIsGood {

    static int target;
    static int limit;
    static List<String> solution;
    static Map<Integer,Integer> map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        limit = sc.nextInt();
        target = (a+b+c)/3;
        map = new HashMap<>();
        solution = new ArrayList<>(limit);
        if ((a+b+c)%3!=0) System.out.println("illegal parameter");
        cal(a,b,c,0);
        System.out.println("ending");
    }

    public static void cal(int a, int b, int c, int deep) {
        deep++;
        if (a==target && b==target && c==target){
            for (String s : solution) {
                System.out.println(s);
            }
            return;
        }
        if (deep>limit) return;
        process(a,b,c,deep);
        process(b,a,c,deep);
        process(c,b,a,deep);
    }
    public static boolean addCode(int a, int b, int c,int deep) {
        int n = a*10000 + b*100 + c;
        if (map.containsKey(n) && map.get(n)<= deep){
            return false;
        }
        map.put(n,deep);
        map.put(a*10000 + c*100 + b,deep);
        map.put(b*10000 + a*100 + c,deep);
        map.put(b*10000 + c*100 + a,deep);
        map.put(c*10000 + a*100 + b,deep);
        map.put(c*10000 + b*100 + a,deep);
        return true;
    }

    public static void process(int n1, int n2, int n3, int deep) {
        if (n1 % 2 == 0) {
            process2(n1/2,n1/2+n2,n3,deep);
            process2(n1/2,n2,n1/2+n3,deep);
        }else{
            process2(0,n1+n2,n3,deep);
            process2(0,n2,n1+n3,deep);
        }
    }
    public static void process2(int n1, int n2, int n3, int deep) {
        if (addCode(n1,n2,n3,deep)) {
            solution.add(n1+","+n2+","+n3);
            cal(n1,n2,n3,deep);
            solution.remove(solution.size()-1);
        }
    }


}


//            System.out.printf("%.2f\n",res);
