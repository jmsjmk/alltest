package com.jiamingku.web.urldecode;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by jiamingku on 16/8/1.
 */
public class UrlEncodeDecode {
    public static void main(String[] args) {
        try {
            String a = "中国";

            byte[] b = a.getBytes("utf-8");

            for (int i = 0; i < b.length; i++) {
                System.out.println(b[i]);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        boolean b = Character.isLetter('A');
        System.out.println(b);

        final int caseDiff = ('a' - 'A');
        System.out.println(caseDiff);

        char t = 'A';
        System.out.println((int) t);
        int t1 = (t - 32);
        System.out.println(t1);

        System.out.println("==================================");
        char z = 'z';

        char A = 'A';

        System.out.println(Integer.toBinaryString(z));
        System.out.println(Integer.toBinaryString(A));


        byte aaa = -1;
        System.out.println(Integer.toBinaryString(aaa));
        char ch = Character.forDigit((aaa >> 4) & 0xF, 16);

        System.out.println(ch);


        char char15Hex = Character.forDigit(17, 16);
        System.out.println(char15Hex);

        int b11 = (-1) & 0xF;
        System.out.println(b11);
        System.out.println(Integer.toBinaryString(b11));

    }

    @Test
    public void testEncode() throws UnsupportedEncodingException {
        String s = "中国";

        String s1 = URLEncoder.encode("中国","utf-8");
        System.out.println("s1 = " + s1);

        String sss = URLDecoder.decode(s1, "utf-8");
        System.out.println("sss = " + sss);


        String sss1 = URLDecoder.decode(s1, "iso8859-1");
        System.out.println("sss = " + sss1);

        String sss3333 = new String(sss1.getBytes("iso8859-1"), "utf-8");
        System.out.println("sss3333 = " + sss3333);
    }

    @Test
    public void testEncode1() throws UnsupportedEncodingException {


        String s1 = URLEncoder.encode("%E4%B8%AD%E5%9B%BD","utf-8");
        System.out.println("s1 = " + s1);

        String sss = URLDecoder.decode(s1, "utf-8");
        System.out.println("sss = " + sss);


        String sss1 = URLEncoder.encode("%E4%B8%AD%E5%9B%BD", "iso8859-1");
        System.out.println("sss1111111111111111 = " + sss1);


        String sss12 = URLDecoder.decode(sss1, "iso8859-1");
        System.out.println("sss = " + sss12);
    }

}
