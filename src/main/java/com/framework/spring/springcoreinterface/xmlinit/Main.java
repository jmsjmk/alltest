package com.framework.spring.springcoreinterface.xmlinit;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 只有这个类创建完成之后--才可能运行init方法-这个区别spring核心接口的方法
 *
 */
public class Main {
    public static void main(String[] args){
        ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("spring1.xml");
    }
}