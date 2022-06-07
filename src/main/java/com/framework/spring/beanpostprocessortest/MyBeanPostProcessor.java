package com.framework.spring.beanpostprocessortest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 这边只做简单打印   原样返回bean
        System.out.println("postProcessBeforeInitialization====" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 这边只做简单打印   原样返回bean
        System.out.println("postProcessAfterInitialization====" + beanName);
        return bean;
    }
}
 