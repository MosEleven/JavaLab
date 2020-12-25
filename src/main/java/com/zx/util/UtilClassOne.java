package com.zx.util;

import lombok.Builder;

@Builder
public class UtilClassOne {

    private String attrOne;

    public void method1(){
        System.out.println("UtilClass1's method1 was invoked");
    }

    public static final void method2(){
        System.out.println("UtilClass1's method2 was invoked");

    }

}
