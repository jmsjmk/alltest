package com.framework.hystrix.hello.sync;

import com.framework.hystrix.hello.HelloWorldHystrixCommand;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiamingku on 2018/12/21.
 */
@SuppressWarnings("all")
public class TestHello3 {

    public static void main(String[] args) throws Exception {
        Object result;
        HelloWorldHystrixCommand helloWorldHystrixCommand = new HelloWorldHystrixCommand("Asynchronous-hystri");


        //异步调用,可自由控制获取结果时机,
        Future<String> future = helloWorldHystrixCommand.queue();
        //get操作不能超过command定义的超时时间,默认:1秒
        result = future.get(100, TimeUnit.MILLISECONDS);
        System.out.println("result=" + result);
        System.out.println("mainThread=" + Thread.currentThread().getName());

//        System.out.println("helloWorldHystrixCommand = " + helloWorldHystrixCommand);


    }
}
