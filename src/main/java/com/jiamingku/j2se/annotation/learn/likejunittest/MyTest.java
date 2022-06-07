package com.jiamingku.j2se.annotation.learn.likejunittest;

/**
 * Created by jiamingku on 2018/10/17.
 */
public class MyTest {

    // 使用@Testable注解指定该方法是可测试的
    @Testable
    public static void m1() {
    }

    // 静态方法创建时候==method.vnovke(对象）对象必须存在
    @Testable
    public void m123() {
    }

    public static void m2() {
    }

    // 使用@Testable注解指定该方法是可测试的
    @Testable
    public static void m3() {
        throw new IllegalArgumentException("参数出错了！");
    }

    public static void m4() {
    }

    // 使用@Testable注解指定该方法是可测试的
    @Testable
    public static void m5() {
    }

    public static void m6() {
    }

    // 使用@Testable注解指定该方法是可测试的
    @Testable
    public static void m7() {
        throw new RuntimeException("程序业务出现异常！");
    }

    public static void m8() {
    }
}
