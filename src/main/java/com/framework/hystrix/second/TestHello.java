package com.framework.hystrix.second;

import com.framework.hystrix.hello.HelloWorldHystrixCommand;

/**
 * Created by jiamingku on 2018/12/21.
 */
public class TestHello {

    public static void main(String[] args) {

        Object result = new HelloWorldHystrixObservableCommand("HLX").observe();
        System.out.println(result);  // 打印出Hello HLX
    }
}
