package com.framework.hystrix.hello.sync;

import com.framework.hystrix.hello.HelloWorldHystrixCommand;

import java.util.concurrent.ExecutionException;

/**
 * Created by jiamingku on 2018/12/21.
 */
@SuppressWarnings("all")
public class TestHello2 {

    public static void main(String[] args) {
        Object result;
        HelloWorldHystrixCommand helloWorldHystrixCommand = new HelloWorldHystrixCommand("TEST");

        //使用execute()同步调用代码,效果等同于:helloWorldCommand.queue().get();
        try {
            //   // 同样只能运行一次并且是同步的，因为你直接调用了get方法
            result = helloWorldHystrixCommand.queue().get();
            System.out.println(result);
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            // result = helloWorldHystrixCommand.queue().get();
            // System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        System.out.println("helloWorldHystrixCommand = " + helloWorldHystrixCommand);


    }
}
