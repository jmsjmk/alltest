package com.jiamingku.network.io.testexception;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class Server2 {

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

            while (true) {

                Socket s = ss.accept();

                SocketAddress sa = s.getRemoteSocketAddress();

                System.out.println("remote address:" + sa);

                OutputStream os = s.getOutputStream();
                InputStream is = s.getInputStream();

                while ((rcvdMsgSize = is.read(buff)) != -1) {


                    os.write(buff, 0, rcvdMsgSize);
                }
                s.close();
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}