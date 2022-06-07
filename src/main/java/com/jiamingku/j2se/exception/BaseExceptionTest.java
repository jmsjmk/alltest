package com.jiamingku.j2se.exception;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * Created by jiamingku on 2019/4/5.
 */
public class BaseExceptionTest {

    @Test
    public void test1() {
        double d = 100 / 0;
        System.out.println("d = " + d);
    }


    @Test
    public void test2() {
        try {
            if (true) {
                NullPointerException nullPointerException = new NullPointerException();
                throw nullPointerException;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    public void testPrintException() {
        try {
            String a = null;
            System.out.println("a.length() = " + a.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * https://www.cnblogs.com/lpob/p/11853592.html
     */
    @Test
    public void testPrintNullPointException() throws NullPointerException {
        try {
            NullPointerException nullPointerException = new NullPointerException("测试异常的创建");
            throw nullPointerException;
        } catch (Exception e) {
            e.printStackTrace();
            String s = JSON.toJSONString(e);
            System.out.println("s = " + s);
        }
    }

    @Test
    public void testArray() {
        String[] array = new String[1];
        System.out.println("array[0] = " + array[0]);
    }


    class Student {
        private String name;
        private String age;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }


    public static void main(String[] args) {
        Student s1 = new BaseExceptionTest().new Student();
        s1.setAge("11");
        s1.setName("name1");

        Student s3 = new BaseExceptionTest().new Student();
        s3.setAge("33");
        s3.setName("name3");

        Student s2 = new BaseExceptionTest().new Student();
        s2.setAge("22");
        s2.setName("name2");

        Student[] ss = {s1, s2, s3};

        String s1s = JSON.toJSONString(ss);
        System.out.println("s1s = " + s1s);
    }
}
