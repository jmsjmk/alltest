package com.framework.hystrix.hello.sync;

import com.framework.hystrix.hello.HelloWorldHystrixCommand;

/**
 * Created by jiamingku on 2018/12/21.
 */
@SuppressWarnings("all")
public class TestHello1 {

    public static void main(String[] args) {

        Object result = new HelloWorldHystrixCommand("HLX").execute();
        System.out.println(result);  // 打印出Hello HLX
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName() +
                "Id:" + Thread.currentThread().getId());
        /**
         *  每个Command对象只能调用一次,不可以重复调用,
         // 重复调用对应异常信息:This instance can only be executed once. Please instantiate a new instance.
         // 并且 execute就相当于一种同步机制
         */
        HelloWorldHystrixCommand helloWorldHystrixCommand = new HelloWorldHystrixCommand("TEST");

        result = helloWorldHystrixCommand.execute();

        System.out.println("helloWorldHystrixCommand = " + helloWorldHystrixCommand);

//        result = helloWorldHystrixCommand.execute();
//        System.out.println("helloWorldHystrixCommand = " + helloWorldHystrixCommand);


//        helloWorldHystrixCommand = new HelloWorldHystrixCommand("TEST");
//
//        result = helloWorldHystrixCommand.execute();
//        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());

    }
}
