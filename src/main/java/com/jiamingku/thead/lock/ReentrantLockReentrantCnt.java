package com.jiamingku.thead.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantLock
 * 重入+1
 * 释放-1
 * <p>
 * 还有常规的监控方法可以看到：
 */
@SuppressWarnings("all")
public class ReentrantLockReentrantCnt {
    ReentrantLock lock = new ReentrantLock();
    static ReentrantLock r1 = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new L(), "t1").start();
        System.out.println("r1.getQueueLength() = " + r1.getQueueLength());
        boolean b = r1.isLocked();

        Condition c = r1.newCondition();

        ReentrantReadWriteLock rr = new ReentrantReadWriteLock();

        Lock l = rr.readLock();
        Lock w = rr.writeLock();

        w.lock();
        w.newCondition().await();

        c.await();

        c.signal();

        r1.lock();
        r1.unlock();

        System.out.println("isLocked = " + b);
        int count = r1.getHoldCount();
        System.out.println("getHoldCount = " + count);
    }

    public void testLock() {
    }

    static class L implements Runnable {
        ReentrantLockReentrantCnt lockCompareSynchronized;

        public L(ReentrantLockReentrantCnt lockCompareSynchronized) {
            this.lockCompareSynchronized = lockCompareSynchronized;
        }

        public L() {
        }

        @Override
        public void run() {
            r1.lock();
            r1.lock();
            int cunt = 0;
            try {
                cunt = r1.getHoldCount();
                System.out.println("getHoldCount:" + cunt);
                Thread.sleep(1000L);
                System.out.println("-----------------------");
            } catch (Exception e) {
                r1.unlock();
            } finally {
                r1.unlock();
            }

            cunt = r1.getHoldCount();
            System.out.println("getHoldCount:" + cunt);
        }
    }
}
