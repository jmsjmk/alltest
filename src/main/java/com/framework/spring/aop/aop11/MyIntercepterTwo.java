package com.framework.spring.aop.aop11;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.util.ClassUtils;

/**
 * Created by jiamingku on 2018/11/28.
 *
 * 采用了advisor的配置方式
 */
public class MyIntercepterTwo implements MethodInterceptor, InitializingBean, ApplicationEventPublisherAware {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        System.out.println("MyIntercepterTwo");

        // 决定调用方法---
        return invocation.proceed();
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {

    }
}
