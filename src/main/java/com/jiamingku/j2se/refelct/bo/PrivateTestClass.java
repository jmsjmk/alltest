package com.jiamingku.j2se.refelct.bo;

import java.lang.reflect.Field;

public class PrivateTestClass {

    private String name = "name";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws Exception {

        Class p = PrivateTestClass.class;

        PrivateTestClass privateTestClass = new PrivateTestClass();

        Field field = p.getDeclaredField("name");

        field.setAccessible(true);

        field.set(privateTestClass, "333333");

        System.out.println(privateTestClass.getName());
    }
}
