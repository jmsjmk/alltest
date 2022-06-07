package com.framework.spring.beanpostprocessortest;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.annotation.Resource;

/**
 * 包含一个资源
 */
public class User2 implements BeanPostProcessor  {

    @Resource
    private User1 user1;

    public User1 getUser1() {
        return user1;
    }

    public void setUser1(User1 user1) {
        System.out.println("set user1 excute...");
        this.user1 = user1;
    }

    public User2() {
        System.out.println("User2的构造方法......");
    }


    public void initUser() {
//        System.out.println(user1);
        System.out.println("\tUser2 initUser（自定义).....");
        System.out.println("user1 = " + user1);
    }

    public void destroyUser() {
        System.out.println("\tUser2 不属于任何接口的自定义方法,执行destroyMethod方法......");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("\tUser2 postProcessBeforeInitialization=before 43 " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("\tUser2 postProcessAfterInitialization=after 26");
        return bean;
    }


}
