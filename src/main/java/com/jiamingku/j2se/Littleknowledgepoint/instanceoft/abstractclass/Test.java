package com.jiamingku.j2se.Littleknowledgepoint.instanceoft.abstractclass;

/**
 * Created by jiamingku on 2018/9/9.
 */
public class Test {
    public static void main(String[] args) {

        MaxInterface2 p = new InterfaceImpl();

        if (p instanceof AbsClass) {
            System.out.println("is Abstract");
        }

        System.out.println(Test.class.getName());

        if (p instanceof MaxInterface2) {
            System.out.println("is MaxInterface2");
        }


        if (p instanceof MaxInterface1) {
            System.out.println("is MaxInterface1");
        }
    }
}
