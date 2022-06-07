package com.framework.hystrix.hello;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * Created by jiamingku on 2018/12/21.
 */
public class HelloWorldHystrixCommand extends HystrixCommand {
    private final String name;
    public HelloWorldHystrixCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }
    // 如果继承的是HystrixObservableCommand，要重写Observable construct()
    @Override
    protected String run() {
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        return "Hello " + name +" thread:" + Thread.currentThread().getName() + "Id:" + Thread.currentThread().getId();
    }
}

