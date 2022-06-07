package com.jiamingku.test.temp;

import java.util.ArrayList;
import java.util.List;

public class JavaTest {

    public static int method(int num, int sum) {
        if (num > 1) {
            sum += num;

            return method(num - 1, sum);

        } else {
            return sum + 1;

        }

    }

    public static int method(int num) {
        return method(num, 0);

    }

    public static int sum(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            list.add(i);
        }

        int a = list.stream().mapToInt(Integer::intValue).reduce(0, (x, y) -> x + y);
        System.out.println(a);
        return a;
    }

    public static void main(String[] args) {
        int a = sum(100);
        System.out.println("a = " + a);
    }
}