package com.jiamingku.thead.base;

import java.util.HashMap;

/**
 * idea 的多线程模式调试  如果all模式的话，只有卡住一个线程- thread会卡住所有的线程， rabbitmq的心跳就是一个出现了一个问题
 * Created by Daxin on 2017/10/22.
 */
public class IdeaHashMapInfiniteLoop {
    private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(2, 0.75f);

    public static void main(String[] args) throws InterruptedException {
        map.put(5, 55);

        new Thread("Thread1-Name") {


        }.start();
        new Thread("Thread2-Name") {
            public void run() {
                try {
                    System.out.println("Thread2-Name Start");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                map.put(3, 33);// 断点位置2
                System.out.println(map);
            }
        }.start();
        new Thread(() -> System.out.println("333"), "test").start();

        // 断点位置 3
        System.out.println("Thread-Main-Name Start");
        System.out.println("Thread-Main-Name Start");
        System.out.println("Thread-Main-Name Start");
        Thread.sleep(500000);

    }
}