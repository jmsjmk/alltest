package com.framework.springmvc.controller;

public enum TestEnum {
    A(1,2);
    private int a;
    private int b;

    TestEnum(int a, int b) {
        this.a = a;
        this.b = b;
    }
}
