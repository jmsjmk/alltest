package com.jiamingku.network.io;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketBIO {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(9090, 20);
        System.out.println("step1: new ServerSocket(9090) ");
        while (true) {
            Socket client = server.accept();  //阻塞1
            System.out.println("step2:client\t" + client.getPort());
            new Thread(new Runnable() {
                public void run() {
                    InputStream in = null;
                    OutputStream out = null;
                    try {
                        in = client.getInputStream();
                        out = client.getOutputStream();


                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        while (true) {
                            String dataline = reader.readLine(); //阻塞2
                            if (null != dataline) {
                                System.out.println(dataline);
                                byte[] bb = dataline.getBytes(StandardCharsets.UTF_8);
                                out.write(bb);
                            } else {
                                client.close();
                                break;
                            }
                        }
                        System.out.println("客户端断开");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
