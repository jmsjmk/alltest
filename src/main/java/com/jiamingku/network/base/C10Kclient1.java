package com.jiamingku.network.base;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

/**
 * 同一个socket 链接。被多个线程把持住之后,
 * <p>
 * 写是线程同步的.
 */
public class C10Kclient1 {

    public static void main(String[] args) throws Exception {
        LinkedList<SocketChannel> clients = new LinkedList<>();


        //  Socket("122.51.100.64", 9090);
        InetSocketAddress serverAddr = new InetSocketAddress("122.51.100.64", 9090);

        SocketChannel client1 = SocketChannel.open();
//        client1.configureBlocking(false);
        client1.connect(serverAddr);

//        client1.isOpen()
        while (!client1.isConnected()) {
            Thread.sleep(1000L);
            System.out.println(".");
        }


        new Thread() {
            @Override
            public void run() {
                try {

                    ByteBuffer buffer = ByteBuffer.allocate(1100000);
                    while (true) {
                        buffer.clear();
                        StringBuilder stringBuilder = new StringBuilder();
                        int i = 0;
                        while (i < 1000) {
                            i++;
                            stringBuilder.append("1");
                        }
                        stringBuilder.append("\n");
                        buffer.put(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
                        buffer.flip();
                        client1.write(buffer);

                        client1.read(buffer);
//                        Thread.sleep(10L);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {

                    ByteBuffer buffer = ByteBuffer.allocate(1100);
                    while (true) {
                        buffer.clear();
                        StringBuilder stringBuilder = new StringBuilder();
                        int i = 0;
                        while (i < 1000) {
                            i++;
                            stringBuilder.append("2");
                        }
                        stringBuilder.append("\n");
                        buffer.put(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
                        buffer.flip();
                        client1.write(buffer);
//                        Thread.sleep(10L);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        System.out.println("clients " + clients.size());

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
