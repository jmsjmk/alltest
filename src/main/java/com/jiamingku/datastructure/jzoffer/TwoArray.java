package com.jiamingku.datastructure.jzoffer;

/**
 * Created by jiamingku on 2018/1/2.
 */
public abstract class TwoArray {

    private static int[][] array = new int[4][4];

    public static void main(String[] args) {
        array = init();
        int result = 12;
        for (int i = array.length - 1; i >= 0; i--) {// 列
            for (int j = 0; j < array[i].length; j++) { // 行
                if (array[j][i] > result) { // 大于
                    break;
                } else if (array[j][i] < result) { // 小于
                    continue;
                } else if (array[j][i] == result) { // 等于
                    System.out.println("---------");
                    System.out.println("result: 列[" + j + "] 行：" + i + "===" + array[j][i]);
                }
            }
        }
    }

    /**
     * 数组的初始化
     *
     * @return
     */
    public static int[][] init() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                System.out.print(j + i * 4);
                System.out.print(" ");
                array[i - 1][j - 1] = (j + i * 4);
            }
            System.out.println();
        }
        System.out.println("=======================");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        return array;
    }
}
