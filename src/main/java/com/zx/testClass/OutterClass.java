package com.zx.testClass;

public class OutterClass {

    public InnerClass innerClass = getInnerClass();
    public StaticNestedClass nestedClass = getNestedClass();

    public static String outStatic = "outStatic";

    public InnerClass getInnerClass(){
        return new InnerClass();
    }
    public StaticNestedClass getStaticNestedClass(){
        return new StaticNestedClass();
    }

    public void printInnerClass(InnerClass innerClass){
        innerClass.print();
    }

    public StaticNestedClass getNestedClass(){
        return new StaticNestedClass();
    }


    public class InnerClass{

        public String inner = "InnerClass' info";

        public void print(){
            System.out.println(inner);
        }
    }

    public static class StaticNestedClass {

        public String nested = "StaticNestedClass' info";

        public void print(){
            System.out.println(outStatic);
            System.out.println(nested);
        }

    }
}
