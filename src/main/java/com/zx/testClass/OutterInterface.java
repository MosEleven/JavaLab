package com.zx.testClass;

public interface OutterInterface {

    int a = 0;

    void out();

    interface InnerInterface{

        void inner();

        default void printOut(){
            System.out.println(a);
        }
    }
}
