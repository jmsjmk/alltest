package com.jiamingku.jvm.runtime;

public class RuntimeTest {


    public static void main(String[] args) {
        long initMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        System.out.println("-Xms :" + initMemory + "M");
        System.out.println("-Xmm :" + maxMemory + "M");
    }
}
