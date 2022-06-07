package com.jiamingku.j2se.refelct.bo;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * 111
 * Created by jiamingku on 2018/9/3.
 */
public class Test {
    private int a = 1;
    private char c = '3';

    private long l = 100L;


    Object o = null;

    public static void main(String[] args) {
        Test t = new Test();
        Field[] fileds = t.getClass().getDeclaredFields();
        for (Field f : fileds) {
            System.out.println("f.getName() = " + f.getName());
            System.out.println("f = " + f.getDeclaringClass());
            Type type = f.getGenericType();
            System.out.println("type = " + type);

        }
        String name = Integer.class.getName();
        System.out.println("name = " + name);
        Object[] o1 = new Object[]{};
        name = o1.getClass().getName();
        System.out.println("o1 = " + o1);
        System.out.println("name = " + name);
    }

    public void sayHello(String str) {
        System.out.println("str = " + str);
    }
}
