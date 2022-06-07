package com.framework.spring.aop.aop3;

import com.framework.spring.aop.aop1.Hello;
import org.springframework.stereotype.Component;

/**
 * Created by jiamingku on 2018/11/22.
 */
@Component("hello")
public class HelloImpl implements Hello {


    public int addUser(String name, String pass) {
        System.out.println("==============执行Hello组件的addUser方法:入参:" + name);
//        if (true)
//        throw new NullPointerException("what");
        System.out.println("方法实际的返回值:" + 100);
        return 100;

    }

    public void foo() {
        System.out.println("执行Hello组件的foo()");
    }

    @Override
    public int process() {
        return 100;
    }

    @Override
    public void th() {
        throw new NullPointerException("tty");
    }
}
