package com.jiamingku.jvm.classloder;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.lang.reflect.Constructor;

/**
 * 此类就相当于一个bo,为了说明例子的demo
 */
public class Test1 {

    private String age;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Test1() {
    }

    public Test1(String age) {
        this.age = age;
    }

    public Test1(String age, String name) {
        this.age = age;
        this.name = name;
    }



    public void fun() {
        System.out.println("this is fun method from Test");
    }

    public void say() {
        System.out.println("Say Hello");
    }

    private static PathMatcher pathMatcher = new AntPathMatcher();


    public static void main(String[] args) {
        String s = "classpath*:/abc/deb/*123456/333.xml";
        String s1 = determineRootDir(s);
        System.out.println("s1 = " + s1);
    }

    public static String determineRootDir(String location) {
        int prefixEnd = location.indexOf(':') + 1;
        int rootDirEnd = location.length();
        while (rootDirEnd > prefixEnd && pathMatcher.isPattern(location.substring(prefixEnd, rootDirEnd))) {
            rootDirEnd = location.lastIndexOf('/', rootDirEnd - 2) + 1;
        }
        if (rootDirEnd == 0) {
            rootDirEnd = prefixEnd;
        }
        return location.substring(0, rootDirEnd);
    }
}