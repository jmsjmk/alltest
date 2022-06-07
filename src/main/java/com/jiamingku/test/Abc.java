package com.jiamingku.test;

import java.beans.Introspector;

public class Abc <T> {

    public static void main(String[] args) {
        String a= "123/456";
        int i = a.lastIndexOf("/", 100);
        System.out.println(i);


        String testASSSAD = Introspector.decapitalize("TestBBBBB");
        System.out.println("testASSSAD = " + testASSSAD);


    }
}
