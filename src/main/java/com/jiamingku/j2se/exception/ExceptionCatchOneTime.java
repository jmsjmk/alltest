package com.jiamingku.j2se.exception;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by jiamingku on 2018/12/19.
 */
public class ExceptionCatchOneTime {

    /**
     * try catch 只会抓到一个异常
     */
    public static void a() {
        try {
            FileInputStream file = null;
            if (false) {
                file = new FileInputStream("d:\\dddd.txt");
            }
            throw new NullPointerException("tt");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException.");
            String s = null;
            // 在发生异常.也不会被下面的异常铺货
            throw new NullPointerException(s.toLowerCase());
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException.");
        } catch (Exception e) {
            System.out.println("Exception.");
        } finally {
            System.out.println("finally excute..");
        }
    }

    @Test
    public void testA() {
        a();
    }

    /**
     * 空指针异常的获取消息---null
     */
    @Test
    public void testNullPointExceptionMessage() {
        String a = null;
        System.out.println("a = " + a);
        try {
            if (a.equals("dd")) {
                System.out.println("a = " + a);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 空指针异常的消息获取过来是null
            String s = e.getMessage();
            if (s == null) {
                System.out.println(" ================ ");
            }
        }
    }

    // -----------------------------------------------------------------------------------------------

    /**
     * 必须两对try catch 才能抓到两次异常
     */
    @Test
    public void testB() {
        try {
            for (int i = 0; i < 10; i++) {
                try {
                    if (i == 3) {
                        int a = 9 / 0;
                    }
                } catch (Exception e) {
                    System.out.println(" ===== ");
                    throw new NullPointerException("ttt");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" =111 ");
            System.out.println("catch");
        }
    }
}
