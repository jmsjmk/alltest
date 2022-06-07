package com.jiamingku.j2se.oop.constract.test;

/**
 * Created by jiamingku on 2020/3/23.
 */
public class T {

    /**
     * 顺序决定这个值的多少
     */
    private static int a = 1;
    static {
        a = 100;
    }
    public static void main(String[] args) {
        System.out.println("T.a = " + T.a);
    }
}
