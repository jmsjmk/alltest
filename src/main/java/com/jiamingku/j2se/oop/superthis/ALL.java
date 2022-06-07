package com.jiamingku.j2se.oop.superthis;

import io.netty.channel.EventLoop;

public class ALL {

    private String age = "122";

    public ALL(String age) {
        this.age = age;
    }

    public ALL() {
        System.out.println("dd");
    }

    public void t() {
        System.out.println("查看this:" + this);
        f();
    }
    public void f() {
        System.out.println("all");
    }

    public static void main(String[] args) {
//        System.out.println("super = " + super);
//        super.super.
//        this.t();

        ALL all = new ALL();

        EventLoop  eventExecutors ;
    }
}
