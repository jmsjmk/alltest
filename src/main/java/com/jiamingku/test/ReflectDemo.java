package com.jiamingku.test;

import java.lang.reflect.Method;

public class ReflectDemo {
    public static void main(String[] args) throws Exception {
        Proxy target = new ReflectDemo.Proxy();
        Method method = Proxy.class.getDeclaredMethod("pmethod", null);
        //MethodAccessor.invoke
        method.invoke(target, null);

    }

    static class Proxy {
        public void pmethod() {
            System.out.println("Proxy.pmethod");
        }
    }
}