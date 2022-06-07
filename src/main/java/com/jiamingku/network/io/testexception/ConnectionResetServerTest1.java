package com.jiamingku.network.io.testexception;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 */
public class ConnectionResetServerTest1 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = serverSocket.accept();
            System.out.println("服务端接收到了客户端的连接....");
            OutputStream os = socket.getOutputStream();
            os.write("你好，服务端接收到了".getBytes());
            Thread.sleep(10000000L);
            InputStream is = socket.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is));
            int len = 0;
            while ((len = bf.read()) != -1) {
                System.out.print((char) len);
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
