package com.jiamingku.j2se.refelct.bo;

import com.jiamingku.j2se.refelct.InstanceofAndisInstanceTest;

/**
 * Created by jiamingku on 2018/9/10.
 */
public class SubTest extends InstanceofAndisInstanceTest {

    public SubTest() {
    }

    public static void main(String[] args) {
        SubTest subTest = new SubTest();
        subTest.a();
    }

    public void a() {
        String t = this + "dd";
        System.out.println("t = " + t);
    }
}
