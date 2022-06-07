package com.framework.spring.Interceptor.learnimpl;

public class BusinessClass implements BusinessFacade {
    public void doSomething() {
        System.out.println("在业务组件 BusinessClass 中调用方法: doSomething()");
    }
}
