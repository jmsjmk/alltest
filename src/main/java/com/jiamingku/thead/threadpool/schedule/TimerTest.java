package com.jiamingku.thead.threadpool.schedule;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    public static void main(String[] args) throws InterruptedException {
        long a = Long.MAX_VALUE >> 1;
        System.out.println(a);
        long a1 = 4611686018427387904L;
        a1 <<= 1;
        System.out.println(a1);
        System.out.println(Long.MAX_VALUE);
        if (Math.abs(-100) > (Long.MAX_VALUE >> 1))
            a1 >>= 1;
    }


    /**
     * 关于取消方法:
     */
    @Test
    public void testAtschedule1() {
        try {
            Timer timer = new Timer();
            WorkTask w = new WorkTask();
            /**
             * 调度放入队列-没运行的都可以取消
             */
            w.cancel();
            /**
             *
             */
            timer.cancel();
            Thread.sleep(1000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    /**
     * 相对时间来运行
     */
    @Test
    public void testAtschedule() {
        try {
            Timer timer = new Timer();
            timer.schedule(new WorkTask(), 1000, 1000);// 1秒后执行 然后每隔1秒 执行一次
            Thread.sleep(1000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // timer.purge();//释放 已经取消的任务所占用的内存 timer.purge();//释放 已经取消的任务所占用的内存
    // timer.cancel();//停止任务(程序停止)

    /**
     * 固定速度就是-如果一个任务时间耗时了,错过的任务都运行(绝对时间运行)
     */
    @Test
    public void testAtFixedRate() {
        try {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new WorkTask(), 1000, 1000);
            Thread.sleep(1000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testAtFixedRate1() {
        try {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new WorkTask1(), 1000, 1001);
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class WorkTask extends TimerTask {
    private static int count = 0;

    public void run() { //计划任务中具体做是事情
        System.out.println("开始任务:--");
        if (count == 0) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss.SSS");
        System.out.print("完成：" + df.format(new Date()));
        System.out.println();
    }
}


class WorkTask1 extends TimerTask {
    private static int count = 0;

    public void run() { //计划任务中具体做是事情
        int a = 1;
        int b = 0;
        int c = a /b;
    }
}