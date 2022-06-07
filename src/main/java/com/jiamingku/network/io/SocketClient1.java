package com.jiamingku.network.io;

import com.jiamingku.network.base.SocketParameter;

import java.io.*;
import java.net.Socket;

/**
 * 如果服务端 control + c 了 这边在写的话 （Broken pipe (Write failed))
 */
public class SocketClient1 {

    public static void main(String[] args) {

        try {
            Socket client = new Socket("122.51.100.64", 9090);

            /**
             * {@link SocketParameter}
             *
             * client.setTcpNoDelay(true);     --是否组包发送--
             *
             * client.setSendBufferSize(15);   --发送缓存区大小--
             //             */
//            client.setSendBufferSize(15);
//
//            client.setTcpNoDelay(false);
            OutputStream out = client.getOutputStream();
            InputStream II = client.getInputStream();
            InputStream in = System.in;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            while (true) {
                String line = reader.readLine();
                System.out.println("line = " + line);
                if (line != null) {
                    if (line.equals("no")) {
                        client.close();
                        try {
                            Thread.sleep(15000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    byte[] bb = line.getBytes();
                    for (byte b : bb) {
                        out.write(b);
                        System.out.println(" = ");
                    }
                    out.write(13);


                    System.out.println(" 写完成... ");


                }

//                Thread.sleep(1000 * 15);
//                break;
            }
//            client.close();

//            System.out.println(" 主动关闭连接.........客户端......");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
