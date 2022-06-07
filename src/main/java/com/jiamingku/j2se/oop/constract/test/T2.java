package com.jiamingku.j2se.oop.constract.test;

import com.jiamingku.j2se.oop.constract.Parent;
import com.jiamingku.j2se.oop.constract.Son;

/**
 * Created by jiamingku on 2020/3/24.
 */
public class T2 {

    public static void main(String[] args) {
        Parent parent = new Son();

        Parent p1 = (Parent) parent;
        System.out.println("p1 = " + p1);
    }
}
