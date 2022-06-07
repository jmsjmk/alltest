package com.jiamingku.j2se.remove;

import org.junit.Test;

import java.math.BigInteger;

public class Shift {

    /**
     * +数是没问题的
     */
    @Test
    public void test() {
        int a = 13232;
        System.out.println("Integer.toBinaryString(a) = " + Integer.toBinaryString(a));
        String s = Integer.toBinaryString(a);
        int av = Integer.parseInt(s, 2);
        System.out.println("av = " + av);

        float atest = 10000L;
        byte b =1;
    }

    /**
     * hashmap1。7源代码获取最高位的位全力
     *
     * @param i
     * @return
     */
    public static int highestOneBit(int i) {
        // HD, Figure 3-1
        i |= (i >> 1);
        i |= (i >> 2);
        i |= (i >> 4);
        i |= (i >> 8);
        i |= (i >> 16);
        return i - (i >>> 1);
    }

    public static int lowestOneBit(int i) {
        // HD, Section 2-1
        return i & -i;
    }

    @Test
    public void a() {
        int a = highestOneBit(14 << 1);
        System.out.println("a = " + a);

        a = highestOneBit(1);
        System.out.println("a = " + a);

        a = highestOneBit(2);
        System.out.println("a = " + a);


        System.out.println(lowestOneBit(1));


//        System.out.println("Integer.MAXIMUM_CAPACITY = " + Integer.MAXIMUM_CAPACITY);
        System.out.println("(1<< 30) = " + (1 << 30));
        int aa = 1 << 30;
        System.out.println("Integer.toBinaryString(aa) = " + Integer.toBinaryString(aa));
        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
    }

    /**
     * 复数就会出现问题 ,对应的解决办法有两种如下
     */
    @Test
    public void test1() {
        try {
            int a = -1;
            System.out.println("Integer.toBinaryString(a) = " + Integer.toBinaryString(a));
            String s = Integer.toBinaryString(a);
            int av1 = Integer.parseInt(s, 2);
            System.out.println("av1 = " + av1);
        } catch (Exception e) {
            // 复数肯定会出现问题
            System.out.println(" = ");
        }
        // ----------------------------------------------------------
        // 解决方法1
        int b = -1;
        String binString;
        if (b < 0) {
            binString = "-" + Integer.toBinaryString(-b);
        } else {
            binString = Integer.toBinaryString(b);
        }
        int av1 = Integer.parseInt(binString, 2);
        System.out.println("av1 = " + av1);

        // ---------------------------------------------------------
        int b3 = -1;
        String binString1;
        binString1 = Integer.toBinaryString(b3);
        BigInteger b31 = new BigInteger(binString1, 2);
        System.out.println("b31 = " + b31);

    }

    @Test
    public void test3() {
        int a = ~(-1 << 12);
        System.out.println("a = " + a);
    }

    public static void main(String[] args) {

        int i = -1;
        System.out.println(Integer.toBinaryString(i));

        long ii = -1;
        System.out.println("Long.toBinaryString(ii) = " + Long.toBinaryString(ii));
        System.out.println("Integer.toBinaryString(1) = " + Integer.toBinaryString(100));
        i = i >>> 10;
        System.out.println(i + ":");
        System.out.println(Integer.toBinaryString(i));
        System.out.println("*************注意：没有<<<符号**************");

        System.out.println("**********byte类型移位时要强转换*************");
        byte k = 10;
        System.out.println(Integer.toBinaryString(k));
        k = (byte) ((byte) k >>> 2);
        System.out.println(Integer.toBinaryString(k));
        // 第一个是操作数
        System.out.println(1 << 3);


        System.out.println(0b11);
        System.out.println(0B11);
        System.out.println(0B11L);

        byte b = 1;

        int c = b << 2;

        System.out.println("c = " + c);
        System.out.println("============负数变化正数的例子=============");
        System.out.println("***********负数的右移操作高位补1**************");
        System.out.println(Integer.toBinaryString(-7));
        i = -7;
        i = i << 30;
        System.out.println(i + ":");
        System.out.println(Integer.toBinaryString(i));
        System.out.println(Integer.toBinaryString(i));
    }
}