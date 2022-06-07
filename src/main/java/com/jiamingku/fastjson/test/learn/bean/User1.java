package com.jiamingku.fastjson.test.learn.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.*;

/**
 * Created by jiamingku on 2017/4/10.
 */
public class User1 {
    private String name;
    private int age;
    private Date birth;
    Map<String, String> maps = new HashMap<>();

    public Map<String, String> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("age=").append(age);
        sb.append(", name='").append(name).append('\'');
        sb.append(", birth=").append(birth);
        sb.append('}');
        return sb.toString();
    }

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

    public static void main(String[] args) {
        User1 u = new User1();
        u.setAge(1);
        u.setName("30");
        u.setBirth(new Date());

        List<User1> lists = new ArrayList<>();
        lists.add(u);
        lists.add(u);
        lists.add(u);
        lists.add(u);

        System.out.println("JSON.toJSONString(lists, true) = " + JSON.toJSONString(lists, true));

        System.out.println(JSON.toJSONString(lists, SerializerFeature.DisableCircularReferenceDetect));
    }
}
