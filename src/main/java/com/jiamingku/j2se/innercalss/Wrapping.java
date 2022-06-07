package com.jiamingku.j2se.innercalss;

/**
 * Created by jiamingku on 2017/7/8.
 */
public class Wrapping {
    public int value;

    public int getValue() {
        return value;
    }
    public int value() {
        System.out.println("dddd");
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }


    public Wrapping(int value) {
        this.value = value;
    }
}
