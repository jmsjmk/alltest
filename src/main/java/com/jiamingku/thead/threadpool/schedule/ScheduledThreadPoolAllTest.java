package com.jiamingku.thead.threadpool.schedule;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * 1) scheduleWithFixedDelay(完成时间点开始计算)
 * <p>
 * 2) scheduleAtFixedRate(指定延迟时间点计算)
 */
public class ScheduledThreadPoolAllTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(10);
        // ---相对时间--进行处理
//        executScheduleWithFixedDelay(executorService);

        // ---固定的时间-轮训执行----差10个固定频率的10个都执行了
        executScheduleAtFixedRate(executorService);
//
//        executSchedule(executorService);
//
//        System.out.println("over");
//
//        executorService.shutdown();
    }

    public static String getTime() {
        Date d = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(d);
    }

    /**
     * 固定时间--也就是固定的延迟进行-操作
     * <p>
     * scheduleWithFixedDelay-2021-08-02 21:27:45
     * scheduleWithFixedDelay-2021-08-02 21:27:52
     * scheduleWithFixedDelay-2021-08-02 21:27:55
     * scheduleWithFixedDelay-2021-08-02 21:27:58
     *
     * @param executorService
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void executScheduleWithFixedDelay(ScheduledExecutorService executorService) throws InterruptedException, ExecutionException {
        System.out.println("调度开始时间：" + getTime());
        ScheduledFuture<?> result = executorService.scheduleWithFixedDelay(
                new Runnable() {
                    @Override
                    public void run() {
                        Date d = new Date();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        System.out.println("scheduleWithFixedDelay-" + simpleDateFormat.format(d));
                        try {
                            if (count == 0)
                                Thread.sleep(4000);
                            count++;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println(" 完成时间::::" + DateUtils.defaultMethod(new Date()));
                    }
                }, 1000, 3000, TimeUnit.MILLISECONDS
        );
        Object o = result.get();
        System.out.println(o);
    }

    private static int count = 0;

    // 线程在第4秒开始执行
    public static void executScheduleAtFixedRate(ScheduledExecutorService executorService) throws InterruptedException, ExecutionException {
        System.out.println("调度开始时间：" + getTime());
        ScheduledFuture<?> result = executorService.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        Date d = new Date();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        System.out.println("scheduleWithFixedRate-" + simpleDateFormat.format(d));
                        try {
                            if (count == 0)
                                Thread.sleep(10000);
                            count++;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, 1000, 1000, TimeUnit.MILLISECONDS
        );

        Object o = result.get();
        if (o == null) {
            System.out.println("o =----------- " + o);
        }else {
            System.out.println("o =============== " + o);
        }

    }

    // 线程延迟4秒执行,仅执行一次
    public static void executSchedule(ScheduledExecutorService executorService) throws InterruptedException, ExecutionException {
        System.out.println("调度开始时间：" + getTime());
        ScheduledFuture<?> result = executorService.schedule(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("schedule-" + System.currentTimeMillis());
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, 4000, TimeUnit.MILLISECONDS
        );

        result.get();
    }
}
