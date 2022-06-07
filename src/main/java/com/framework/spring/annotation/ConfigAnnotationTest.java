package com.framework.spring.annotation;

import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jiamingku on 2018/11/5.
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/com/framework/spring/annotation/beans.xml")
public class ConfigAnnotationTest {

    public static void main(String[] args) {
        //ApplicationContext annotationContext = new ClassPathXmlApplicationContext("/Users/jiamingku/IdeaProjects/alltest/src/main/java/com/framework/spring/annotation/beans.xml")
        //ApplicationContext annotationContext = new ClassPathXmlApplicationContext("com/framework/spring/annotation/beans.xml")
        ApplicationContext annotationContext = new ClassPathXmlApplicationContext("beans.xml")
                ;// 读取bean.xml中的内容
//        ApplicationContext annotationContext = new AnnotationConfigApplicationContext(SpringConfig.class);

        if (annotationContext == null) {
            System.out.println("annotationContext = " + annotationContext);
        }
//        Counter c = annotationContext.getBean("counter", Counter.class);// 创建bean的引用对象
//        System.out.println(c.getMultiplier());
//        System.out.println(c.isEquals());
//        System.out.println(c.getSong());
//        System.out.println(c.getInstrument().getName());

        Piano p = annotationContext.getBean("piano", Piano.class);
        System.out.println("p.getName() = " + p.getName());


        String[] arrsys = annotationContext.getBeanDefinitionNames();
        for (String s : arrsys) {
            System.out.println("s = " + s);
        }
    }
}
