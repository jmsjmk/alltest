package com.jiamingku.jvm.oomdemo;

/**
 * jdk1.8 静态属性，字符串常量池在堆区中
 */
public class RuntimeConstantPoolStringStaticOOM {
    private static byte[] arr = new byte[1024 * 1024 * 100];

    public static void main(String[] args) {
        System.out.println(RuntimeConstantPoolStringStaticOOM.arr);
        try {
            Thread.sleep(1000000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
