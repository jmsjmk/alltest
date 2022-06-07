package com.framework.spring.aop.aop1;

import org.springframework.stereotype.Component;

/**
 * Created by jiamingku on 2018/11/22.
 */
@Component("hello")
public class HelloImpl implements Hello {


    public int addUser(String name, String pass) {
        System.out.println("===业务方法:入参name:[" + name + "] pass:[" + pass + "] 准备返回结果:100  " + this.getClass().getSimpleName() + "  this?????");
        return 100;
    }

    public void foo() {
        System.out.println("执行Hello组件的foo()");
    }

    @Override
    public int process() {
        try {
            boolean b = true;
            if (b) {
                throw new NullPointerException("null");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return 100;
    }

    @Override
    public void th() {
        throw new NullPointerException("tty");
    }
}
