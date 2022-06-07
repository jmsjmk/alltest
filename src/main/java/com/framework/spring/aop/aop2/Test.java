package com.framework.spring.aop.aop2;

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
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop2.xml");
        Hello o = (Hello) ctx.getBean("hello");
        o.addUser("1", "1");
    }
}
