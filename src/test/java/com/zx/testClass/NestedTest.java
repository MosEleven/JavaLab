package com.zx.testClass;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NestedTest {

    @Test
    void test(){
        int a = OutterInterface.a;

        Nested nested = new Nested();
        nested.inner();

        NestInterfaceImpl nestInterface = new NestInterfaceImpl();
        nestInterface.out();

    }

}
