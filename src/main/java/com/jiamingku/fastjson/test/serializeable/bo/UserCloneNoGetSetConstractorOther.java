package com.jiamingku.fastjson.test.serializeable.bo;

import java.io.Serializable;

/**
 * clone user
 * java --的序列化是不能跨类型的， fastJson是可以的
 */
public class UserCloneNoGetSetConstractorOther implements Serializable {
//    private static final long serialVersionUID = 4125096738372084309L;

    private String name;
    private int age;
//
//
//    private String kkkk;

    private String kk1;

//    private String k333;

    public String getKk1() {
        return kk1;
    }

    public void setKk1(String kk1) {
        this.kk1 = kk1;
    }

//    public static long getSerialVersionUID() {
//        return serialVersionUID;
//    }

//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//
//    public String getKkkk() {
//        return kkkk;
//    }
//
//    public void setKkkk(String kkkk) {
//        this.kkkk = kkkk;
//    }

    public UserCloneNoGetSetConstractorOther(int age, String name) {
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