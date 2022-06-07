package com.jiamingku.thead.threadpool;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 创建固定尺寸的线程池
 * <p>
 * Created by jiamingku on 2019/9/29.
 */
@SuppressWarnings("all")
public class BaseExecutorsUseTest {
    static class MyTask implements Runnable {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ": Thread ID :" + Thread.currentThread().getId() + ": Thread Name:" + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建固定尺寸的线程池
     */
    @Test
    public void testNewFixedThreadPool() throws Exception {
        MyTask myTask = new MyTask();
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            service1.execute(myTask);
        }
        service1.shutdown();
        Thread.sleep(500000);
        Object o = null;
    }

    /**
     * 单例线程池
     */
    @Test
    public void testNewSingleThreadExecutor() throws Exception {
        MyTask myTask = new MyTask();
        ExecutorService service1 = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            service1.submit(myTask);
        }
        service1.shutdown();
        Thread.sleep(500000);
    }

    /**
     * 缓存线程池
     */
    @Test
    public void testnewCachedThreadPool() throws Exception {
        MyTask myTask = new MyTask();
        ExecutorService service1 = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            service1.submit(myTask);
        }
        service1.shutdown();
        Thread.sleep(500000);
    }


    /**
     * 缓存线程池
     */
    @Test
    public void testnewScheduledThreadPool() throws Exception {
        MyTask myTask = new MyTask();
        ExecutorService service1 = Executors.newScheduledThreadPool(2);
        for (int i = 0; i < 10; i++) {
            service1.submit(myTask);
        }
        service1.shutdown();
        Thread.sleep(500000);
    }


    /**
     * STOP
     * 536870912
     * TIDYING
     * 1073741824
     * TERMINATED
     * 1610612736
     * STOP < TIDYING < TERMINATED
     * <p>
     * ====
     * <p>
     * RUNNING < SHUTDOWN < STOP < TIDYING < TERMINATED
     */
    @Test
    public void learnThreadPoolStatus() {
        final int COUNT_BITS = Integer.SIZE - 3;
        System.out.println("COUNT_BITS = " + COUNT_BITS);
        int RUNNING = -1 << COUNT_BITS;
        System.out.println("RUNNING = " + RUNNING + "  " + Integer.toBinaryString(RUNNING));
        int SHUTDOWN = 0 << COUNT_BITS;
        System.out.println("SHUTDOWN = " + SHUTDOWN + "  " + Integer.toBinaryString(SHUTDOWN));
        int STOP = 1 << COUNT_BITS;
        System.out.println("STOP = " + STOP + "  " + Integer.toBinaryString(STOP));
        int TIDYING = 2 << COUNT_BITS;
        System.out.println("TIDYING = " + TIDYING + "  " + Integer.toBinaryString(TIDYING));

        int TERMINATED = 3 << COUNT_BITS;
        System.out.println("TERMINATED = " + TERMINATED + "  " + Integer.toBinaryString(TERMINATED));
        AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
        int CAPACITY = (1 << COUNT_BITS) - 1;

        System.out.println("CAPACITY = " + CAPACITY + "  " + Integer.toBinaryString(CAPACITY));
        System.out.println("~CAPACITY + \" \" + Integer.toBinaryString(~CAPACITY) = " + ~CAPACITY + " " + Integer.toBinaryString(~CAPACITY));
        // 536870912
        System.out.println("---》" + Integer.MAX_VALUE);
        // runState is stored in the high-order bits
        System.out.println("Integer.toBinaryString(-536870912) = " + Integer.toBinaryString(-536870912));
        System.out.println("(-3 | 0 = " + (-3 & 0));
    }

    @Test
    public void testGetSecurityManagerMethod() {
        SecurityManager smgr = System.getSecurityManager();
        if (smgr != null) {
            smgr.checkExit(0);
        } else {
            System.out.println("Security manager is null");
        }
    }

    @Test
    public void test() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,
                100,
                100,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10));

        threadPoolExecutor.execute(null);
        threadPoolExecutor.execute(null);
        threadPoolExecutor.execute(null);
        threadPoolExecutor.execute(null);
        //threadPoolExecutor.shutdown();
    }


    @Test
    public void test1() {
        BaseExecutorsUseTest b = new BaseExecutorsUseTest();
//        b.breakRetry();
        b.continueRetry();
        while (true) {
            System.out.println("1 = " + 1);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
    }

    private static void breakRetry() {
        int i = 0;
        retry:
        for (; ; ) {
            System.out.println("start");
            for (; ; ) {
                i++;
                if (i == 4)
                    break retry;
            }
        }
        //start 进入外层循环
        //4
        System.out.println(i);
    }

    private static void continueRetry() {
        int i = 0;
        retry:
        for (; ; ) {
            System.out.println("start");
            for (; ; ) {
                i++;
                if (i == 3)
                    continue retry;
                System.out.println("end");
                if (i == 4)
                    break retry;
            }
        }
        //start 第一次进入外层循环
        //end i=1输出
        //end i=2输出
        //start 再次进入外层循环
        //end i=4输出
        //4 最后输出
        System.out.println(i);
    }

    public static void main(String[] args) {
        final int COUNT_BITS = Integer.SIZE - 3;
        System.out.println("COUNT_BITS = " + COUNT_BITS);
        int RUNNING = -1 << COUNT_BITS;
        System.out.println("RUNNING = " + RUNNING + "  " + Integer.toBinaryString(RUNNING));
        int SHUTDOWN = 0 << COUNT_BITS;
        System.out.println("SHUTDOWN = " + SHUTDOWN + "  " + Integer.toBinaryString(SHUTDOWN));
        int STOP = 1 << COUNT_BITS;
        System.out.println("STOP = " + STOP + "  " + Integer.toBinaryString(STOP));
        int TIDYING = 2 << COUNT_BITS;
        System.out.println("TIDYING = " + TIDYING + "  " + Integer.toBinaryString(TIDYING));

        int TERMINATED = 3 << COUNT_BITS;
        System.out.println("TERMINATED = " + TERMINATED + "  " + Integer.toBinaryString(TERMINATED));
        AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
        int CAPACITY = (1 << COUNT_BITS) - 1;

        System.out.println("CAPACITY = " + CAPACITY + "  " + Integer.toBinaryString(CAPACITY));
        System.out.println("~CAPACITY + \" \" + Integer.toBinaryString(~CAPACITY) = " + ~CAPACITY + " " + Integer.toBinaryString(~CAPACITY));
        // 536870912
        System.out.println("---》" + Integer.MAX_VALUE);
        // runState is stored in the high-order bits
        System.out.println("Integer.toBinaryString(-536870912) = " + Integer.toBinaryString(-536870912));
        System.out.println("(-3 | 0 = " + (-3 & 0));
    }


    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }

}
