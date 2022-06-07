package com.jiamingku.thead.base;

/**
 * Created by jiamingku on 16/12/22.
 *
 * kill -15
 */
public class KillThread1 extends Thread {
    public static  boolean b = true;
    @Override
    public void run() {
        try {
            while(b) {
                System.out.println(this.getName() + "^_^");
                Thread.sleep(10000L);
                System.out.println(this.getName() + "^_^完成 ");
            }
            System.out.println(" ==================================== ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("==$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

        } ));
    }


    public static void main(String[] args) {
        KillThread1 thread1 = new KillThread1();
        thread1.start();
    }

}
