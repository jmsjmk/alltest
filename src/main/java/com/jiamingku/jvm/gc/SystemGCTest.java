package com.jiamingku.jvm.gc;

public class SystemGCTest {
    public static void main(String[] args) {
        new SystemGCTest();

        // Runtime.getRuntime().gc(); =  System.gc();--不定义垃圾回收被调用
         System.gc();

        // 强制垃圾回收
//        System.runFinalization();
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("----------------");

    }
}
