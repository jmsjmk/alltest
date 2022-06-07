package com.jiamingku.md5;

import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jiamingku on 2019/8/19.
 */
public class SnowflakeTest {

    /**
     * 时间的长度最长就是41位
     */
    @Test
    public void test1() {
        long dateLong = System.currentTimeMillis();
        String str = Long.toBinaryString(dateLong);
        System.out.println("str = " + str);
        System.out.println("str.length() = " + str.length());
    }

    /**
     * 接触过的系统时间都是从1970年1月1日 08 时间开始的
     *
     * 雪花算法最多使用69年，我操的已经够用了
     * @throws Exception
     */
    @Test
    public void test2() throws Exception{
        String s = "1970-01-01 00:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = simpleDateFormat.parse(s);
        System.out.println("d = " + d);
        long a = d.getTime();
        System.out.println("a = " + a);
        System.out.println(" ========================== ");
        System.out.println(" ========================== ");
        System.out.println(" ========================== ");
        long l =0L;
        Date date = new Date(l);
        String s1 = simpleDateFormat.format(date);
        System.out.println("s1 = " + s1);
        System.out.println(" ========================== ");
        System.out.println(" ========================== ");
        System.out.println(" ========================== ");

        String s2 = "11111111111111111111111111111111111111111";
        long a2 = Long.parseLong(s2,2);
        System.out.println("a2 = " + a2);

        Date dd = new Date(2199023255551L);
        String sd =simpleDateFormat.format(dd);
        System.out.println(sd);

    }

    @Test
    public void test3() {
        int a = 1;
        long  b =1;
        long c = 121212121233L;
        a = a >>32;
        System.out.println("c = " + a);
        float f  = 3;
        // t >>4; java中的移位操作只对int和long有效，byte、short、char升级为int后再进行移位

    }

    @Test
    public void test4() {
        long id = 0L;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                id = ((0x000000FF & (long) mac[mac.length - 1])
                        | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
//                id = id % (maxDatacenterId + 1);
            }
        } catch (Exception e) {
            System.out.println(" getDatacenterId: " + e.getMessage());
        }
        System.out.println("id = " + id);
    }

    @Test
    public void test5() {
        long datacenterId=31;
        long maxWorkerId =31;
        StringBuffer mpid = new StringBuffer();
        mpid.append(datacenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (!name.isEmpty()) {
         /*
          * GET jvmPid
          */
            mpid.append(name.split("@")[0]);
        }
      /*
       * MAC + PID 的 hashcode 获取16个低位
       */
        long l = (mpid.toString().hashCode() & 0xffff) % (maxWorkerId + 1);
        System.out.println("l = " + l);
    }

}
