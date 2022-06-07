package com.framework.spring.beanpostprocessortestxml;

import com.framework.spring.aop.aop1.Hello;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by jiamingku on 2018/11/22.
 */
public class HelloImpl2 implements Hello, InitializingBean {

    public HelloImpl2() {
        System.out.println(" 构造器方法创建HelloImpl2 ");
    }

    private HelloImpl3 helloImpl3;

    public HelloImpl3 getHelloImpl3() {
        return helloImpl3;
    }

    public void setHelloImpl3(HelloImpl3 helloImpl3) {
        System.out.println("设置属性。hello3");
        this.helloImpl3 = helloImpl3;
    }

    public int addUser(String name, String pass) {
        System.out.println("==============执行Hello组件的addUser方法:入参:" + name);
        System.out.println("方法实际的返回值:" + 100);
        return 100;
    }

    public void foo() {
        System.out.println("指定方法 init foo 。。。。。");
    }

    @Override
    public int process() {
        return 100;
    }

    @Override
    public void th() {
        throw new NullPointerException("tty");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(" afterPropertiesSet...... ");
    }
}
