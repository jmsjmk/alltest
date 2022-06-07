package com.framework.spring.aop.aop4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jiamingku on 2017/4/18.
 * <p>
 * 1.spring aop 只要会配置就可以了(xml,注解配置,多个拦截顺序问题)
 * <p>
 * 2.还有一种更高级的配置.拿一个普通的类pojo 直接加上一些属性就可以做到拦截.
 */
@SuppressWarnings("ALL")
public class Test {

    public static void main(String[] args) {
        /**
         * 代理是否生成文件是有开关设置的，
         *
         * 打印容器帮我们生成的bean信息
         *
         */
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop4.xml");

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
