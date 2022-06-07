package com.jiamingku.j2se.annotation.learn.v2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义注解的时候,访问修饰符可以写也可以不写,
 * <p>
 * 并且还是设置默认值得
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {
    public int id();

    String description() default "no description";
}