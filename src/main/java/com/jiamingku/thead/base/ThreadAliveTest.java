package com.jiamingku.thead.base;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 没启动alive=false
 * 启动alive=true
 * 运行结束alive=false
 */
public class ThreadAliveTest {

    @Test
    public void testNoStart() {
        Thread t = new Thread(new AliveNoStart());
        System.out.println("t.isAlive() = " + t.isAlive());
    }

    @Test
    public void testStart() throws InterruptedException {
        Thread t = new Thread(new AliveStart());
        t.start();
        System.out.println("t.isAlive() = " + t.isAlive());
        while (true) {
            System.out.println("t.isAlive() = " + t.isAlive());
            TimeUnit.SECONDS.sleep(1L);
        }
    }
}

class AliveNoStart implements Runnable {
    @Override
    public void run() {
        System.out.println("true = " + true);
    }
}

class AliveStart implements Runnable {
    public AliveStart() {
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(3L);
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("================true = " + true);
        }
    }
}