package com.jiamingku.datastructure.sort;

public class Temp {

    public static void main(String[] args) {
        int[] array = SortUtils.source;
        SortUtils.print(array);
        System.out.println("----");
//        downHeap(array, 0, array.length - 1);
//        SortUtils.print(array);
//        System.out.println("----");
        downHeap2(array, 0, array.length - 1);
        SortUtils.print(array);
//        System.out.println("+++++++++++++++");
//        System.out.println("+++++++++++++++");
//        System.out.println("+++++++++++++++");
//        System.out.println("+++++++++++++++");
//        System.out.println("+++++++++++++++");
//        buildHeap(array, 0, array.length - 1);
//        SortUtils.print(array);
//        System.out.println("+++++++++++++++");
//        buildHeap2(array, 0, array.length - 1);
//        SortUtils.print(array);
//        System.out.println("----");
//        sort(array);
//        SortUtils.print(array);
    }

    public static void b(int[] a) {
        boolean flag = true;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    SortUtils.swap(a, j, j + 1);
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    public static void buildHeap(int array[], int index, int n) {
        int lastP = (n - 1) / 2;
        while (lastP >= 0) {
            downHeap(array, lastP, n);
            lastP--;
        }
    }

    public static void buildHeap2(int array[], int index, int n) {
        int lastP = (n - 1) / 2;
        while (lastP >= 0) {
            downHeap2(array, lastP, n);
            lastP--;
        }
    }

    public static void downHeap(int array[], int index, int n) {
        if (index >= n) {
            return;
        }
        int l = index * 2 + 1;
        int r = index * 2 + 2;
        int min = index;
        if (r < n && array[r] < array[min]) {
            min = r;
        }
        if (l < n && array[l] < array[min]) {
            min = l;
        }
        if (min == index) {
            return;
        } else {
            SortUtils.swap(array, index, min);
        }
        downHeap(array, min, n);
    }

    public static void downHeap2(int array[], int index, int n) {
        while (index < n) {
            int l = index * 2 + 1;
            int r = index * 2 + 2;
            int min = index;
            if (r < n && array[r] < array[min]) {
                min = r;
            }
            if (l < n && array[l] < array[min]) {
                min = l;
            }
            if (min == index) {
                return;
            } else {
                SortUtils.swap(array, index, min);
                index = min;
            }
        }
    }


    public static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            SortUtils.swap(array, (array.length - i) - 1, 0);
            downHeap2(array, 0, array.length - 2 - i);
        }

    }
}
