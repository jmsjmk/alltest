package com.framework.spring.aop.shiwushibai;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟事物失败的情况.
 */
public class TestServiceAop {

    @Autowired
    private VideoTestService videoTestService;

    @Autowired
    private VideoTestService2 videoTestService2;

    public static void main(String[] args) {

        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("swsb.xml");

        VideoTestService videoTestService = (VideoTestService) ctx.getBean("videoTestService");
        VideoTestService2 videoTestService2 = (VideoTestService2) ctx.getBean("videoTestService2");


        System.out.println("AopUtils.isCglibProxy(videoTestService):" + AopUtils.isCglibProxy(videoTestService));
        System.out.println("AopUtils.isJdkDynamicProxy(videoTestService):" + AopUtils.isJdkDynamicProxy(videoTestService));
        System.out.println("AopUtils.isCglibProxy(videoTestService2):" + AopUtils.isCglibProxy(videoTestService2));
        System.out.println("AopUtils.isJdkDynamicProxy(videoTestService2):" + AopUtils.isJdkDynamicProxy(videoTestService2));

        System.out.println("\n--------------------------");
        videoTestService.test1();
        System.out.println("\n--------------------------");
        System.out.println("\n--------------------------");
        System.out.println("\n--------------------------");
        videoTestService.test2();
        System.out.println("\n--------------------------");
        System.out.println("\n--------------------------");
        System.out.println("\n--------------------------");
        videoTestService.test4();
        System.out.println("\n--------------------------");
        System.out.println("\n--------------------------");
        System.out.println("\n--------------------------");
        System.out.println("\n--------------------------");

        videoTestService2.test1();
        System.out.println("\n--------------------------");
        System.out.println("\n--------------------------");
        System.out.println("\n--------------------------");
        videoTestService2.test2();
        System.out.println("\n--------------------------");
        System.out.println("\n--------------------------");
        System.out.println("\n--------------------------");
        videoTestService2.test4();
        System.out.println("\n--------------------------");
        System.out.println("\n--------------------------");
        System.out.println("\n--------------------------");
        videoTestService2.add();

        System.out.println("\n--------------------------");
    }


}