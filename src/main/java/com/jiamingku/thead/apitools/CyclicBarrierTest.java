package com.jiamingku.thead.apitools;

import java.util.concurrent.CyclicBarrier;

/**
 * 感觉跟countdown好像区别不大
 */
public class CyclicBarrierTest {
    private static CyclicBarrier barrier;

    static class threadTest1 extends Thread {
        public void run() {
            System.out.println(Thread.currentThread().getName() + "达到...");
            try {
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完成...");
        }
    }

    public static void main(String[] args) {
        barrier = new CyclicBarrier(8);
        for (int i = 1; i <= 5; i++) {
            new threadTest1().start();
        }
    }
}