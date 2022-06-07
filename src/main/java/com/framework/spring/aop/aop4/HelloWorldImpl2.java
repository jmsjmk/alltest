package com.framework.spring.aop.aop4;

public class HelloWorldImpl2 implements HelloWorld {
    public void printHelloWorld() {
        System.out.println("::::进入业务方法::: HelloWorldImpl2.printHelloWorld ");
    }

    public void doPrint() {
        System.out.println("::::进入业务方法::: HelloWorldImpl2.doPrint ");
        return;
    }
}