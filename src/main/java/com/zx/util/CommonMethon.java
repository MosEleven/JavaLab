package com.zx.util;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CommonMethon {
    public static <T> void  swap (T[] array, int a, int b){
        T ob = array[a];
        array[a] = array[b];
        array[b] = ob;
        //System.out.println(a+"和"+b);
    }

}
