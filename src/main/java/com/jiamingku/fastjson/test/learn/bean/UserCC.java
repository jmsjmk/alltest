package com.jiamingku.fastjson.test.learn.bean;

import com.alibaba.fastjson.JSON;

import java.util.Date;

/**
 * Created by jiamingku on 2017/4/10.
 */
public class UserCC {
    private String name;
    private int age;
    private Date birth;


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
        UserCC u = new UserCC();
        u.setAge(1);
        u.setName("30");
        u.setBirth(new Date());

        String str = JSON.toJSONString(u);
        System.out.println("str = " + str);

        String s = "{\"age\":1.332,\"birth\":1531723823518,\"name\":\"30\"}";

        UserCC u1 = JSON.parseObject(s, UserCC.class);

        System.out.println("u1 = " + u1);
    }
}
