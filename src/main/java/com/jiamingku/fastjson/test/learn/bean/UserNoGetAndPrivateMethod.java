package com.jiamingku.fastjson.test.learn.bean;

import java.util.Date;

/**
 * fastJson的序列化与java的序列化区别很大
 * <p>
 * 1.会调用get方法,并且key是get方法的驼峰名字(不在乎属性是否存在)
 * 2.私有的get方法不会被调用,也就是不会出现序列化信息
 * <p>
 * <p>
 * Created by jiamingku on 2017/4/10.
 */
public class UserNoGetAndPrivateMethod {
    public UserNoGetAndPrivateMethod(int age) {
        this.age = age;
    }

    public UserNoGetAndPrivateMethod() {
    }

    private String name;
    private int age;
    private Date birth;
    private String Tttt;
    private String t;
    private String privateGetMethod = "privateMethod";

    private String getPrivateGetMethod() {
        return privateGetMethod;
    }

    public void setPrivateGetMethod(String privateGetMethod) {
        this.privateGetMethod = privateGetMethod;
    }

    public String getT() {
        System.out.println(" json 序列化时候-----属性的get方法----会被调用 ");
        return t;
    }

    public void setT(String t) {
        System.out.println(" 反序列化的时候会调用用setT-------------------- ");
        this.t = t;
    }

//    public String getT() {
//        System.out.println(" json 序列化时候-----属性的get方法----会被调用 ");
//        return Tttt;
//    }
//
//
//    public void setT(String t) {
//        System.out.println(" 反序列化的时候会调用用-------------------- ");
//        this.Tttt = t;
//    }

    public String getWhatProblem() {
        return "nx";
    }

    public int getAge() {
        return age;
    }

    public void setTttt(String tttt) {
        System.out.println(" 反序列化的时候会调用setTTT ");
        Tttt = tttt;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserNoGetAndPrivateMethod{");
        sb.append("age=").append(age);
        sb.append(", name='").append(name).append('\'');
        sb.append(", birth=").append(birth);
        sb.append(", Tttt='").append(Tttt).append('\'');
        sb.append(", t='").append(t).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /*
        序列化需要get方法,如果toSring时候没有get是不会打印的

        public int getAge() {
            return age;
        }
        */
    public void setAge(int age) {
        System.out.println(" ------------------ ");
        this.age = age;
    }

    /**
     * 在进行序列化的时候-会调用get方法--私有的方法 导致没办法调用get
     *
     * @return
     */
    private Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

//    public String getNameWhat() {
//        return name;
//    }

    public void setName(String name) {
        this.name = name;
    }


}
