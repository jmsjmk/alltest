package com.jiamingku.jvm.runtime;

import org.openjdk.jol.info.ClassLayout;

/**
 * 对象的hashcode在调用hashcode函数时候才会设置
 * 并且设置值的时候低位在前.
 * // -XX:+UseCompressedOops           默认开启的压缩所有指针
 * // -XX:+UseCompressedClassPointers  默认开启的压缩对象头里的类型指针Klass Pointer
 */
public class ObjectHeaderTest {

    public static void main(String[] args) {
        PaddedAtomicLong a = new PaddedAtomicLong();
        ClassLayout layout = ClassLayout.parseInstance(a);
        System.out.println(layout.toPrintable());
        int a1 = a.hashCode();
        System.out.println(layout.toPrintable());
        // 00000001 01111100 11110001 01010011 00000101 00000000 00000000 00000000
        // 1000010111010011001111001001010
        /**
         * 00000101
         * 01010011
         * 11110001
         * 01111100 ---先放这个 --一次向上
         */
        String sss = layout.toString();
    }


    // Oops : Ordinary Object Pointers
    public static class ArtisanTest {
        //8B mark word
        //4B Klass Pointer   如果关闭压缩-XX:-UseCompressedClassPointers或-XX:-UseCompressedOops，则占用8B
        int id;        //4B
        String name;   //4B  如果关闭压缩-XX:-UseCompressedOops，则占用8B
        byte b;        //1B
        Object o;      //4B  如果关闭压缩-XX:-UseCompressedOops，则占用8B
    }
}

