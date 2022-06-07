package com.jiamingku.fastjson.test.learn;

import com.alibaba.fastjson.JSON;
import com.jiamingku.j2se.exception.MyException;
import org.junit.Test;

/**
 * 对于异常，你这个其实简单的-java异常也就是个对象么,-对象的序列化也是一样的，将这个对象序列化出去就可以了
 * <p>
 * <p>
 * Created by jiamingku on 2020/6/11.
 */
public class JSONExceptionTest {
    @Test
    public void overRange() {

    }

    public static void main(String[] args) {
        try {
            System.out.println(" ==== ");
            throw new NullPointerException("ceshi");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" =============== ");
            e.getStackTrace();
            String s = JSON.toJSONString(e);
            System.out.println(s);
        }
    }

    /**
     * 一般异常的序列化器-会打印出来--@type 信息。
     */
    @Test
    public void throwMyException() {
        try {
            throw new MyException("myException'");
        } catch (MyException e) {
            e.printStackTrace();
            String s = JSON.toJSONString(e);
            System.out.println(s);
        }
    }
}
