package com.jiamingku.thead.base;

/**
 * 线程
 */
public class TestCatchException {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                int a = 100;
                int c = a / 0;
            }
        };
        /**
         * 不设置主线程就是崩盘.退出jvm
         */
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("t.getName() = " + t.getName());
            System.out.println("e.getMessage() = " + e.getMessage());
        });

        thread.start();

        try {
            Thread.sleep(1000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
