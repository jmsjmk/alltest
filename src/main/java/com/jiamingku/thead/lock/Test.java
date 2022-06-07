package com.jiamingku.thead.lock;

public class Test {

    public static void main(String[] args) {

        Object O = new Object();
        Thread t = new Thread(() -> {

            Thread.currentThread().interrupt();
            synchronized (O) {

                System.out.println("Thread.currentThread().isInterrupted() = " + Thread.currentThread().isInterrupted());
                System.out.println("\"...\" = " + "...");
            }
        });

        t.start();


        System.out.println(" ===== ");

        try {
            Thread.sleep(100000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
