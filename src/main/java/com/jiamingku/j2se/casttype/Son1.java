package com.jiamingku.j2se.casttype;

/**
 * Created by jiamingku on 2019/3/25.
 */
public class Son1 extends Super {
    private static int a = 10;

    static {
        b = 1000;
    }

    @Override
    public void a() {
        System.out.println("son1.a");
    }

    public static void main(String[] args) {
        Super S = new Son1();
        S.a();
    }
}
