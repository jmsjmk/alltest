package com.jiamingku.fastjson.test.learn.bean;

import com.alibaba.fastjson.JSON;


import java.beans.Transient;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiamingku on 2017/4/10.
 */
public class UserBean {
    private String name;
    private int age;
    private Date birth;

    private int weight;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        map.put("key", "value");
        map.put("key1", "");

        String s = JSON.toJSONString(map);
        System.out.println("s = " + s);
    }
}
