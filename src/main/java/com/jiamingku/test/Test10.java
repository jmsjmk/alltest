package com.jiamingku.test;

public class Test10 {
    public static void main(String args[]) {



        System.out.println("This is " + getLineInfo());

//        System.out.println(Test1.class);
    }

    public static String getLineInfo() {
        StackTraceElement[] array = new Throwable().getStackTrace();

        System.out.println(array.length);
        StackTraceElement ste = array[1];

        System.out.println("0:" + array[0].getLineNumber());
        return ste.getFileName() + ": Line " + ste.getLineNumber();
    }
}