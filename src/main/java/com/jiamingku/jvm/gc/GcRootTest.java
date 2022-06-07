package com.jiamingku.jvm.gc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GcRootTest {

    public static void main(String[] args) {
        Date d = new Date();

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            list.add(String.valueOf(i) + "");
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据添加完毕--请操作");
        new Scanner(System.in).next();
        list = null;
        d = null;

        System.out.println("设置空完成---请操作");
        new Scanner(System.in).next();

        System.out.println("结束");
    }
}
