package com.jiamingku.lambda.sgg.com.atguigu.java8;

public interface MyFun1 {

    int getValue(int i);

    default String getName() {
        return "哈哈哈";
    }

    default void a() {
        System.out.println("true = " + true);
    }
    static void ccc() {
        System.out.println("true = " + true);
    }
}
