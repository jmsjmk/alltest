package com.jiamingku.j2se.string;

import org.junit.Test;

/**
 * Created by jiamingku on 16/12/12.
 */
public class StringTest {
    private static String s = "sdfdsfdf";

    private String s1;

    {
        s = "ddd";
    }

    /**
     * 编译之后s1=abc12345(s1,s2指向常量池中的数据)
     */
    @Test
    public void testIntern1() {
        String s1 = "ab" + "cd12345";
        String s2 = "abc" + "d12345";
        System.out.println("s1 == s2 = " + (s1 == s2));
        // ---返回常量池中的一个方法
        String s3 = new String("abcd12345").intern();
        System.out.println(s3 == s2); // 证明s1,s2,s3都是指向常量池

        System.out.println("--");
        System.out.println("--");
        System.out.println("--");

        String s4 = new String("abcd12345");
        System.out.println(s3 == s4);
    }

    @Test
    public void test2() {
        String s1 = "abc";
        String s2 = "abc";
        s1 = "hell0";
        System.out.println("(s1 ==s2) = " + (s1 == s2));
        System.out.println(s1);
        System.out.println(s2);
    }

    @Test
    public void test3() {
        String s1 = "abc";
        String s2 = "abc";
        s2 += "hell0";
        System.out.println("(s1 ==s2) = " + (s1 == s2));
        System.out.println(s1);
        System.out.println(s2);
    }

    @Test
    public void test4() {
        String s1 = "abc";
        String s2 = "abc";
        String s34 = "a" + "b" + "c";
        s2 += "hell0";
        System.out.println("(s1 ==s2) = " + (s1 == s2));

        new Thread(() -> {
            String s3 = "abc";
            System.out.println("(s3==s1) ===" + (s3 == s1));
        }).start();
        System.out.println(s1);
        System.out.println(s2);
    }

    @Test
    public void testConstantPool() {
        String a = "abc";
        String b = "abc";
        String nb = new String("abc");
        System.out.println("(a == b) = " + (a == b));
        System.out.println("(nb == a) = " + (nb == a));
        String nbi = new String("abc").intern();
        System.out.println("(nbi == b) = " + (nbi == b));
    }

    @Test
    public void stringImport() {
        String s = new String("11"); //编译时候进入常量池
        s.intern();
        String s2 = "11";
        System.out.println(s == s2); // false

        String s3 = new String("10") + new String("1");
        s3.intern();
        String s4 = "101";
        System.out.println(s3 == s4); // true

        System.out.println("============================================================ ");

        // 在堆中产生三个对象，string只指向合并后生成的那个"hello"对象；
        String string = new String("he") + new String("llo");
        // 在常量池中找，没有“hello”这个常量对象，所以生成常量引用，和堆中那个对象的地址相同
        string.intern();

        // 常量池中已经存在这个常量对象的引用，所以str2指向这个引用，所以str2也指向string对象
        String str2 = "hello";
        System.out.println(string == str2);    // >>>>>>>>   地址一样，内容一样，所以true

    }

    @Test
    public void ttt1() {
        // ==因为采用了+号导致在常量池中没有创建
        String s3 = new String("12345JMK") + new String("1");
        s3.intern();
        String s6 = "12345JMK1";
        System.out.println(s3 == s6);
    }

    @Test
    public void test1122() {
        String s2 = "2021-09-14 18:25:19.808 [pool-simple-buffer-trigger-thread-0] - Statistics from 2021-09-14 18:24:19 to 2021-09-14 18:25:19                                                             1.00     2.11     2.02     2.93";
        s2 = "Statistics from 2021-09-14 18:26:19 to 2021-09-14 18:27:19";
        System.out.println(s2.length());


    }

    //=========================================
    @Test
    public void testStringPool() {
        // 编码时候指定了在常量池中
        String str = new String("12345JMK1");
        String s2 = str.intern();
        String s1 = "12345JMK1";
        System.out.println(s1 == str);
        System.out.println(s1 == s2);
    }

    @Test
    public void testerer() {
        String a = "0123456789";
        int i = a.indexOf("1", 0);
        System.out.println("i = " + i);
        String b = a.substring(1);
        System.out.println("b = " + b);
        System.out.println("a.substring(1,2) = " + a.substring(1,2));
        System.out.println("a.substring(2,4) = " + a.substring(2,4));

        int i1 = a.indexOf("234");
        System.out.println("i1 = " + i1);


        StringBuilder stringBuilder = new StringBuilder();


        String s = stringBuilder.append(a.toCharArray(), 0, 2).toString();
        System.out.println("s = " + s);


        String ab = "calsspath*:" + 34;

        String substring = ab.substring("calsspath*:".length());
        System.out.println("substring = " + substring);


    }
    
    @Test
    public void tstttt() {
        String s = "dfsdfsdfsdfsdfsdfsdfsfsfs8f/";

        System.out.println("s.length() = " + s.length());

        char c = s.toCharArray()[27];
        System.out.println(c);


        int i = s.lastIndexOf('8', s.length()+100);
        System.out.println("i = " + i);

        String location = "sdfsfsdfsdfsdfsdfsdfsd/f/";

        String a = "01234";
        String substring = a.substring(0, 2);
        System.out.println("substring = " + substring);

        int lll = location.length();
        System.out.println("lll = " + lll);
        int i1 = location.lastIndexOf('/', lll - 2) + 1;
        System.out.println("i1 = " + i1);

        char c1 = location.toCharArray()[25];
        System.out.println("c1 = " + c1);


        String substring1 = location.substring(0, i1);
        System.out.println("substring1 = " + substring1);

    }
}
