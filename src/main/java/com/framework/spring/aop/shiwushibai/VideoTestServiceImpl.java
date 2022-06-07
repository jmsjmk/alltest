package com.framework.spring.aop.shiwushibai;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

@Service
public class VideoTestServiceImpl implements VideoTestService {
    @Override
    public void test1() {
        System.out.println("test1()");
    }

    @Override
    public void test2() {
        System.out.println("test2()");
        test3();
    }

    @Override
    public void test3() {
        System.out.println("test3()");
    }

    @Override
    public void test4() {
        System.out.println("test4()");
        String name = AopContext.currentProxy().getClass().getSimpleName();
        System.out.println("name = " + name);
        VideoTestService service = (VideoTestService) AopContext.currentProxy();
        service.test5();
    }

    @Override
    public void test5() {
        System.out.println("test5()");
    }
}