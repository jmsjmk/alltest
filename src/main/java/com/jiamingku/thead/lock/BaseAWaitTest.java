package com.jiamingku.thead.lock;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 1.锁必须是排他锁(才可进行等待通知模式),因为在挂节点的时候没有进行安全控制,说明只有一个线程操作,所以只能排他锁
 * 2.没有获取锁的线程,直接操作condition.await(),condition.signal()方法来说会直接抛出异常
 *   await()要释放锁,在释放锁的时候检查是否是当前线程
 *   signal()进行isHeldExclusively()检查,不是当前线程就直接抛出异常
 */
public class BaseAWaitTest {

    @Test
    public void lockTypeFor() {
        try {
            /**
             * 排它锁才能进行等待通知模式
             */
            ReentrantLock lock = new ReentrantLock();
            Condition condition = lock.newCondition();
            condition.await();
            condition.signal();
            /**
             * 共享锁类不能执行-await操作抛出异常,newCondition()时候抛出异常
             */
            ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
            rwl.readLock().newCondition().await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 没有获取锁的线程-不能调用通知信号-会导致异常
     */
    @Test
    public void signalTestNoHoldLock() {
        try {
            /**
             * 排它锁才能进行等待通知模式
             */
            ReentrantLock lock = new ReentrantLock();
            Condition condition = lock.newCondition();
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 没有获取锁的线程-要抛出异常-有获取锁的检查
     */
    @Test
    public void awaitTestNoHoldLock() {
        try {
            /**
             * 排它锁才能进行等待通知模式
             */
            ReentrantLock lock = new ReentrantLock();
            Condition condition = lock.newCondition();
            condition.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
