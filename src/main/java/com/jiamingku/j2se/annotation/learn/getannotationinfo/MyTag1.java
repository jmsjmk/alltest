package com.jiamingku.j2se.annotation.learn.getannotationinfo;

/**
 * Created by jiamingku on 2018/10/17.
 */
public @interface MyTag1 {

    String name();
    // 可以设置默认值
//    String name() default "hi";
    int age();

//    int age() default  1;
}
