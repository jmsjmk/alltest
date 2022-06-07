package com.jiamingku.j2se.numberprocess;

import org.junit.Test;

/**
 * Created by jiamingku on 2019/11/5.
 */
public class CharacterToInt {

    public static void main(String[] args) {
        /**
         * 将0-9 的字符转换成为int
         * 1.方法一 Character.getNumericValue(c) ----------这个方法知识将对应的数字字符转换成为对应代码的数字--------------------a-z A-z不会显示对应的 65了之类的
         * 2.方法二 Integer.parseInt(Character.toString(c)); 容易爆异常
         * 3.int i3 = (int)c - 48;    字符1 对应的unicode是49 --------同样数字9对应的就是57 所以减48 也能得到同样的效果。
         */

        /**
         * 字符转换成为整形,如果直接用系统的类型转换
         * int i = c 这样的方式得到的是这个unicode的值
         * 这样0-9的字符就变成了48-57
         *
         * 如果用Character.getNumericValue() 获取的字符a =10
         * a-z 10-35
         * A-Z 10-35
         * 0-9 0-9
         */
        char c = 'a';
        int ci = c;
        // 'a' 的unicode \u0061 ==97
        System.out.println("ci = " + ci);
        int ci1 = Character.getNumericValue(c);
        // 结果是10
        System.out.println("ci1 = " + ci1);

        char b = '3';
        int b1 = (int)b;
        // 结果是49
        System.out.println("b1 = " + b1);
        int bi1 = Character.getNumericValue(b);
        System.out.println("bi1 = " + bi1);
    }

    @Test
    public void test() {
        System.out.println("Character.getNumericValue('a'); = " + Character.getNumericValue('a'));
        System.out.println("Character.getNumericValue('A); = " + Character.getNumericValue('A'));
        System.out.println("Character.getNumericValue('1); = " + Character.getNumericValue('1'));
    }

    @Test
    public void test3() {
        char t = '1';
        int c = t - 48;
        System.out.println("c = " + c);
    }
}
