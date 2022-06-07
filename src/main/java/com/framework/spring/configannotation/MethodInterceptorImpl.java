package com.framework.spring.configannotation;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public  class MethodInterceptorImpl implements MethodInterceptor {
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("MethodInterceptorI......................mpl:" + method.getName());
        return methodProxy.invokeSuper(o, objects);
    }
}