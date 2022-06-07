package com.jiamingku.j2se.refelct.bo;

import java.lang.reflect.Method;

/**
 * Created by jiamingku on 2018/12/29.
 */
class P1 {
    public void ttttt() {

    }

    private void pppppptttttt() {

    }
}

public class Parent extends P1 {

    public Parent(int parentIntValue, int privateIntValue) {
        this.parentIntValue = parentIntValue;
        this.privateIntValue = privateIntValue;
    }

    public Parent() {
    }

    public int parentIntValue;
    private int privateIntValue;

    public static void main(String[] args) {
        String name = Parent.class.getName();
        System.out.println("name = " + name);
        String simpleName = Parent.class.getSimpleName();
        System.out.println("simpleName = " + simpleName);


        String simpleName1 = Parent.class.getSuperclass().getSimpleName();
        System.out.println("simpleName1 = " + simpleName1);


        Method[] methods = Parent.class.getMethods();
        System.out.println("--");
        System.out.println("--");
        for (Method m : methods) {
            System.out.println(m.getName());
        }
    }

    /* 方法-----------*/
    public static void staticParentMethod() {
    }

    public void publicParentMethod() {
    }

    private void privateParentMethod() {
    }

    protected void protectedParentMethod() {
    }

    void defaultMethod() {
    }

    /* 方法-----------*/

    public void parentA() {
    }

    public static void publicstaticMethod() {
    }

    private static void privatestaticMethod() {

    }

    static void staticDefaultMethod() {

    }

    public final void finalMethod() {
        for (int i = 0; i < 100; i++) {
            System.out.println("i = " + i);
        }
    }


    void defaultAMethod() {

    }

    public void parentB() {

    }

    public void a_public() {

    }

    public void a_public(int i) {

    }

    public void a_public(String s) {

    }

    private void b_public() {

    }

    protected void b_public(int i) {

    }

    void c_public(String s) {

    }
}
