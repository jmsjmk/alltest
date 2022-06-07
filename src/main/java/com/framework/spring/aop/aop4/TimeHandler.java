package com.framework.spring.aop.aop4;

public class TimeHandler {

//
//    void printHelloWorld();
//
//    void doPrint();
//

    public void beforeTime() {
        System.out.println("拦截器[TimeHandler] beforeTime");
    }

    public void afterTime() {
        System.out.println("拦截器[TimeHandler] afterTime");
    }
}