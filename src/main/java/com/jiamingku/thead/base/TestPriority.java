package com.jiamingku.thead.base;

/**
 * Created by jiamingku on 2019/9/30.
 */
public class TestPriority {

    public static void main(String[] args) throws InterruptedException {
        ThreadPriority t1 = new ThreadPriority("线程1");
        t1.setPriority(1);
        t1.start();

        ThreadPriority t2 = new ThreadPriority("线程2");
        t2.setPriority(2);
        t2.start();

        ThreadPriority t3 = new ThreadPriority("线程3");
        t3.setPriority(3);
        t3.start();
        Thread.sleep(1000000L);
    }
}

class ThreadPriority extends Thread {

    public ThreadPriority(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ": 在运行priority:" + this.getPriority() + "" +
                    " 线程的id:" + Thread.currentThread().getId());
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
