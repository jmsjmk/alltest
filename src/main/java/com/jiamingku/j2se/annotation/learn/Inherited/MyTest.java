package com.jiamingku.j2se.annotation.learn.Inherited;


public class MyTest {
    // 修饰方法
    @Testable
    public void info() {
        System.out.println("info 修饰方法");
    }

//    @Testable // 修饰字段错误
//    private int  t;
}
