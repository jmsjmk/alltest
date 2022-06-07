package com.jiamingku.thead.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 只要有写节点挂上之后，后来的读都不会获取读锁了
 * 至于网上说的那种情况可能都没有出现
 */
public class ReadWriteLockTest {
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) throws Exception {
        data1 data1 = new data1();

        t1 t1 = new t1("t1");
        t1.setData1(data1);
        t1.start();

        t2 t2 = new t2("t2");
        t2.setData1(data1);
        t2.start();
        Thread.sleep(1000L);

//        long a = 21072817323934600001;

        t1 t3 = new t1("t3");
        t3.setData1(data1);
        t3.start();

    }
}

class t1 extends Thread {

    public t1(String name) {
        super(name);
    }

    private data1 data1;

    public com.jiamingku.thead.lock.data1 getData1() {
        return data1;
    }

    public void setData1(com.jiamingku.thead.lock.data1 data1) {
        this.data1 = data1;
    }

    @Override
    public void run() {
        data1.get();
    }
}

class t2 extends Thread {

    public t2(String name) {
        super(name);
    }

    private data1 data1;

    public com.jiamingku.thead.lock.data1 getData1() {
        return data1;
    }

    public void setData1(com.jiamingku.thead.lock.data1 data1) {
        this.data1 = data1;
    }

    @Override
    public void run() {
        data1.set(1);
    }
}

class data1 {
    private int data;// 共享数据
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public void set(int data) {
        rwl.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "准备写入数据");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.data = data;
            System.out.println(Thread.currentThread().getName() + "写入" + this.data);
        } finally {
            rwl.writeLock().unlock();
        }
    }

    public void get() {
        rwl.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "准备读取数据");
            try {
                Thread.sleep(2000000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "读取" + this.data);
        } finally {
            rwl.readLock().unlock();
        }
    }
}