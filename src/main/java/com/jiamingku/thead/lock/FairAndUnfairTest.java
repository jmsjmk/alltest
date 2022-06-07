package com.jiamingku.thead.lock;

import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * (公平 / 非公平) 其实就是获取锁的先后顺序（获取锁时检查队列中有没有同步节点）
 * <p>
 * 1.非公平锁：
 * 为什么会出现线程连续获取锁的情况呢？ 刚释放锁的线程再次获取同步状态的几率会非常大，
 * <p>
 * 2.非公平效果要优于公平，原因就是公平导致上下文切换比较多,所以导致性能不如非公平好。
 * <p>
 * 会造成线程“饥饿”，但极少的线程切换，保证了其更大的吞吐量。
 * <p>
 * <p>
 * ==========================================
 * 公平的锁机制往往没有非公平的效率高，但是，并不是任何场景都是以TPS作为 唯一的指标，公平锁能够减少“饥饿”发生的概率，
 * 等待越久的请求越是能够得到优先满足。
 * ①. 指的是同一线程外层函数获得锁后,再进入该线程的内层方法会自动获取锁 (前提,锁对象是同一个对象)
 * 类似于家里面的大门,进入之后可以进入厕所、厨房等
 * <p>
 * ②. Java中ReentranLock(显示锁)和synchronized(隐式锁)都是可重入锁,可重入锁的一个优点是可在一定程度避免死锁
 */
@SuppressWarnings("ALL")
public class FairAndUnfairTest {

    /**
     * 公平测试
     */
    @Test
    public void test1() {
        ReentLock2 reentLock2 = new ReentLock2(true);
        Thread t1 = new Job(reentLock2, "1");
        Thread t2 = new Job(reentLock2, "2");
        Thread t3 = new Job(reentLock2, "3");
        Thread t4 = new Job(reentLock2, "4");
        Thread t5 = new Job(reentLock2, "5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        try {
            Thread.sleep(100000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 非公平测试
     */
    @Test
    public void test2() {
        ReentLock2 reentLock2 = new ReentLock2(false);
        Thread t1 = new Job(reentLock2, "1");
        Thread t2 = new Job(reentLock2, "2");
        Thread t3 = new Job(reentLock2, "3");
        Thread t4 = new Job(reentLock2, "4");
        Thread t5 = new Job(reentLock2, "5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        try {
            Thread.sleep(100000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class Job extends Thread {
        private ReentLock2 lock;

        public Job(ReentLock2 lock, String name) {
            super(name);
            this.lock = lock;
        }

        @Override
        public void run() {
            for (int i = 0; i < 2; i++) {
                lock.lock();
                try {
                    System.out.println("Lock by [" + this.getName() + "] waiting by " + lock.getQueuedThreads().stream()
                            .map(Thread::getName).collect(Collectors.toList()));
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static class ReentLock2 extends ReentrantLock {
        public ReentLock2(boolean fair) {
            super(fair);
        }

        @Override
        protected Collection<Thread> getQueuedThreads() {
            Collection<Thread> list = super.getQueuedThreads();
            Collections.reverse((List) list);
            return list;
        }
    }
}
