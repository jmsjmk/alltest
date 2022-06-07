package com.jiamingku.fastjson.test.serializeable.bo;

import java.io.Serializable;

/**
 * 1.实现Serializable 否者异常 java.io.NotSerializableException
 * <p>
 * 2.如果不增加： private static final long serialVersionUID = 4125096758372084309L;
 * 2.1会导致你修改类之后，在反序列化就出现问题。例如get，set的方法的增加,属性增加
 * 2.2如果写了serialVersionUID 哪怕属性你删除了也没问题
 * =================================================================================
 * 如果有serialVersionUID的话, 属性减少,get set 方法删除都是没有问题的
 * =================================================================================
 * <p>
 * 3。总结：简单的理解：JAVA序列化的机制是通过判断类的serialVersionUID来验证的版本一致的。
 * 在进行反序列化时，JVM会把传来的字节流中的serialVersionUID于本地相应实体类的serialVersionUID进行比较。
 * 如果相同说明是一致的，可以进行反序列化，否则会出现反序列化版本一致的异常，即是InvalidCastException。
 */
public class User implements Serializable {

    private static final long serialVersionUID = 4125096738372084309L;

    private int age;
    private int num;
    private String name;

    public User(String name, int age, int num) {
        this.age = age;
        this.name = name;
        this.num = num;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("age=").append(age);
        sb.append(", name='").append(name).append('\'');
        sb.append(", num=").append(num);
        sb.append('}');
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}