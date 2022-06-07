package com.jiamingku.datastructure.sort;

/**
 * Created by jiamingku on 2017/6/25.
 */
public class SortUtils {

    public static int[] source = {1111, 2, 88, 99, 32, 98, 66, 34};

    public static void print(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 3, 23, 323};
        print(a);
    }
}
