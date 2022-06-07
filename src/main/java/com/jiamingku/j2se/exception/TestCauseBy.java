package com.jiamingku.j2se.exception;

import org.junit.Test;

/**
 * Created by jiamingku on 2018/12/18.
 */
public class TestCauseBy {

    static class InnerClassTest {
        InnerClassTest testCauseBy = new InnerClassTest();
    }

    /**
     * 明显的循环创建对象
     */
    @Test
    public void testInterMySelf() {
        InnerClassTest testCauseBy = new InnerClassTest();
    }


    public static void a() {
        try {
            if (true) {
                throw new NullPointerException("null");
            }
        } catch (NullPointerException e) {
            // cause by 就是自己
            // 默认情况下发生异常，cause 就是自己
            // private Throwable cause = this; 因为异常的体系结构中，默认cause指向自己 在Throwable类中
            // 如果创建异常时候指向了异常,cause by 就是那个异常
            throw new RuntimeException("file", e);
        }
    }

    @Test
    public void testBaseCauseBy() {
        try {
            a();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------------------
    public static void simple() {
        try {
            if (true) {
                throw new NullPointerException("null");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSimple() {
        try {
            simple();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------------------
    public static void aCausebyOneSelf() {
        try {
            if (true) {
                throw new NullPointerException("null");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            // caust 就是自己
            RuntimeException e1 = new RuntimeException("file");
            throw new RuntimeException("file", e1);
        }
    }

    /**
     * debug时候cause by 就是自己
     */
    @Test
    public void testCauseByOneSelf() {
        try {
            aCausebyOneSelf();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testCreateException() {
        try {
            String a = null;
            int a1 = a.length();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
