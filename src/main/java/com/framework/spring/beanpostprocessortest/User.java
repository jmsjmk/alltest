package com.framework.spring.beanpostprocessortest;

import com.google.common.base.Objects;

public class User {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equal(name, user.name) && Objects.equal(beanName, user.beanName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, beanName);
    }

    private int id;

    private String name;

    private String beanName;

    public User(){
        System.out.println("User 被实例化");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("设置："+name);
        this.name = name;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
    /**
     * 自定义的初始化方法
     */
    public void start(){
        System.out.println("User 中自定义的初始化方法");
    }
}