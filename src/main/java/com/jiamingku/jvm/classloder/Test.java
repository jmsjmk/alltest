package com.jiamingku.jvm.classloder;

import com.google.common.base.Strings;
import lombok.val;
import sun.misc.SharedSecrets;
import sun.misc.URLClassPath;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URLClassLoader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 此类就相当于一个bo,为了说明例子的demo
 */
public class Test extends Test1 {
    public static void main(String[] args) {

        final URLClassPath ucp = SharedSecrets.getJavaNetAccess().getURLClassPath((URLClassLoader) (ClassLoader.getSystemClassLoader()));

        System.out.println("this is main method from Test");

        Constructor<?>[] constructors = Test.class.getConstructors();
        System.out.println(constructors.length);

        int b = Test.class.getDeclaredConstructors().length;
        System.out.println(b);

        int a = Test.class.getMethods().length;
        System.out.println(a);


//        isAnnotationPresent

        Properties properties = new Properties();
        properties.put("key1", "v1");


        try {
            properties.load(new FileInputStream("/Users/jiamingku/test/2.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        Enumeration enum1 = properties.propertyNames();//得到配置文件的名字

        while (enum1.hasMoreElements()) {
            String strKey = (String) enum1.nextElement();
            String strValue = properties.getProperty(strKey);
            System.out.println(strKey + "=" + strValue);
        }


    }


    @org.junit.Test
    public void tst1() {

        List<Integer> lists = new ArrayList<>();
        lists.add(100);
        lists.add(100);

        List subLists = lists.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(() ->
                        new TreeSet<>(Comparator.comparing(o -> Integer.valueOf(1)))), ArrayList::new));


        System.out.println("subLists = " + subLists);


    }

}