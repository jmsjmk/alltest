package com.jiamingku.j2se.refelct.proxy.proxy2;

import com.jiamingku.j2se.refelct.proxy.proxy1.InvocationHandler;

import java.lang.reflect.Method;

public class TransactionHandler implements InvocationHandler {

    private Object target;

    public TransactionHandler(Object target) {
        super();
        this.target = target;
    }

    /**
     * 这个就是我们在动态生成的代码中，需要调用的方法
     *
     * @param o
     * @param m
     */
    @Override
    public void invoke(Object o, Method m) {
        System.out.println(o.getClass().getSimpleName());
        System.out.println("Transaction Start");
        try {
            m.invoke(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Transaction Commit");
    }

}