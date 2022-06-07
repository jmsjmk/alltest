package com.jiamingku.j2se.methodoverrite;

public class StaticInitializer {
    static int i = 1;
    static int j = getI();


    static int getI () {
        System.out.println("is excute.");
        return i;
    }

    public static void main(String[] args) {
        System.out.println(StaticInitializer.j);
    }
}  
