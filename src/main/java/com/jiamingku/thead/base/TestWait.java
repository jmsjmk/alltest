package com.jiamingku.thead.base;

/**
 * 1.notfiy 方法没执行完不释放锁
 * 2.notify之后线程的状态会发生变化(等待队列变化成为,阻塞队列)
 */
public class TestWait {

    /**
     * 1.进入这个方法必须的获取锁
     * 2.wait之后直接释放锁,并且线程卡住wait()位置
     * <p>
     * 但是notify 执行必须获取锁, 并且离开synchronized方法前,是不会释放锁的
     * <p>
     * 但是如果线程唤醒之后，在执行这个方法的时候，必须也的获取到锁
     *
     * @throws InterruptedException
     */
    public synchronized void t() throws InterruptedException {
        System.out.println("true = " + true + "---" + Thread.currentThread().getName());
        this.wait();
        System.out.println(" 唤醒了 但是又睡了= " + Thread.currentThread().getName());
        Thread.sleep(1000000L);
    }

    public static void main(String[] args) throws InterruptedException {
        TestWait testWait = new TestWait();
        MyThread m1 = new MyThread();
        m1.setT(testWait);

        MyThread m2 = new MyThread();
        m2.setT(testWait);
        m1.start();
        m2.start();
        Thread.sleep(10000L);
        /** 唤醒所有只有一个获得执行机会,并且sleep不释放锁*/
        testWait.n();
    }

    /**
     * 在离开方法前是不是释放锁的, notify只是唤醒线程, 并且状态变成block状态
     * jstack 方法可以查看到信息 这时候的状态：
     * java.lang.Thread.State: BLOCKED (on object monitor)
     * at java.lang.Object.wait(Native Method)
     * -------------------------------------------
     * 去掉notifyall方法 -线程的状态
     * <p>
     * <p>
     * "Thread-0" #11 prio=5 os_prio=31 tid=0x00007ff375a7b000 nid=0x5703 in
     *   Object.wait() [0x000070000f6d4000]
     * java.lang.Thread.State: WAITING (on object monitor)
     * <p>
     *  一个wait在等待队列一个在阻塞队列中.
     * @throws InterruptedException
     */
    public synchronized void n() throws InterruptedException {
        System.out.println(" prepare notify = ");
        this.notifyAll();
        Thread.sleep(1009000L);
    }
}

class MyThread extends Thread {
    TestWait t;

    public TestWait getT() {
        return t;
    }

    public void setT(TestWait t) {
        this.t = t;
    }

    @Override
    public void run() {
        try {
            t.t();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
