package com.jiamingku.j2se.annotation;

import java.lang.annotation.*;

/**
 * getDeclaredAnnotation <p/>
 * getAnnotation <p/>
 * 两种区别在于 @Inherited 是否可以
 */
public class Test2 {

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    static @interface W{

    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    static @interface W1{

    }

    @W
    @W1
    static class A{

    }

    static class B extends A{

    }

    public static void main(String[] args) {

        //return inherited annotation
        System.out.println(B.class.getAnnotation(W.class));
        System.out.println(B.class.getDeclaredAnnotation(W.class));


        Annotation[] annotations = B.class.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("annotation = " + annotation);
        }
    }

}
