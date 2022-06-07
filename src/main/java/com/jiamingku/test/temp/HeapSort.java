package com.jiamingku.test.temp;

import org.junit.Test;

public class HeapSort {

    public void buildHeap(int[] array, int n) {
        int lastIndex = (n - 1);
        int parentIndex = (lastIndex - 1) / 2;
        for (int i = parentIndex; i >= 0; i--) {
            heapFiy(array, n, i);
        }
    }

    @Test
    public void testBuildHeap() {
        int[] array = {10, 32, 23, 332, 33, 55, 66, 777, 9, 8};
        buildHeap(array, array.length);
        for (int i : array) {
            System.out.println(i);
        }
    }


    public void sort(int[] array, int n) {
        buildHeap(array, n);
        for (int i = n - 1; i >= 0; i--) {
            swap(array, i, 0);
            heapFiy(array, i, 0);
        }
    }

    @Test
    public void testSortHeap() {
        int[] array = {10, 3232333, 23, 3312, 3232333, 55, 66, 7977, 9, 8};
        sort(array, array.length);
        for (int i : array) {
            System.out.println(i);
        }
    }

    /**
     * 指定节点堆话
     *
     * @param array
     * @param n
     * @param i
     */
    public static void heapFiy(int[] array, int n, int i) {
        if (i >= n)
            return;
        int lIndex = 2 * i + 1;
        int rIndex = 2 * i + 2;
        int maxIndex = i;
        int max = array[i];
        if (lIndex < n && array[lIndex] > max) {
            max = array[lIndex];
            maxIndex = lIndex;
        }
        if (rIndex < n && array[rIndex] > max) {
            maxIndex = rIndex;
        }
        if (i != maxIndex) {
            swap(array, i, maxIndex);
            heapFiy(array, n, maxIndex);
        }
    }

    public static void swap(int[] array, int s, int d) {
        int temp = array[s];
        array[s] = array[d];
        array[d] = temp;
    }

    public static void main(String[] args) {
        int[] array = {10, 32, 23, 332, 33, 55, 66, 777, 9, 8};
        for (int i : array) {
            System.out.println(i);
        }
        System.out.println("--------------------------------");
        heapFiy(array, array.length, 0);
        for (int i : array) {
            System.out.println(i);
        }
    }

}
