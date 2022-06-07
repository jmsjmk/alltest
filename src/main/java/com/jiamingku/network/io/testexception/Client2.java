package com.jiamingku.network.io.testexception;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client2 {
    Socket client;
    PrintWriter pw;
    String ip = "127.0.0.1";
    int port = 7777;
    byte[] data = null;
    String str = "hello";

    public Client2() throws UnknownHostException, IOException {
        client = new Socket(ip, port);
        OutputStream os = client.getOutputStream();
        InputStream is = client.getInputStream();
        data = str.getBytes();
        os.write(data);//这个地方注意,就是客户端不写入流，服务器的read方法就一直阻塞

        int totalbytesRcvd = 0;
        int byteRcvd;

        while (totalbytesRcvd < data.length) {
            // 同样的道理服务器没有发送信息，客户端这里也是阻塞的
            if ((byteRcvd = is.read(data, totalbytesRcvd, data.length - totalbytesRcvd)) == -1) {
                System.out.println("exception");
            }
            totalbytesRcvd += byteRcvd;
        }

        //System.out.println(new String(data));
        client.close();
    }

    public static void main(String[] args) {
        try {
            new Client2();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}