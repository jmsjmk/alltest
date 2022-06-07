package com.framework.spring.aop.aop9;

import com.framework.spring.aop.aop1.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jiamingku on 2018/11/22.
 */
@SuppressWarnings("all")
public class Test {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop9.xml");

        Hello o = (Hello) ctx.getBean("hello");
        System.out.println("o = " + o);
        System.out.println("hw1.getClass().getSimpleName() = " + o.getClass().getName());

        System.out.println();
        o.addUser("name", "pass");
        System.out.println();

    }
}
