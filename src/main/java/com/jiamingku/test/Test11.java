package com.jiamingku.test;

public class Test11 {
    public static void main(String args[]) {



        System.out.println("This is " + getLineInfo());

        System.out.println(Test1.class);

        System.out.println("Test1.class.getName() = " + Test1.class.getName());
    }

    public static String getLineInfo() {
        //StackTraceElement[] array = new Throwable().getStackTrace();
        StackTraceElement[] array = Thread.currentThread().getStackTrace();
        System.out.println(array.length);

        StackTraceElement ste = array[1];

        System.out.println("0:" + array[0].getLineNumber() + "\t" +
        array[0].getClass() + "\t" + array[0].getClassName());


        System.out.println("1:" + array[1].getLineNumber() + "\t" +
                array[1].getClass() + "\t" + array[1].getClassName());


        System.out.println("2:" + array[2].getLineNumber() + "\t" +
                array[2].getClass() + "\t" + array[2].getClassName());

        return ste.getFileName() + ": Line " + ste.getLineNumber();
    }
}