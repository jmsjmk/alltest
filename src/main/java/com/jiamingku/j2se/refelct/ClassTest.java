package com.jiamingku.j2se.refelct;

import java.lang.reflect.Constructor;
import java.util.Arrays;

/**
 * Created by jiamingku on 2018/9/10.
 */

public class ClassTest {

    /* 私有构造器*/
    private ClassTest() {
    }

    /* 公有的有参数构造器*/
    public ClassTest(String name) {
        System.out.println("有参数的构造器!");
    }

    public void info(String str) {
        System.out.println(" info 方法执行 -str = " + str);
    }

    private void test(String test) {
    }

    class Inner {
    }

    public static void main(String[] args) {
        Class<ClassTest> classTestClass = ClassTest.class;
        System.out.println("classTestClass = " + classTestClass);
        System.out.println("classTestClass.getTypeName() = " + classTestClass.getTypeName());
        System.out.println("classTestClass.getName() = " + classTestClass.getName());
        System.out.println("classTestClass.getSimpleName() = " + classTestClass.getSimpleName());

        System.out.println("---------------------------------------------------" );

        Constructor<?>[] declaredConstructors = classTestClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println("declaredConstructor = " + declaredConstructor);
        }
        System.out.println(" === ");

        Arrays.stream(classTestClass.getConstructors()).forEach(System.out::println);

        System.out.println(" === ");

        Arrays.stream(classTestClass.getMethods()).forEach(System.out::println);
        System.out.println(" === ");

        Arrays.stream(classTestClass.getDeclaredMethods()).forEach(System.out::println);

        System.out.println(" 重点::=== ");

        // ------重点方法::获取所有的内部类:
        Arrays.stream(classTestClass.getDeclaredClasses()).forEach(System.out::println);

        System.out.println("--------------------");

        try {
            // 内部类可以获取, 可以获取获取定义的外部类.
            Class<?> aClass = Class.forName("com.jiamingku.j2se.refelct.ClassTest$Inner");
            Class<?> declaringClass = aClass.getDeclaringClass();
            System.out.println("declaringClass = " + declaringClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // --------------------------------------------------------------------------------------------------------------------
    // 如何获取一个Class对象.
    /**
     * {@link GetClass}
     */

    // --------------------------------------------------------------------------------------------------------------------------
}
