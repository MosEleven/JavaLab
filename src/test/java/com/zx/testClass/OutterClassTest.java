package com.zx.testClass;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


class OutterClassTest {

    @Test
    void createClass(){

        //外部类
        OutterClass outterClass = new OutterClass();

        OutterClass.InnerClass innerClass = outterClass.innerClass;
        innerClass.print();

        //内部类
        //outterClass.new InnerClass();
        //OutterClass.InnerClass innerClass = outterClass.getInnerClass();

        //sun.misc.Launcher$AppClassLoader@18b4aac2
        //System.out.println(OutterClass.InnerClass.class.getClassLoader());

        //静态内部类
        OutterClass.StaticNestedClass staticNestedClass = new OutterClass.StaticNestedClass();

    }


}
