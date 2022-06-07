package com.jiamingku.j2se.refelct.proxy.proxy1;

/**
 * 1.运行测试类(自己实现的动态代理)
 * <p>
 * 动态代理就是在上面在进行一层抽象，把想加入的逻辑一起写入到.java文件中
 */
public class Client {
    public static void main(String[] args) throws Exception {
        /**
         * 创建被代理对象
         */
        Tank t = new Tank();
        /**
         * 调用助手，
         */
        InvocationHandler h = new TimeHandler(t);
        /**
         * 创建个代理对象，你要代理那个接口？参数传递进去
         */
        Moveable m = (Moveable) Proxy.newProxyInstance(Moveable.class, h);
        m.move();
    }
}
