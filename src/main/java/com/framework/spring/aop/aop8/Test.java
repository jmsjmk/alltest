package com.framework.spring.aop.aop8;

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
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aopTx.xml");

        Hello o = (Hello) ctx.getBean("hello");
        System.out.println("Spring[使用的动态代理来实现的]:::: = " + o.getClass().getName());

        System.out.println();
        int apoValue = o.addUser("name", "pass");
        System.out.println("aop之后的value:" + apoValue);


        String s = "{\"session\":{\"id\":\"SESSION0002484794971M9050938I80\"},\"apiOperation\":\"VERIFY\",\"sourceOfFunds\":{\"type\":\"CARD\"},\"order\":{\"currency\":\"CNY\"}}";


        System.out.println(s);

    }
}
