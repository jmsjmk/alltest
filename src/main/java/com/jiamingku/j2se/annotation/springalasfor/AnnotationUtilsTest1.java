package com.jiamingku.j2se.annotation.springalasfor;

import org.junit.Test;

/**
 * 注解可以理解为参数,那如何获取参数呢,这就是反射的知识点
 * 1.反射的第一个知识点类，获取他他的注解信息
 * 2.如果没有注解信息返回什么信息？
 */
//@MyAnnotation(value = "aa", alias = "bb")
public class AnnotationUtilsTest1 {

    @Test
    public void testAliasfor2() {
        MyAnnotation ann = getClass().getAnnotation(MyAnnotation.class);
        if (ann == null) {
            System.out.println("类没有注解信息......");
            return;
        }
        System.out.println(ann.value());
        System.out.println(ann.alias());
    }
}
