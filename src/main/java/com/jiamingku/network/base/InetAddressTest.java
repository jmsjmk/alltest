package com.jiamingku.network.base;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddress--就代表一个ip地址
 */
public class InetAddressTest {

    public static String ip;
    public static String hostname;

    @Test
    public void test1() {
        InetAddress addr = null;
        try {
            // 通过主机名进行-获取ip地址
            addr = InetAddress.getLocalHost();
            ip = addr.getHostAddress();
            hostname = addr.getHostName();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("ip = " + ip);
        System.out.println("hostname = " + hostname);
    }

    /**
     * 通过一个名字获取一个ip地址？
     */
    @Test
    public void test2() {
        String ipStr = "";
        try {
            InetAddress ia = InetAddress.getByName("imagecut");
            ipStr = ia.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println("ipStr = " + ipStr);
    }


    /**
     * 充当域名解析的工作
     */
    @Test
    public void test3() {
        String ipStr = "";
        try {
            InetAddress ia = InetAddress.getByName("www.baidu.com");
            ipStr = ia.getHostAddress();
            String name = ia.getHostName();
            System.out.println("name = " + name);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println("ipStr = " + ipStr);
    }

    /**
     * 1.困惑已久的：连接地址配置域名的问题(里面会有一个域名解析的过程在里面)
     */
    @Test
    public void test4() {
        String ipStr = "";
        try {
            InetAddress ia = InetAddress.getByName("mysql-m-wr-car-all-dev-new-db.01zhuanche.com");
            ipStr = ia.getHostAddress();
            System.out.println("ipStr = " + ipStr);
            String name = ia.getHostName();
            System.out.println("name = " + name);
            String nnnn = InetAddress.getByName("10.130.10.32").getHostName();
            System.out.println("nnnn = " + nnnn);
            String nn1nn = InetAddress.getByName("10.130.10.32").getHostAddress();
            System.out.println("nn1nn = " + nn1nn);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println("ipStr = " + ipStr);
    }
}