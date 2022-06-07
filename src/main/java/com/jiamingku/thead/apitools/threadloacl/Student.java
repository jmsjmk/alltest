package com.jiamingku.thead.apitools.threadloacl;

/**
 * Created by jiamingku on 16/9/19.
 */
public class Student {
    private String age;
    private String name;

    public String getAge() {
        return age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age='" + age + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
