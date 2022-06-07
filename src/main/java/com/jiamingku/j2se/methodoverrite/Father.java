package com.jiamingku.j2se.methodoverrite;

public class Father {
    protected String name = "父亲属性";
    public static String  father = "father";
    public void method() {
        System.out.println(this);
        System.out.println("this.getClass().getName() = " + this.getClass().getName());
        System.out.println("super.getClass().getName() = " + super.getClass().getName());
        System.out.println("父类方法，对象类型：" + this.getClass());
    }
}