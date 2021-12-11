package com.zx.testClass;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String mark = reader.readLine().trim();
        char[] cs = mark.toCharArray();

        Deque<Integer> nums = new LinkedList<>();
        Deque<Integer> op = new LinkedList<>();

        //difine #:1, @:2
        int n = 0;
        for(char c : cs){
            if(c!='#'&&c!='@'){
                n = n*10 + c-'0';
                continue;
            }
            nums.push(n);
            n = 0;
            int opn = (c=='#')? 1 : 2;

            while(!op.isEmpty()&&op.peek()<=opn){
                int b = nums.pop();
                int a = nums.pop();
                int temp = op.pop();
                if(temp==1){
                    nums.push(a-(a&b));
                }else{
                    nums.push(a+b);
                }
            }
            op.push(opn);
        }
        //最后还有一个n没有入栈
        nums.push(n);
        while(!op.isEmpty()){
            int b = nums.pop();
            int a = nums.pop();
            int temp = op.pop();
            if(temp==1){
                nums.push(a-(a&b));
            }else{
                nums.push(a+b);
            }
        }

        System.out.println(nums.pop());
    }
}
