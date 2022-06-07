package com.jiamingku.thead.base;

import org.junit.Test;

/**
 * Created by jiamingku on 2019/9/10.
 */
public class TestWaitNotifyNotifyAllBase {

    /**
     * 调用wait方法的时候必须是同步方法,否则异常(IllegalMonitorStateException)
     *
     * @param args
     */
    public static void main(String[] args) {
        TestWaitNotifyNotifyAllBase testWait = new TestWaitNotifyNotifyAllBase();
        try {
            testWait.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 非同步方法中调用是发生异常的
     */
    @Test
    public void testNotify() {
        TestWaitNotifyNotifyAllBase testWaitNotifyNotifyAll = new TestWaitNotifyNotifyAllBase();
        testWaitNotifyNotifyAll.notify();
    }

    /**
     * 非同步方法中调用是发生异常的
     */
    @Test
    public void testNotifyAll() {
        TestWaitNotifyNotifyAllBase testWaitNotifyNotifyAll = new TestWaitNotifyNotifyAllBase();
        testWaitNotifyNotifyAll.notifyAll();
    }
}
