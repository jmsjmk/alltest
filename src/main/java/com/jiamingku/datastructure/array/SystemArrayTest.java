package com.jiamingku.datastructure.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by jiamingku on 2020/2/14.
 */
public class SystemArrayTest {

    @Test
    public void teestCopyT() {
        String[] arr = new String[10];
        String str = "";
        System.out.println(arr.getClass()); // 数组的String 类
        System.out.println(str.getClass()); // 非数组的String 类
        System.out.println("--");
        System.out.println("--");
        System.out.println("--");
        System.out.println(arr.getClass().getComponentType()); // String类
        System.out.println(str.getClass().getComponentType()); // 得到null值，因为str不是数组
        System.out.println(arr.getClass().getComponentType().isPrimitive()); // 显示false，因为String 不是基本数据类型

        int[] arr1 = new int[10];
        System.out.println(arr1.getClass().getComponentType().isPrimitive()); // 显示true，因为int是基本数据类型
    }

    /**
     * System.arraycopy 方法：
     * 原始数据，开始坐标，目标数组，目标数据的坐标，长度，对应的记录下异常
     */
    @Test
    public void testArrayCopy() {
        int[] a = {1, 2, 3, 4, 5};
        p1(a);
        int[] b = new int[5];
        System.arraycopy(a, 0, b, 1, 3111);
        // System.arraycopy(a, 0, b, 0, 3);
        System.out.println("-=====================");
        p1(b);
    }

    public static void p(int[] a) {
        for (int i : a) {
            System.out.print("i = " + i + "\t");
        }
        System.out.println();
    }

    public static void p1(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print("i = " + i + "(" + a[i] + ")" + "\t");
        }
        System.out.println();
    }

    @Test
    public void test1() {
        int[][] arr = {{2, 5}, {1}, {3, 2, 4}, {1, 7, 5, 9}};

        System.out.println("arr = " + arr[3][3]);
        System.out.println("arr = " + arr[2][2]);
    }
}
