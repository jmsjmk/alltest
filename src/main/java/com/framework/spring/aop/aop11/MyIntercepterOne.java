package com.framework.spring.aop.aop11;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.util.ClassUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by jiamingku on 2018/11/28.
 *
 * 采用了advisor的配置方式
 */
public class MyIntercepterOne implements MethodInterceptor, InitializingBean, ApplicationEventPublisherAware {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        // -- 获取被代理对象
        ReflectiveMethodInvocation reflectiveMethodInvocation = (ReflectiveMethodInvocation) invocation;
        System.out.println("看热闹:::"+reflectiveMethodInvocation.getProxy().getClass().getName());

        // ---获取代理对象----
        String simpleName = invocation.getThis().getClass().getSimpleName();
        System.out.println("simpleName = " + simpleName);

        Class<?> clazz = invocation.getThis().getClass();
        System.out.println("clazz.getSimpleName() = " + clazz.getSimpleName());

        String statementId = invocation.getMethod().getName();
        System.out.println(statementId);
        

        Proxy proxy = (Proxy)reflectiveMethodInvocation.getProxy();


        InvocationHandler invocationHandler = Proxy.getInvocationHandler(proxy);
        System.out.println(invocationHandler.getClass().getSimpleName());

//        System.out.println("reflectiveMethodInvocation.ta = " + reflectiveMethodInvocation.ta);

        String name = invocation.getMethod().getDeclaringClass().getName();
        System.out.println("name -----= " + name);
        System.out.println("MyIntercepterOne");
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
