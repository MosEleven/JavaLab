package com.zx.testClass;

public class InitialClass {

    {
        System.out.println("class function block1");
    }

    private int n = getNewInt();

    public InitialClass(int n){
        System.out.println("instance");
        this.n = n;
    }

    {
        System.out.println("class function block2");
        this.n = 2;
    }

    public int getNewInt(){
        System.out.println("method");
        return 1;
    }

    public int getN(){
        return this.n;
    }
}
