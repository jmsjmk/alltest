package com.jiamingku.j2se.refelct.proxy.cglib;

/**
 * https://blog.csdn.net/danchu/article/details/70238002
 */
public class SampleBean {
    private String value;

    public SampleBean() {
    }

    public SampleBean(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
