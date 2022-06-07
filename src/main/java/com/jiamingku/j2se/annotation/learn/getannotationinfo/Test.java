package com.jiamingku.j2se.annotation.learn.getannotationinfo;


import java.lang.annotation.Annotation;

/**
 * 1.最基本的获取注解的信息,获取注解的信息
 *
 * 其实可以把注解理解成为元数据的的一种参数
 *
 * Created by jiamingku on 2018/10/17.
 */
public class Test {

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
        System.out.println("Test.class = " + Test.class.getName());
        Annotation[] annotations = Class.forName(Test.class.getName()).getDeclaredMethod("info").getAnnotations();

        System.out.println("annotations = " + annotations.length);

        for (Annotation annotation : annotations) {
            System.out.println("annotation = " + annotation);
            if (annotation instanceof MyTag) {
                System.out.println(" this is My tag...");
                MyTag myTag = (MyTag) annotation;
                String name = myTag.name();
                System.out.println(name);
            }
        }
    }
}
