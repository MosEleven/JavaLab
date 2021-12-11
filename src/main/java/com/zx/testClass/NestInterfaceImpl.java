package com.zx.testClass;

public class NestInterfaceImpl implements OutterInterface{


    @Override
    public void out() {
        System.out.println(a);
    }
}

class Nested implements OutterInterface.InnerInterface{

    @Override
    public void inner() {
        printOut();
    }


}
