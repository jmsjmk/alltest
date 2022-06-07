package com.jiamingku.test;

import javax.xml.bind.SchemaOutputResolver;
import java.io.Serializable;

/**
 * Created by jiamingku on 2018/5/4.
 */
public class Dowhile implements Serializable {

    public static void main(String[] args) {

        // 接口没有父类，有父接口也 也获取不到
        Class superclass = iii.class.getSuperclass();
        System.out.println("superclass = " + superclass);

        boolean anInterface = iii.class.isInterface();
        System.out.println("anInterface = " + anInterface);

        boolean anInterface1 = Dowhile.class.isInterface();
        System.out.println("anInterface1 = " + anInterface1);

        // --


        boolean b = iii.class != null && Object.class.isAssignableFrom(iii.class);
        System.out.println("b = " + b);



    }

    public static void t(int i) {
        System.out.println("i = " + i);
    }

    public static void a() {
        c();
    }

    public static void c() {
        System.out.println("cc");
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            System.out.println(stackTraceElement);
            String cN = stackTraceElement.getClassName();
            System.out.println("cN = " + cN);
            System.out.println("line ==" + stackTraceElement.getLineNumber());
            System.out.println("");
        }
        System.out.println("dd");


        System.out.println("===================\" ====================");


//        throw new NullPointerException("ddd");

    }
}
