package com.jiamingku.j2se.basetype;

import org.junit.Test;

/**
 * Created by jiamingku on 2018/11/23.
 */
public class IntRange {
    public static void main(String[] args) {
        // 100000000 代表负数的最大
        System.out.println("-------------------------------");
        int aa = 2147483647;
        int bb = aa + 1;
        System.out.println("result: ==== " + bb); // -2147483648
        int a = -2147483648;
        int b = -2147483648 - 1;
        System.out.println("result: =====" + b);//2147483647

        // 将int 直接转换成为float类型
        float f = 12;
        double d23 = 110030323323L;
        long l = 32;
        Long ll = 1L;
        int bb1 = 0B11;
        System.out.println(bb1);

        System.out.println("l = " + l);
        byte bbbbb = 23;
    }


    /**
     * 整形：与整形兼容性相关的类型，默认的计算类型就是整形，byte ，short 都会转换成为整形在计算
     * java的数据类型默认的是整型的，而且是 10进制的 定义变量c=010 是8 进制的10，但是我们打印的时候他会按照 10进制给你转换下变成了我们 10进制的8.
     * 计算的时候按照int类型计算
     * byte a = 10;
     * byte b = 20;
     * byte c = a + b;// error 运算时候是int没办法变化成为byte
     * <p>
     * byte x =(byte) (a + b); -----只能定义成为如下这个样子
     */
    @Test
    public void test1() {
        int cc = 0B10;
        System.out.println("cc = " + cc);
        int c = 010;
        System.out.println("c = " + c);
        byte a = 10;
        byte b = 20;
        byte x = (byte) (a + b);
        System.out.println("x = " + x);
        int t = 100;

        int bbbbb = 127;
        byte t1 = (byte) 129;
        System.out.println("t1 = " + t1);
        System.out.println(Integer.toBinaryString(129));
    }

    /**
     * 基本类型的转型-就是截断.
     */
    @Test
    public void test2() {
        int a = 128;
        System.out.println(Integer.toBinaryString(a) + ", a = " + a);
        byte b = (byte) a;
        System.out.println(Integer.toBinaryString(b) + ", b = " + b);


        b = -3;

        int c = b;
        System.out.println("c = " + c + "  ..."  + Integer.toBinaryString(c));

        char cc = '\u0000';
        System.out.println("cc = " + cc);
    }

}
