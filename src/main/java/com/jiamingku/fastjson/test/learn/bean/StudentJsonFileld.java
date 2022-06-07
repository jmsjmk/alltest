package com.jiamingku.fastjson.test.learn.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by jiamingku on 2017/4/11.
 */
public class StudentJsonFileld {

    private String name;

    private int age;

    @JSONField(name="ts")
    List<User> users;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
