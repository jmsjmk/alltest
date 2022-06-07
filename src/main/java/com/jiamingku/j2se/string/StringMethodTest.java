package com.jiamingku.j2se.string;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * Created by jiamingku on 2019/4/11.
 */
public class StringMethodTest {

    public static void main(String[] args) {
        String A = "ABC";
        String t = "ABC";
        /**
         * 引用堆中的对象
         */
        String B = new String("ABC");
        System.out.println(A == t);
        System.out.println(A == B);
    }

    /**
     * 构建新对象.
     */
    @Test
    public void testReplace() {
        String s = "ab";
        String sb = "a1b1";
        String s1 = sb.replace("1", "");
        System.out.println("s1 = " + s1);
        s1 = sb.replaceFirst("1", "");
        System.out.println("s1 = " + s1);
        String s2 = sb.replaceAll("1", "");
        System.out.println("s2 = " + s2);
        System.out.println("s1 = " + (s1 == s));
    }
    // -----------------------------------------------------基本的方法

    /**
     * 测试方法a
     * <p>
     * String.valueof(Null)就报异常了方法重载
     */
    @Test
    public void testValueOf() {
        String test = null;
        /**
         * {@link String#valueOf(char[])} 所以抛出异常
         */
        // String s = String.valueOf(null);
        /**
         * java.lang.String#valueOf(java.lang.Object) 这个就不发生异常
         */
        String s1 = String.valueOf(test);

        System.out.println("s1.length() = " + s1.length());

        // Integer.valueOf(null);
        Object o = null;
        String s2 = String.valueOf(o);
        System.out.println(s2 + "\t s2.length: [" + s2.length() + "]");

    }

    /**
     * 方法重载很多，一般出现null的时候不会出现错误
     */
    @Test
    public void testIntegerValueOf() {
        Integer i = null;
        String istring = String.valueOf(i);
        System.out.println("s1.length() = " + istring);

        String s = Integer.toString(3);
        System.out.println("s = " + s);

        s = String.valueOf(3);
        System.out.println("s = " + s);
    }

    /**
     * 1.null.split("") 出现空指针异常
     * 2."".split() 长度1.
     * 3.字符串用 | 分割时候 "\\|" 来进行拆分
     * 4.拆分$有两种
     * 4.1.是 \\$  属于转义
     * 4.2.一种[$] 属于正则的方式
     */
    @Test
    public void testSplit() {
        // ------------如果没有拆分字符长度1，就是被拆分字符
        String a = "333333%333333";
        String[] array = a.split("u");
        System.out.println("array.length = " + array.length);
        for (String s : array) {
            System.out.println("s = " + s);
        }
        System.out.println("========================================== ");
        // ------------如果拆分字符在头 长度2,第一个长度0
        a = "u3333333333";
        array = a.split("u");
        System.out.println("array.length = " + array.length);
        for (String s : array) {
            System.out.println("s = " + s);
        }
        System.out.println("========================================== ");
        // ------------如果拆分字符在头 长度2,第一个长度0
        a = "3333333333u";
        array = a.split("u");
        System.out.println("array.length = " + array.length);
        for (String s : array) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void testSplit2() {
        String s1 = "/web/driverMessage/addMessageDat";
//		String[] excludedUrls = filterConfig.getInitParameter("excludedUrls");
        String[] excludedUrlArray = s1.split(",");
        Stream.of(excludedUrlArray).forEach(e -> System.out.println(e));
    }

    // index/index/index----------------------------------------------------------------------------------------------------------------------------

    @Test
    public void testIndex() {
        String a = "01234567890";
        /* 从头开始找*/
        int i1 = a.indexOf("0");
        System.out.println("i1 = " + i1);

        /* 从fromIndex位置开始找, 包含fromIndex里面的数据*/
        int i2 = a.indexOf("0", 0);
        System.out.println("i2 = " + i2);

        /* 从fromIndex位置开始找, 包含fromIndex里面的数据*/
        int i4 = a.indexOf("0", 1);
        System.out.println("i4 = " + i4);

        /* 默认从最后一个字符开始查找*/
        int i = a.lastIndexOf("0");
        System.out.println("i = " + i);

        /* 从指定的位置向前查找数据*/
        int i3 = a.lastIndexOf("0", 5);
        System.out.println("i3 = " + i3);
    }


    @Test
    public void testSubString() {
        String a = "0123456789";

        // ---截取时候包含启始位置
        char c = a.charAt(3);
        System.out.println("c = " + c);
        String s = a.substring(3);
        System.out.println("s = " + s);
        // ---不包含尾部的位置
        c = a.charAt(6);
        System.out.println("char[6] = " + c);
        System.out.println("a.substring(3,6) = " + a.substring(3, 6));
    }
}
