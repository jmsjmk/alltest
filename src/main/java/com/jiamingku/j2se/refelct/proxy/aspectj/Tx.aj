package com.jiamingku.j2se.refelct.proxy.aspectj;

public aspect Tx {
    void around():call(void HelloWorld.sayHello()){
        System.out.println("开始事务。。。");
        proceed();
        System.out.println("结束事务。。。");
    }
}
