package com.jiamingku.j2se.annotation.learn.getannotationinfo;


import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;

/**
 * 1.最基本的获取注解的信息,获取注解的信息
 * <p>
 * 其实可以把注解理解成为元数据的的一种参数
 * <p>
 * Created by jiamingku on 2018/10/17.
 */
public class Test1 {

    @MyTag(name = "12", age = 3)
    public void info() {

    }

    @MyTag(name = "12", age = 3)
    public void info2() {

    }

    // 默认注解里面的属性必须唯一，并且 是value的方法名
//    @MyTag("12")
//    public void info1() {
//
//    }

    public static void main(String[] args) throws Exception {

        Constructor<?>[] declaredConstructors = Test1.class.getDeclaredConstructors();
        System.out.println("declaredConstructors = " + declaredConstructors.length);

    }
}
