package com.jiamingku.thead.park;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.LockSupport;

/**
 * 阻塞线程到执行的时间：
 */
public class ParkUntil {

    public static void main(String[] args) throws Exception {
        ParkUntil parkUntil = new ParkUntil();
        new Thread(() -> {
            try {
                parkUntil.a("2021-07-28 08:17:40");
            } catch (Exception E) {
                E.printStackTrace();
            }
        }, "a1").start();

        new Thread(() -> {
            System.out.println("线程b准备park--0");
            LockSupport.parkNanos(0);
            System.out.println("线程b准备park--0----------");
        }, "线程B").start();
        Thread.sleep(1000000L);
    }

    public void a(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = simpleDateFormat.parse(date);
        System.out.println("线程准备休息到:" + date);
        LockSupport.parkUntil(d.getTime());
        System.out.println("线程唤醒了：当前系统时间：" + simpleDateFormat.format(new Date()));
    }
}
