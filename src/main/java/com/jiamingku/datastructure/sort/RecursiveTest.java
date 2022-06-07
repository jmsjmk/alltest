package com.jiamingku.datastructure.sort;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * 1)找到递归关系
 * 2)找到递归出口
 * -----------------------------------
 * 1.斐波那契数列
 * 2.阶乘
 * 3.等差数列求第n项
 * 3.1等差数列求前n项和
 * 4.数组的n,L到R项求和
 * 5.数组求最大的数字
 * 6.汉诺塔问题
 * -----------------------------------
 * 1.冒泡
 * 2.选择
 * 3.插入
 * 4.希尔
 * 5.归并(分/合)
 * 6.堆排
 * 7.快速
 * ------------------------------------
 */
public class RecursiveTest {

    public static void moveDish(int level, char from, char middle, char to) {
        if (level == 1) {
            System.out.println("从" + from + " 移动盘子" + level + " 号到" + to);
        } else {
            moveDish(level - 1, from, to, middle);
            System.out.println("从" + from + " 移动盘子" + level + " 号到" + to);
            moveDish(level - 1, middle, from, to);
        }
    }

    public static void main1(String[] args) {
        int nDisks = 4;
        moveDish(nDisks, 'A', 'B', 'C');
    }

    @Test
    public void test7() {
        hnt(3, "A", "B", "C");

    }

    public void hnt(int n, String A, String B, String C) {
        if (n == 1) {
            System.out.println(A + "-->" + C);
        } else {
            hnt(n - 1, A, C, B);
            System.out.println(A + "-->" + C);
            hnt(n - 1, B, A, C);
        }
    }

    @Test
    public void test1() {
        int n = 10;
        int r = fbnq(n);
        System.out.println(r);
        r = fbnq2(n);
        System.out.println(r);
    }

    public int fbnq(int n) {
        if (n == 1) return 1;
        if (n == 2) return 1;
        return fbnq(n - 1) + fbnq(n - 2);
    }

    public int fbnq2(int n) {
        int r = 0;
        int temp1 = 0;
        int temp2 = 0;
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                temp1 = 1;
            }
            if (i == 2) {
                temp2 = 1;
            }
            r = temp1 + temp2;
            temp1 = temp2;
            temp2 = r;
        }
        return r;
    }

    public void fbnq3() {
        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);

    }

    public static void main(String[] args) {
        Stream.iterate(new int[]{1, 1}, a -> new int[]{a[1], a[0] + a[1]})
                .limit(10)
                .forEach(a -> System.out.println(a[0]));

//        Stream.iterate(1 , a -> 1 + 1).limit(10).forEach(System.out::println);
    }
    // ------------------------------------------------------------------------------------

    @Test
    public void test2() {
        int r = jc(6);
        System.out.println(r);
        r = jc1(6);
        System.out.println(r);
    }

    public int jc(int n) {
        if (n == 1) return 1;
        return jc(n - 1) * n;
    }

    public int jc1(int n) {
        int r = 1;
        for (int i = 1; i <= n; i++) {
            r = r * i;
        }
        return r;
    }

    // ------------------------------------------------------------------------------------
    @Test
    public void test3() {
        int r = dcsx(10);
        System.out.println(r);
        r = dcsl1(10);
        System.out.println(r);

        r = sumfor(5);
        System.out.println(r);

        r = sumDcsx(10);
        System.out.println(r);
    }

    public int dcsx(int n) {
        if (n == 1) {
            return 2;
        }
        return dcsx(n - 1) + 2;
    }

    public int dcsl1(int n) {
        int r = 0;
        for (int i = 0; i < n; i++) {
            r += 2;
        }
        return r;
    }


    public int sumDcsx(int n) {
        if (n == 1) {
            return 0;
        }
        if (n % 2 == 1) {
            n = n - 1;
        }
        return sumDcsx(n - 1) + n;
    }

    public int sumfor(int n) {
        int r = 0;
        for (int i = 0; i <= 2 * n; i++) {
            if (i % 2 != 0) {
                continue;
            }
            r = r + i;
        }
        return r;
    }

    @Test
    public void test5() {
        int[] aa = {1, 2, 3, 4, 6, 7, 8};
        int r = sumArraay(aa, 3, 5);
        System.out.println(r);
        r = sumFOR(aa, 3, 5);
        System.out.println(r);
    }

    public int sumArraay(int[] arr, int l, int r) {
        int result = 0;
        if (l == r) {
            return arr[l];
        }
        result = sumArraay(arr, l, r - 1) + arr[r];
        return result;
    }

    public int sumFOR(int[] arr, int l, int r) {
        int sum = 0;
        for (int j = l; j <= r; j++) {
            sum += arr[j];
        }
        return sum;
    }

    @Test
    public void test6() {
        int[] a = {3, 44, 66, 5, 43, 34344, 434};
        int r = maxArray(a, 0, (a.length - 1));
        System.out.println(r);
        r = maxArrayForLoop(a);
        System.out.println(r);
    }

    public int maxArray(int[] arrsy, int l, int r) {
        if (l == r) {
            return arrsy[l];
        }

        if (arrsy[r] > maxArray(arrsy, l, r - 1)) {
            return arrsy[r];
        } else {
            return maxArray(arrsy, l, r - 1);
        }
    }

    public int maxArrayForLoop(int[] array) {
        int maxInt = array[0];
        for (int i : array) {
            if (maxInt > i) {
                continue;
            }
            maxInt = i;

        }
        return maxInt;
    }


}
