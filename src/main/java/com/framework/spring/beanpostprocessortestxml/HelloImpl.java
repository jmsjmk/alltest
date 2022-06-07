package com.framework.spring.beanpostprocessortestxml;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by jiamingku on 2018/11/22.
 */
@Component("hello")
public class HelloImpl implements BeanPostProcessor {


    private HelloImpl2 helloImpl2;

    public HelloImpl2 getHelloImpl2() {
        return helloImpl2;
    }

    public void setHelloImpl2(HelloImpl2 helloImpl2) {
        System.out.println("helloImpl2 属性被设置. ");
        this.helloImpl2 = helloImpl2;
    }

    public HelloImpl() {
//        System.out.println("HelloImpl构造器被执行");
    }

    public int addUser(String name, String pass) {
        System.out.println("==============执行Hello组件的addUser方法:入参:" + name);
        System.out.println("方法实际的返回值:" + 100);
        return 100;
    }

    public void foo() {
        System.out.println("执行Hello组件的foo()");
    }



    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization = " + beanName + "\t" + bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization = " + beanName+ "\t" + bean);
        return bean;
    }
}
