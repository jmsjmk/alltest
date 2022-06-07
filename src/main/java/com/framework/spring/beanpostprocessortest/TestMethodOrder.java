package com.framework.spring.beanpostprocessortest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by jiamingku on 2018/11/22.
 * <p>
 * 1，首先执行bean的构造方法,
 * <p>
 * 2，BeanPostProcessor的postProcessBeforeInitialization方法
 * <p>
 * 3，InitializingBean的afterPropertiesSet方法
 * <p>
 * 4，@Bean注解的initMethod方法
 * <p>
 * 5，BeanPostProcessor的postProcessAfterInitialization方法
 * <p>
 * 6，DisposableBean的destroy方法
 * <p>
 * 7，@Bean注解的destroyMethod方法
 */
public class TestMethodOrder {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext2 = new AnnotationConfigApplicationContext(MainConfig.class);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        applicationContext2.close();
        try {
            Thread.sleep(1000L * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int a() {
        return 1;
    }

    int a(int a) {
        return 1;
    }
}