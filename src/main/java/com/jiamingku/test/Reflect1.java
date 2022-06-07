package com.jiamingku.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by jiamingku on 16/6/30.
 */
public class Reflect1 {
    public static void main(String[] args) {
//        Reflect1 r = new Reflect1();
//        System.out.println(r.getClass().getSimpleName());
//        System.out.println(r.getClass().getName());
//
//        System.out.println(System.getProperty("file.encoding"));
//
//        Properties pps=System.getProperties();
//        pps.list(System.out);
//        System.out.println("--------------------以上为JVM的所有属性值-------------");
//        System.out.print("系统默认的字符集为：");
//        String name=System.getProperty("sun.jnu.encoding");
//        System.out.print(name);



        ArrayList<String> strings1 = new ArrayList<>();
        strings1.add("1");
        strings1.add("1");
        strings1.add("1");


        Object[] o1 = strings1.toArray();
        o1[2] = "100";

        List<String> list = strings1.subList(1,2);
        list.add("333");
        list.add("333");

        for (String ssss : list) {
            System.out.println(ssss);
        }
        System.out.println("-------------------");
        for (String s : strings1) {
            System.out.println(s);
        }



    }
}
