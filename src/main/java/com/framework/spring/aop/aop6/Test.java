package com.framework.spring.aop.aop6;

import com.framework.spring.aop.aop4.HelloWorld;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jiamingku on 2017/4/18.
 */
public class Test {
    /**
     * 一个切入点，切入两个切面。并且通过order可以执行先后的顺序
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop6.xml");

        HelloWorld hw1 = (HelloWorld) ctx.getBean("helloWorldImpl1");
        System.out.println("hw1.getClass().getSimpleName() = " + hw1.getClass().getName());
        HelloWorld hw2 = (HelloWorld) ctx.getBean("helloWorldImpl2");
        System.out.println("hw2.getClass().getSimpleName() = " + hw2.getClass().getName());


        hw1.printHelloWorld();
        System.out.println();
        hw1.doPrint();

        System.out.println();
        hw2.printHelloWorld();
        System.out.println();
        hw2.doPrint();


    }
}
