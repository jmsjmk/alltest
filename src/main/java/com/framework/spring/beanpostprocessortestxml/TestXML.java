package com.framework.spring.beanpostprocessortestxml;

import com.framework.spring.aop.aop1.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@SuppressWarnings("all")
public class TestXML {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aopXML1.xml");
        /**
         * cglib生成的代理类:::
         */
        Hello o = (Hello) ctx.getBean("hello2");
        System.out.println("o.getClass().getName() = " + o.getClass().getName());
        o.addUser("name", "pass");
        System.out.println();
    }
}
