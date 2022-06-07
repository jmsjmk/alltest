package com.jiamingku.j2se.annotation.learn.getannotationinfo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by jiamingku on 2018/10/17.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTag {

    String name();
    // 可以设置默认值
    //    String name() default "hi";
    int age();

//    String value();

//    int age() default  1;
}
