package com.zx.myStream;

import com.google.common.collect.Lists;
import com.zx.testClass.Fruit;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MyStreamTest {

    @Test
    public void test1(){

        Fruit fruit1 = new Fruit("apple","Red apple",new BigDecimal("7"));
        Fruit fruit2 = new Fruit("banana","Malaysia banana",new BigDecimal("10"));
        Fruit fruit3 = new Fruit("apple","Red apple",new BigDecimal("7"));
        Fruit fruit4 = new Fruit("orange","South orange",new BigDecimal("5"));
        ArrayList<Fruit> fruits = Lists.newArrayList(fruit1, fruit2, fruit3, fruit4);
        MyStream.ofList(fruits).filter(f->f.getPrice().compareTo(new BigDecimal("6"))>0).map(Fruit::getName).distinct().sort(String::compareTo).foreach(System.out::println);

    }

    @Test
    void test2(){

    }

}