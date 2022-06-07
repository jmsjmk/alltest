package com.jiamingku.thead.base;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * 1) 秒->毫秒->微秒->纳秒(ns)
 * <p>
 * unix时间戳是距离1970年的秒数
 * java的日期获取的时间是毫秒--整整差了1000倍 处理就可以获取到了
 * <p>
 * System.nanoTime(): 此方法只能用于测量经过的时间，并且与系统或挂钟时间的任何其他概念无关。
 * 简单理解就是处理精度很高的计算
 * <p>
 * Created by jiamingku on 2017/4/10.
 */
public class TimeUnitTest {

    /**
     * 同mysql的unix_timestamp函数
     */
    public static int unixTimestamp(long ts) {
        return (int) (ts / 1000);
    }

    /**
     * 同mysql的unix_timestamp函数
     * SELECT UNIX_TIMESTAMP();
     * SELECT FROM_UNIXTIME(1619144984);
     */
    public static int unixTimestamp(Date date) {
        if (date == null) {
            return 0;
        }
        return unixTimestamp(date.getTime());
    }

    /**
     * 枚举类的转换方法:convert
     */
    @Test
    public void test() {
        //3600分钟 转换成 小时 是多少
        System.out.println(TimeUnit.HOURS.convert(3600, TimeUnit.MINUTES));
        //3600分钟 转换成 天 是多少
        System.out.println(TimeUnit.DAYS.convert(2880, TimeUnit.MINUTES));
        //3600分钟 转换成 秒 是多少
        System.out.println(TimeUnit.SECONDS.convert(3600, TimeUnit.MINUTES));
        long a = TimeUnit.MILLISECONDS.convert(1, TimeUnit.SECONDS);
        System.out.println("a = " + a);
    }

    @Test
    public void testToTime() {
        //1天有24个小时    1代表1天：将1天转化为小时
        //结果： 24
        System.out.println(TimeUnit.DAYS.toHours(1));
        System.out.println(TimeUnit.DAYS.toHours(-2));
        //1小时有3600秒
        //结果3600
        System.out.println(TimeUnit.HOURS.toSeconds(1));
        //把3天转化成小时
        //结果是：72
        System.out.println(TimeUnit.HOURS.convert(3, TimeUnit.DAYS));
    }


    @Test
    public void unixTime() {
        Date date = new Date();
        long l = date.getTime();
        // -- db存的时间 1522650017
        // -- db存的时间 1594357729383
        long l2 = System.currentTimeMillis();
        System.out.println("= " + l2);
        System.out.println("= " + l);
        long a = 9223372036854775807L;
        System.out.println("a = " + a);
        System.out.println("= " + System.nanoTime());

        // 157618497375600
        // 9223372036854775807
    }


    public static void main(String[] args) {
        Date d = new Date();
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d21 = dateFormat1.format(d);
        System.out.println(d21);
        long a = d.getTime() + (30 * 1000);
        Date d1 = new Date(a);
        System.out.println(a / 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d2 = dateFormat.format(d1);
        System.out.println(d2);
    }

    @Test
    public void testDelay() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5 * 1000);
                    System.out.println("延时完成了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("延时5秒，完成了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // -----------------------------------------------------------------------------------
    //  jdk8
    //  jdk8
    // -----------------------------------------------------------------------------------

    @Test
    public void testPeriod() {
        LocalDate startDate = LocalDate.of(2015, 2, 20);
        LocalDate endDate = LocalDate.of(2017, 1, 20);
        Period period = Period.between(startDate, endDate);
        System.out.println("Years:" + period.getYears() +
                " months:" + period.getMonths() +
                " days:" + period.getDays());
    }

    @Test
    public void testPeriodA() {
        long a = TimeUnit.SECONDS.toNanos(-1);
        System.out.println(a);
        long b = TimeUnit.SECONDS.toNanos(1);
        System.out.println(b);
    }

    @Test
    public void testDuration() {
        Instant start = Instant.parse("2017-10-03T10:15:30.00Z");
        Instant end = Instant.parse("2017-10-03T10:16:39.00Z");

        Duration duration = Duration.between(start, end);

        long a = duration.getSeconds();

        System.out.println(a);

        Duration fromDays = Duration.ofDays(1);

        System.out.println(fromDays.getSeconds());

        Duration DEFAULT_MAX_WAIT = Duration.ofMillis(21111);
        System.out.println(DEFAULT_MAX_WAIT.getSeconds());
    }


    @Test
    public void test111() {

        Instant startTime = Instant.now();

        // 主要是Period类方法getYears（），getMonths（）和getDays（）来计算.
        LocalDate today = LocalDate.now();
        System.out.println("Today : " + today);
        LocalDate birthDate = LocalDate.of(1996, Month.MARCH, 3);
        System.out.println("BirthDate : " + birthDate);

        Period p = Period.between(birthDate, today);
        System.out.printf("年龄 : %d 年 %d 月 %d 日", p.getYears(), p.getMonths(), p.getDays());
        System.out.println("\n");
        System.out.println();
        System.out.println("------------------华丽分割线-------------------------------");

        // 提供了使用基于时间的值（如秒，纳秒）测量时间量的方法
        Instant inst1 = Instant.now();
        System.out.println("Inst1 : " + inst1);
        Instant inst2 = inst1.plus(Duration.ofSeconds(10));
        System.out.println("Inst2 : " + inst2);

        System.out.println("Difference in milliseconds : " + Duration.between(inst1, inst2).toMillis());

        System.out.println("Difference in seconds : " + Duration.between(inst1, inst2).getSeconds());


        System.out.println("------------------华丽分割线-------------------------------");

        // ChronoUnit类可用于在单个时间单位内测量一段时间，例如天数或秒。
        LocalDate startDate = LocalDate.of(1996, Month.MARCH, 3);
        System.out.println("开始时间  : " + startDate);

        LocalDate endDate = LocalDate.now();
        System.out.println("结束时间 : " + endDate);

        long daysDiff = ChronoUnit.DAYS.between(startDate, endDate);
        System.out.println("两天之间的差在天数   : " + daysDiff);

        // 程序结束时间
        Instant endTime = Instant.now();
        long millis = ChronoUnit.MILLIS.between(startTime, endTime);
        long micros = ChronoUnit.MICROS.between(startTime, endTime);
        long seconds = ChronoUnit.SECONDS.between(startTime, endTime);
        long minutes = ChronoUnit.MINUTES.between(startTime, endTime);
        long hours = ChronoUnit.HOURS.between(startTime, endTime);

        System.out.println("程序消耗时间(毫秒)" + millis);
        System.out.println("程序消耗时间(微秒)" + micros);
        System.out.println("程序消耗时间(秒)" + seconds);
        System.out.println("程序消耗时间(分钟)" + minutes);
        System.out.println("程序消耗时间(小时)" + hours);

        System.out.println("=====================");
        System.out.println("=====================");
        System.out.println("=====================");
        System.out.println("=====================");
        System.out.println("=====================");

        System.out.println("=====================");
        long a = NANOSECONDS.toMicros(500000);
        System.out.println(a);




    }
}
