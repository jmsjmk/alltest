package com.jiamingku.j2se.oop.statics;

public class StaticTest {
    private static int a;
    private int b;
    static {
        StaticTest.a = 3;
        System.out.println(a);
        StaticTest t = new StaticTest();
        t.f();
        t.b = 1000;
        System.out.println(t.b);

    }
    /** 静态的抽象方法是不存在*/
    // public static abstract staticAbsMethod() ;

    public static abstract class T {

    }

    static {
        StaticTest.a = 4;
        System.out.println(a);
    }
    public static void main(String[] args) {
    }
    static {
        StaticTest.a = 5;
        System.out.println(a);
    }
    public void f() {
        System.out.println("hhahhahah");
    }
}