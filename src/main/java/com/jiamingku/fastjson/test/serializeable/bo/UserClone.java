package com.jiamingku.fastjson.test.serializeable.bo;

import java.io.Serializable;

/**
 * clone user
 * java --的序列化是不能 跨类型的， fastJson 是可以的
 */
public class UserClone implements Serializable {

    private static final long serialVersionUID = 4125096758372084309L;
    private String name;
    //    private int age;
    private int num;

//    public User(String name, int age, int num) {
////        this.age = age;
//        this.name = name;
//        this.num = num;
//    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
//        sb.append("age=").append(age);
        sb.append(", name='").append(name).append('\'');
        sb.append(", num=").append(num);
        sb.append('}');
        return sb.toString();
    }

//    public int getNum() {
//        return num;
//    }
//
//    public void setNum(int num) {
//        this.num = num;
//    }

//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
}