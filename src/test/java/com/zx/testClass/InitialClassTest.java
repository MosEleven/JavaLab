package com.zx.testClass;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InitialClassTest {

    @Test
    void test(){
        InitialClass initialClass = new InitialClass(3);
        InitialClass initialClas2 = new InitialClass(3);

        System.out.println(initialClass.getN());
    }

}
