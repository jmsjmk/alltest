package com.jiamingku.network.learn;

public class Student {
    private String age;
    private String name;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {

        Student[] s = new Student[10];

        s[0] = new Student();
        s[1] = new Student();

        System.out.println("s = " + s);
    }
}
