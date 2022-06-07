package com.jiamingku.network.io.testexception;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 客户端异常关闭.read方法抛出异常.:java.net.SocketException: Connection reset
 *
 */
public class ConnectionResetClientTest1 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8888);

            int len;
            byte[] bytes = new byte[1024];
            OutputStream os = socket.getOutputStream();
            os.write("hello.服务器, 我是客户端".getBytes());
            InputStream is = socket.getInputStream();
            while ((len = is.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
