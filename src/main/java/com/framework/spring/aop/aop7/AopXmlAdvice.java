package com.framework.spring.aop.aop7;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jiamingku on 2018/11/23.
 */
public class AopXmlAdvice {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aop7.xml");
        Sleepable sleeper = (Sleepable) context.getBean("human");
        sleeper.sleep();
        System.out.println(" ================ ");
    }
}

