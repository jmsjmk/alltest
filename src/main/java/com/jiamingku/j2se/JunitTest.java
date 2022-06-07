package com.jiamingku.j2se;

import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JunitTest {

    @Test
    public void test() {
        assertEquals(1, 2);
    }


    /**
     * http://blog.51cto.com/lavasoft/43735
     * <p>
     * assert :  jdk自带的
     * java -ea 开启
     * java -da 关闭
     *
     * @param args
     */
    public static void main(String args[]) {
        //断言1结果为true，则继续往下执行
        assert true;
        System.out.println("断言1没有问题，Go！");

        System.out.println("\n-----------------\n");

        //断言2结果为false,程序终止
        assert false : "断言失败，此表达式的信息将会在抛出异常的时候输出！";
        System.out.println("断言2没有问题，Go！");
    }
    // ----------------------------------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------------------------------

    /**
     * @BeforeClass -> @Before -> @Test -> @After -> @AfterClass;
     * 注意@beforeclass与@alterclass是静态的方法修饰
     */
    @Test
    public void test1() {
        String a = "111";
        String b = "1131";
        System.out.println("---");
    }

    @BeforeClass
    public static void testBeforeClass() {
        System.out.println("BeforeClass annotation zx");
    }

    @AfterClass
    public static void testAfterClass() {
        System.out.println("AfterClass annotation zx ");
    }

    @Before
    public void testBefore() {
        System.out.println("Before annotation zx");
    }

    @After
    public void testAfter() {
        System.out.println("After annotation zx ");
    }
    // ----------------------------------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------------------------------
}
