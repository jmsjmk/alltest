package com.jiamingku.j2se.refelct.proxy.aspectj;
/*
    https://blog.csdn.net/weixin_41338006/article/details/80939212
 */

/**
 * 运行其实就是编译时候时候进行了一些手脚.单独建立项目然后执行编译
 * <p>
 * 1.编译时候将代码植入进去
 * <p>
 * 2.看class文件里面插入了一个类，Tx.class -- 原来是Tx.aj
 * <p>
 * 3.运行时候直接指定主类就行了。
 */
public class HelloWorld {
    public void sayHello() {
        System.out.println(this.getClass().getSimpleName());
        System.out.println("Hello AspectJ");
    }

    public static void main(String[] args) {
        HelloWorld hello = new HelloWorld();
        hello.sayHello();
    }
}
