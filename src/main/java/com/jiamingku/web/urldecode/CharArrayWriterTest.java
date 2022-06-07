package com.jiamingku.web.urldecode;

import java.io.CharArrayWriter;

/**
 * Created by jiamingku on 16/7/31.
 */
public class CharArrayWriterTest {
    public static void main(String[] args) {
        CharArrayWriter cw = new CharArrayWriter();


        cw.write('d');
        cw.flush();
        System.out.println(cw.toString());

        System.out.println(0xD800);
        System.out.println(0xDBFF);
        System.out.println(0xDC00 + "\t" +0xDFFF);
    }
}
