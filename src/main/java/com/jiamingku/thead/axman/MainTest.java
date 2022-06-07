package com.jiamingku.thead.axman;

import com.jiamingku.thead.axman.custor.NoResultInvokeThread;
import com.jiamingku.thead.axman.custor.ResultInvokeThread;

public class MainTest {
    public static void main(String[] args) throws Exception {
        /**
         * 创建代理对象，里面主要有个调度线程
         *
         * 主要是启动了调度器，主要是消费，
         */
        ISmartAxmanService service = SmartAxmanServiceFactory.createService();
        /**
         * 传递的是代理对象
         */
        new ResultInvokeThread("Axman",service).start();
        new ResultInvokeThread("Sager",service).start();

        //不需要返回结果的线程
        new NoResultInvokeThread("Macke",service).start();
    }  
}  