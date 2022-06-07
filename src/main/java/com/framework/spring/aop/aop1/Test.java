package com.framework.spring.aop.aop1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.Map;

/**
 * 基于注解的实现方式:
 */
@SuppressWarnings("all")
public class Test {
    public static void main(String[] args) throws InterruptedException {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop1.xml");
        Hello o = (Hello) ctx.getBean("hello");
        System.out.println("代理对象的实现类:=" + o.getClass().getName());
        System.out.println();

        int a = o.addUser("name123", "pass123");
        System.out.println(" 业务方法返回:" + a);

        Map<String, Hello> map = ctx.getBeansOfType(Hello.class);
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object val = map.get(key);
            System.out.println("--key:" + key + " val:" + val);
        }


        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        Hello o1 = (Hello) ctx.getBean("hello");
        System.out.println("o1 = " + o.getClass().getName());

        System.out.println("( o1 == o ) = " + (o1 == o));

        o1.addUser("name", "pwd");
        Object o111;
        System.out.println("o1 = " + o.getClass().getName());

        map = ctx.getBeansOfType(Hello.class);
        iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object val = map.get(key);
            System.out.println("--key:" + key + " val:" + val);
            System.out.println(val.getClass().getSimpleName());
        }

        System.out.println("----");
        System.out.println("----");
        System.out.println("----");
        System.out.println("----");

        Hello h = ctx.getBean(Hello.class);
        System.out.println(h.getClass().getSimpleName());

        System.out.println(o.toString());
    }
}
