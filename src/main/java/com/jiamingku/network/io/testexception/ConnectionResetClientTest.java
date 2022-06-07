package com.jiamingku.network.io.testexception;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 客户端异常关闭.read方法抛出异常.:java.net.SocketException: Connection reset
 *
 *
 */
public class ConnectionResetClientTest {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("122.51.100.64", 8888);

            int len;
            byte[] bytes = new byte[1024];
            OutputStream os = socket.getOutputStream();
            os.write("hello.服务器, 我是客户端".getBytes());
            /**
             * 异常关闭：read发现连接非-1.
             */
            System.out.println(" 客户端发送完数据-休息10s  " );
//            Thread.sleep(10000000L);
            System.out.println(" 客户端发送完数据-休息10s 结束:  " );
//            socket.shutdownOutput();
            System.out.println(" 然后关闭: socket.shutdownOutput(); " );
            InputStream is = socket.getInputStream();
            while ((len = is.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
