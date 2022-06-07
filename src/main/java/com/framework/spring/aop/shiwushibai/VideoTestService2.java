package com.framework.spring.aop.shiwushibai;

import org.springframework.aop.framework.AopContext;

public class VideoTestService2 {

    public void test1() {
        System.out.println("test1()");
    }

    public final void test2() {
        System.out.println("test2()");
        test3();
    }

    public void test3() {
        System.out.println("test3()");
    }

    public void test4() {
        System.out.println("test4()");
        VideoTestService2 service = (VideoTestService2) AopContext.currentProxy();
        service.test5();
    }

    public void test5() {
        System.out.println("test5()");
    }

    public void add() {
        System.out.println("add()");
        test1();

    }
}