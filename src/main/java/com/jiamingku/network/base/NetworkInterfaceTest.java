package com.jiamingku.network.base;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 取网卡的配置信息：ifconfig对比
 */
public class NetworkInterfaceTest {
    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> interfaceEnumeration = NetworkInterface.getNetworkInterfaces();
            while (interfaceEnumeration.hasMoreElements()) {
                NetworkInterface networkInterface = interfaceEnumeration.nextElement();
                System.out.println("networkInterface = " + networkInterface.getName());
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
