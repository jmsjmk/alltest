package com.jiamingku.network.io.testexception;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client4 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",7777);

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            int a= 0;
            int i = 0;
            while ((a = inputStream.read()) != -1) {
                i++;
                if (i % 1000 ==0) {
                    System.out.println("reciv = " + a);
                }

                if (i == 5000) {
                    inputStream.close();
//                    break;
                }
                System.out.println(" = " );
            }
            System.out.println("a = " + a);
            System.out.println("正常关闭::::::::::....");
            Thread.sleep(100000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}