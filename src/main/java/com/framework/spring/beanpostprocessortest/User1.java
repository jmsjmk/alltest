package com.framework.spring.beanpostprocessortest;

public class User1 {
    public User1() {
        System.out.println("实例化User1的构造方法......" + this);
    }


    public void initUser() {
        System.out.println("不属于任何接口的自定义方法,执行initMethod方法.....");
    }

    public void destroyUser() {
        System.out.println("不属于任何接口的自定义方法,执行destroyMethod方法......");
    }


}
