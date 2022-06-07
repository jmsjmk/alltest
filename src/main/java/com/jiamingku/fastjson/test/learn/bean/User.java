package com.jiamingku.fastjson.test.learn.bean;

import com.alibaba.fastjson.JSON;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by jiamingku on 2017/4/10.
 */
public class User {
    private String name;
    private int age;
    private Date birth;
    private Object objctNUll;
    private User u1;

    private HashMap hashMap;

    public HashMap getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap hashMap) {
        this.hashMap = hashMap;
    }

    public User getU1() {
        return u1;
    }

    public void setU1(User u1) {
        this.u1 = u1;
    }

    public Object getObjctNUll() {
        return objctNUll;
    }

    public void setObjctNUll(Object objctNUll) {
        this.objctNUll = objctNUll;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("age=").append(age);
        sb.append(", name='").append(name).append('\'');
        sb.append(", birth=").append(birth);
        sb.append(", objctNUll=").append(objctNUll);
        sb.append(", u1=").append(u1);
        sb.append(", hashMap=").append(hashMap);
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
        User u = new User();
        u.setAge(1);
        u.setName("30");
        u.setBirth(new Date());

        String str = JSON.toJSONString(u);
        System.out.println("str = " + str);

        String s = "{\"age\":1.332,\"birth\":1531723823518,\"name\":\"30\"}";

        User u1 = JSON.parseObject(s, User.class);

        System.out.println("u1 = " + u1);
    }
}
