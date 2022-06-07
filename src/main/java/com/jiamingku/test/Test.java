package com.jiamingku.test;

import javax.annotation.Resource;

public class Test {

    public String a = Math.random() + "3";

    @Resource
    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public static void main(String[] args) {
        Test.A a1 = new Test().new A();
        System.out.println(a1.getA());
        Test.A a2 = new Test().new A();
        System.out.println(a2.getA());

    }


    class A extends Test {

    }
}
