package com.jiamingku.thead.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockInterupt {

    ReentrantLock lock = new ReentrantLock();


    public static void main(String[] args) throws InterruptedException {
        ReentrantLockInterupt lockCompareSynchronized = new ReentrantLockInterupt();

        Thread t1 = new Thread(new L(lockCompareSynchronized), "t1");
        t1.start();
        Thread.sleep(100L);
        Thread t2 = new Thread(new L(lockCompareSynchronized), "t2");
        t2.start();
        // 获取中不能中断
        t2.interrupt();
    }

    public void testLock() {
        // 阻塞的方式，
        try {
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            System.out.println("抛出异常了----");
            e.printStackTrace();
        }
        try {
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            Thread.sleep(100000L);
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName() + " complete");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    static class L implements Runnable {
        ReentrantLockInterupt lockCompareSynchronized;

        public L(ReentrantLockInterupt lockCompareSynchronized) {
            this.lockCompareSynchronized = lockCompareSynchronized;
        }

        @Override
        public void run() {
            lockCompareSynchronized.testLock();
        }
    }
}
