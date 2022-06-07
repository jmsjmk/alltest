package com.jiamingku.network.io.testexception;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class Server4 {

    /**
     * <p></p>
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(7777);

            int rcvdMsgSize;

            byte[] buff = new byte[30];


            Socket s = ss.accept();
            int i = 0;
            while (true) {
                i++;
                SocketAddress sa = s.getRemoteSocketAddress();
                OutputStream os = s.getOutputStream();
                InputStream is = s.getInputStream();

                os.write(1);
                if (i % 1000  == 0) {
                    System.out.println("is = " + is);
                }
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}