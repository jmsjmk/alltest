package com.jiamingku.datastructure.array;

import org.junit.Test;

/**
 * 数组记住三点：
 * 声明
 * 创建
 * 初始化
 * <p>
 * 两种写法：1.type[] arr_name;  2.type arr_name[];几乎被淘汰了。
 * --------------------------------------------------------------------------------------------------------
 * 1.====数组的静态初始化，其实里面包含了一种类型推断在里面，如果你换成两行写就报错，这里面有个编译器的类型推断功能
 * <p>
 * 2.====数组的动态初始化，下面三个步骤
 * <p>
 * 3.=====数组是引用类型
 *
 *
 * A.for循环逐一复制
 * B.System.arraycopy
 * C.System.copyof
 * D.使用clone方法
 */
public class BaseArrayTest {

    @Test
    public void test1() {
        // 静态初始化--,声明，创建，初始化
        int[] a = {1, 2, 3};
    }


    @Test
    public void test2() {
        // 静态初始化--,声明，创建，初始化
        int[] a;
        // 创建
        a = new int[10];
        // 初始化
        for (int i = 0; i < 10; i++) {
            a[i] = i + 1;
        }
    }

    @Test
    public void test3() {
        int[] a = new int[5];
        String[] s = new String[3];
        System.out.println(a.getClass());
        System.out.println(s.getClass());
    }

    /**
     * 注意事项 :
     * 不能指定数组长度
     */
    @Test
    public void test4() {
        // correct
        String[] str = new String[]{"1", "2"};
        String[] asss = {"1", "3"};
        // 错误
        //  String[] str1 = new String[2]{"2","c"};
        //
        // int[5] a;
    }

    // -----------------------------------------
    // -----------------------------------------
    @Test
    public void test5() {
        int[][] i = new int[2][3];
        System.out.println("Is i an Object? " + (i instanceof Object));
        System.out.println("Is i[0] an int[]? " + (i[0] instanceof int[]));
        System.out.println("----");
        i[0][2] = 5;
        i[0][4] = 5;
        i[0][3] = 5;
    }

    /**
     * 代码注意:这个位置
     * 这个只是申请了可以容纳3个一纬数组的，第二纬度你还没有初始化，这时候你赋值
     * a[0][0]会抛出空指针异常的
     */
    public void test6() {
        //二维变长数组 --- 可以省略一个-但不能两个都省略
        int[][] a = new int[3][]; // 代码注意
        a[0] = new int[2];
        a[1] = new int[3];
        a[2] = new int[1];
        //Error: 不能空缺第一维大小
        //int[][] b = new int[][3];

        // 二维数组静态初始化：
        int[][] c = new int[][]{{1, 2, 3}, {4}, {5, 6, 7, 8}};
        int[][] c1 = {{1, 2, 3}, {4}, {5, 6, 7, 8}};

        /**
         * 使用花括号的嵌套完成，这时候不指定两个维数的大小，并且根据初始化值的个数不同，可以生成不同长度的数组元素。
         * com.jiamingku.collection.enumerationtest.DemoEnumeration 里面有例子
         */
    }
}
