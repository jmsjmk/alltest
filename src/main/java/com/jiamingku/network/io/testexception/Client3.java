package com.jiamingku.network.io.testexception;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client3 {
    public static void main(String[] args) {
        try {
            Object o = null;
            Socket socket = new Socket();
            InetAddress inetAddress = InetAddress.getByName("www.google.com");
            InetSocketAddress inetSocketAddressHolder = new InetSocketAddress(inetAddress, 80);
            System.out.println("inetAddress = " + inetAddress);
            socket.connect(inetSocketAddressHolder, 1810);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}