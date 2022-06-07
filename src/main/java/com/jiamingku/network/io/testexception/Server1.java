package com.jiamingku.network.io.testexception;

import com.jiamingku.thead.threadpool.schedule.DateUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.*;
import java.util.Arrays;
import java.util.Date;

/**
 * bio会出现阻塞,所以呢？soTime的值就是在对应的阻塞过程中都期作用
 * read.阻塞.
 */
public class Server1 {
    public static void main(String[] args) {
        try {
            SocketAddress address = new InetSocketAddress("127.0.0.1", 30001);
            ServerSocket ss = new ServerSocket();
            ss.bind(address);
            System.out.println("准备接收连接.......");
            Socket s = ss.accept();
            new Thread(new T(s)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class T implements Runnable {
    public void run() {
        try {
            System.out.println(socket.toString());
            socket.setKeepAlive(true);
            socket.setSoTimeout(5 * 1000);
            while (true) {
                System.out.println("开始---时间:::::" + DateUtils.defaultMethod(new Date()));
                try {
                    InputStream ips = socket.getInputStream();
                    ByteArrayOutputStream bops = new ByteArrayOutputStream();
                    int data = -1;
                    while ((data = ips.read()) != -1) {
                        System.out.println(data + "接收到的时间::" + DateUtils.defaultMethod(new Date()));
                        bops.write(data);
                    }
                    System.out.println(Arrays.toString(bops.toByteArray()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                Thread.sleep(100);
                System.out.println("socket.isBound():" + socket.isBound()); //
                System.out.println("socket.isClosed():" + socket.isClosed()); //
                System.out.println("socket.isConnected(): " + socket.isConnected()); //
                System.out.println("socket.isInputShutdown():" + socket.isInputShutdown()); //
                System.out.println("socket.isOutputShutdown():" + socket.isOutputShutdown()); //
                System.out.println("结束时间" + DateUtils.defaultMethod(new Date()));
                System.out.println("");
                System.out.println("");
                System.out.println("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Socket socket = null;

    public T(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}