package com.jiamingku.fastjson.test.serializeable.bo;

import java.io.Serializable;

/**
     clone user
     java --的序列化是不能 跨类型的， fastJson是可以的
 */
public class UserCloneNoGetSetConstractor implements Serializable {

    private String name;
    private int age;

    public UserCloneNoGetSetConstractor(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserCloneNoGetSetConstractor{");
        sb.append("age=").append(age);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}