package com.jiamingku.fastjson.test.learn.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by jiamingku on 2017/4/11.
 */
public class Student {

    @JSONField(name = "ts")
    List<User> users;
    @JSONField(serialize = false)
    private String name;
    private int age;
    private String noSet;

    public Student() {
        System.out.println(" constructor invoke ..");
    }


    public Student(String noSet) {
        this.noSet = noSet;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        System.out.println(" age ");
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Student{");
        sb.append("age=").append(age);
        sb.append(", name='").append(name).append('\'');
        sb.append(", noSet='").append(noSet).append('\'');
        sb.append(", users=").append(users);
        sb.append('}');
        return sb.toString();
    }
}
