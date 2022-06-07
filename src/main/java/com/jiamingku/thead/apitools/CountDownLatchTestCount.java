package com.jiamingku.thead.apitools;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTestCount {
    /**
     * 可以无限的countdown 返现等0时候就返回false
     * 这个count不能从新设置，就是比较强大
     *
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch begin = new CountDownLatch(1);
        System.out.println(begin.getCount());
        begin.countDown();
        System.out.println(begin.getCount());
        System.out.println("--------");
        begin.countDown();
        begin.countDown();
        begin.countDown();
        System.out.println(begin.getCount());
    }
}
