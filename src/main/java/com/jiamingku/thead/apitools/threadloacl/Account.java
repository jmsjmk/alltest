package com.jiamingku.thead.apitools.threadloacl;

/**
 * Created by jiamingku on 16/9/19.
 */
public class Account {
    public ThreadLocal<String> name = new ThreadLocal<>();

    public ThreadLocal<Student> stu = new ThreadLocal<>();


    public Student getStu() {
        return stu.get();
    }

    public void setStu(Student stu) {
        this.stu.set(stu);
    }

    public String getName() {
        return name.get();
    }

    public Account(String name) {
        this.name.set(name);
    }

    public void setName(String name) {
        this.name.set(name);
    }
}

