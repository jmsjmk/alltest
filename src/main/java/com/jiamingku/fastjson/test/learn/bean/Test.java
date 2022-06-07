package com.jiamingku.fastjson.test.learn.bean;

import com.alibaba.fastjson.JSON;

/**
 * Created by jiamingku on 2020/6/15.
 */
public class Test {

    private String name;
    private String age;

    public String getAge() {
        System.out.println("序列化get age");
        return age;
    }

    public void setAge(String age) {
        System.out.println("反序列化set age");
        this.age = age;
    }

    public String getName() {
        System.out.println("序列化get name");
        return name;
    }

    public void setName(String name) {
        System.out.println("反序列化set name");
        this.name = name;
    }

    public static void main(String[] args) {
        Test t = new Test();
        t.setName("name");
        t.setAge("age");

        String s = JSON.toJSONString(t);
        System.out.println("s = " + s);
        System.out.println(" ===== " );
        System.out.println(" ===== " );
        System.out.println(" ===== " );

        Test t1 = JSON.parseObject(s, Test.class);
    }
}
