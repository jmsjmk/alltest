package com.jiamingku.thead.base;

public class SyncWaitTest {

    public static void main(String[] args) throws Exception {
        SyncWaitTest syncWaitTest = new SyncWaitTest();
        Thread t1 = new Thread(() -> {
            syncWaitTest.a();
        }, "t1");
        t1.start();

        Thread.sleep(1000L);
        new Thread(() -> {
            syncWaitTest.a();
        }, "t2").start();
        Thread.sleep(1000L);

        t1.interrupt();

    }

    /**
     * 如果线程2 不释放锁, wait（）被唤醒，只是放在同步队列中。只有在次得到锁，才可以执行异常的语句
     * <p>
     * 也就是说，wait唤醒，必须的得到锁
     */
    public synchronized void a() {
        try {
            if (Thread.currentThread().getName().equals("t1")) {
                System.out.println("线程t1准备开始休息，wait()释放锁");
                wait();
                System.out.println("=======================");
            } else {
                System.out.println("线程t2准备开始休息，sleep()不释放锁");
                Thread.sleep(1000000L);
            }
        } catch (InterruptedException e) {
            System.out.println("===============!!!!!!!!!!!!!========");
            e.printStackTrace();
        }
    }
}
