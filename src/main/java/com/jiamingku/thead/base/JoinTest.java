package com.jiamingku.thead.base;

public class JoinTest {

    public static void main(String[] args) throws Exception {

        Thread t = new Thread(()->{
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.setName("t1");
        t.start();

        t.join();




    }
}
