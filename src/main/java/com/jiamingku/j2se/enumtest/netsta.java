package com.jiamingku.j2se.enumtest;

/**
 * Created by jiamingku on 2018/6/12.
 */
public enum netsta {

    aa(1, EnumValue.Abss),
    bb(2, EnumValue.BB);

    private int a;
    private EnumValue enumTest;

    netsta(int a, EnumValue enumTest) {
        this.a = a;
        this.enumTest = enumTest;
    }
}
