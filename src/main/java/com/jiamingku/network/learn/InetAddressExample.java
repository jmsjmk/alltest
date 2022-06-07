package com.jiamingku.network.learn;

import org.junit.Test;

import java.util.Enumeration;
import java.net.*;

/**
 * 简单的说：获取网卡，网卡的地址信息.
 */
public class InetAddressExample {

    public static void main(String[] args) {
        try {
            String nnn = InetAddress.getLocalHost().getHostName();
            System.out.println("nnn = " + nnn);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        // Get the network interfaces and associated addresses for this host
        try {
            Enumeration<NetworkInterface> interfaceList = NetworkInterface.getNetworkInterfaces();
            if (interfaceList == null) {
                System.out.println("--No interfaces found--");
            } else {
                while (interfaceList.hasMoreElements()) {
                    NetworkInterface iface = interfaceList.nextElement();
                    System.out.println("Interface " + iface.getName() + ":");
                    Enumeration<InetAddress> addrList = iface.getInetAddresses();
                    if (!addrList.hasMoreElements()) {
                        System.out.println("\t(No addresses for this interface)");
                    }
                    while (addrList.hasMoreElements()) {
                        InetAddress address = addrList.nextElement();
                        System.out.print("\tAddress "
                                + ((address instanceof Inet4Address ? "(v4)"
                                : (address instanceof Inet6Address ? "(v6)" : "(?)"))));
                        System.out.println(": " + address.getHostAddress());
                    }
                }
            }
        } catch (SocketException se) {
            System.out.println("Error getting network interfaces:" + se.getMessage());
        }

        // Get name(s)/address(es) of hosts given on command line
        String[] args1 = {"jiamingkudeMacBook.local", "www.baidu.com"};
        for (String host : args1) {
            try {
                System.out.println(host + ":");
                InetAddress[] addressList = InetAddress.getAllByName(host);
                for (InetAddress address : addressList) {
                    System.out.println("\t" + address.getHostName() + "/" + address.getHostAddress());
                }
            } catch (UnknownHostException e) {
                System.out.println("\tUnable to find address for " + host);
            }
        }
    }

    @Test
    public void test() {
        try {
            InetAddress[] addresses = InetAddress.getAllByName("jiamingkudeMacBook.local");

            for (InetAddress inetAddress : addresses) {
                System.out.println("inetAddress = " + inetAddress.getHostAddress());
                System.out.println("inetAddress.getHostName() = " + inetAddress.getHostName());
            }

            System.out.println("===== ");

            InetAddress inetAddress1 = InetAddress.getByName("www.baidu.com");
            System.out.println("inetAddress1 = " + inetAddress1);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {


            InetAddress inetAddress1 = InetAddress.getByName("jiamingkudeMacBook.local");
            String name1 = inetAddress1.getCanonicalHostName();
            System.out.println("name1 = " + name1);
            String name2 = inetAddress1.getHostName();
            System.out.println("name2 = " + name2);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test3() {
        try {
            InetAddress inetAddress1 = InetAddress.getByName("http://sports.sina.cn");
            System.out.println("inetAddress1 = " + inetAddress1);
            boolean b = inetAddress1.isReachable(10000000);
            System.out.println("b = " + b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
