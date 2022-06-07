package com.jiamingku.datastructure.array;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by jiamingku on 2019/7/5.
 * <p>
 * Arrays 工具类
 */
public class ArrayMaker<T> {

    private Class<T> kind;

    public ArrayMaker(Class<T> kind) {
        this.kind = kind;
    }

    public T[] create(int size) {
        return (T[]) Array.newInstance(kind, 10);
    }

    public static void main(String[] args) {
        ArrayMaker<String> stringArrayMaker = new ArrayMaker<>(String.class);
        String[] array = stringArrayMaker.create(9);
        System.out.println(Arrays.toString(array));

        int[][] i = new int[2][3];
        System.out.println("Is i an Object? " + (i instanceof Object));
        System.out.println("Is i[0] an int[]? " + (i[0] instanceof int[]));

        for (int[] j : i) {
            System.out.println("j.length = " + j.length);
            for (int jj : j) {
                System.out.println("jj = " + jj);
            }
        }

        System.out.println(" =============================== ");
        int[][] c = new int[][]{{1, 2, 3}, {4}, {5, 6, 7, 8}};
        int[][] c1 = {{1, 2, 3}, {4}, {5, 6, 7, 8}};
        System.out.println("c[0][1] = " + c[0][1]);
    }
}
