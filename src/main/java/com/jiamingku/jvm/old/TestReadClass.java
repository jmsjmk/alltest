package com.jiamingku.jvm.old;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by jiamingku on 2018/9/13.
 */
public class TestReadClass {

    public static void main(String[] args) {
        String str = "/Users/jiamingku/test/java/test0/a.class";
        Class c = null;

        try {
            InputStream inputStream = new FileInputStream(str);
            byte[] array = new byte[1024*4];
            int readLength = 0;
            while((readLength = inputStream.read(array)) != -1) {

                String s = new String(array);

                System.out.println("s = " + s);
                System.out.println("======");

                for (byte b : array) {

                    String sss = String.format("%02x", b&0xff);
                    System.out.println(sss);
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}