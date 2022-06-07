package com.jiamingku.j2se.oop;

/**
 * Created by jiamingku on 16/12/5.
 */
public class Test1 {
    /**
     * 字节 变化成了 无符号的 整数 --&OXff
     *
     * @param args
     */
    public static void main(String[] args) {
        byte[] a = new byte[10];
        a[0]= -127;
        System.out.println(a[0]);
        int c = a[0]&0xff;
        System.out.println(c);

        int c1 =  a[0];
        System.out.println(c1);
    }
}
