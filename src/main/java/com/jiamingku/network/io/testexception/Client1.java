package com.jiamingku.network.io.testexception;

import java.net.Socket;

public class Client1 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 30001);
            socket.setKeepAlive(true);
            while (true && null != socket) {
                Thread.sleep(8 * 1000);
                socket.getOutputStream().write(12);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}