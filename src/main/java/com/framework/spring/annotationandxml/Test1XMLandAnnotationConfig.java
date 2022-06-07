package com.framework.spring.annotationandxml;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


/**
 * Created by jiamingku on 16/10/26.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:anotation-xml.xml"})
public class Test1XMLandAnnotationConfig {

    @Resource
    private D d;


    public static void main(String[] args) {

        ApplicationContext annotationContext = new ClassPathXmlApplicationContext("anotation-xml.xml");
        D d = (D) annotationContext.getBean("d");
        d.t();
    }


}
