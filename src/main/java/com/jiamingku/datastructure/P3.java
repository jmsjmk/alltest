package com.jiamingku.datastructure;

import java.util.*;

/**
 * Created by jiamingku on 2020/3/28.
 * 可以删除的例子
 */
@SuppressWarnings("all")
public class P3 {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();
        List<Integer> list = new ArrayList<>();


        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int j = random.nextInt(1000000);
            list.add(j);
            list1.add(j);
            list2.add(j);
            list3.add(j);
            list4.add(j);
        }

        Integer[] a = list.toArray(new Integer[0]);
        Integer[] a2 = list2.toArray(new Integer[0]);
        Integer[] a3 = list3.toArray(new Integer[0]);
        Integer[] a4 = list4.toArray(new Integer[0]);


        mp(a);
        xz(a2);
        cr(a3);
        ks(a4, 0, a4.length - 1);
        Collections.sort(list1, Comparator.reverseOrder());

        for (int i : a) {
            System.out.print("冒泡 = " + i + "\t");
        }


        System.out.println();
        for (int i : list1) {
            System.out.print("java = " + i + "\t");
        }


        System.out.println();
        for (int i : a2) {
            System.out.print("选择 = " + i + "\t");
        }

        System.out.println();
        for (int i : a3) {
            System.out.print("插入 = " + i + "\t");
        }

        System.out.println();
        for (int i : a4) {
            System.out.print("快速 = " + i + "\t");
        }
    }

    public static Integer[] mp(Integer[] array) {
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] < array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                }
            }
        }
        return array;
    }

    public static Integer[] xz(Integer[] array) {
        int index;
        int temp;
        for (int i = 0; i < array.length; i++) {
            index = i;
            for (int j = i; j < array.length; j++) {
                if (array[index] < array[j]) {
                    index = j;
                }
            }
            if (index != i) {
                temp = array[i];
                array[i] = array[index];
                array[index] = temp;
            }
        }
        return array;
    }


    public static Integer[] cr(Integer[] array) {
        int temp;
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] > array[j - 1]) {
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                } else {
                    break;
                }
            }
        }

        return array;
    }

    public static Integer[] ks(Integer[] array, int s, int e) {
        int value = array[s];
        int i = s;
        int j = e;
        while (i < j) {
            while (i < j && array[i] > value) {
                i++;
            }
            while (i < j & array[j] < value) {
                j--;
            }

            if (array[j] == array[i] && i < j) {
                j--;
            } else {
                if (j != i) {
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }

        if (i - 1 > s) {
            ks(array, s, i - 1);
        }
        if (j + 1 < e) {
            ks(array, j + 1, e);
        }
        return array;
    }

}
