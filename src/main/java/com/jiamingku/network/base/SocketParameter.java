package com.jiamingku.network.base;

import java.net.Socket;
import java.net.SocketException;
import java.net.SocketOptions;

/**
 * {@link SocketOptions}
 * <p>
 * TCP_NODELAY: 表示立即发送数据.
 *
 * SO_RESUSEADDR: 表示是否允许重用Socket 所绑定的本地地址.
 *
 * SO_TIMEOUT: 表示接收数据时的等待超时数据.
 *
 * SO_LINGER: 表示当执行Socket 的 close()方法时, 是否立即关闭底层的Socket.
 * <p>
 * <p>
 *
 * SO_SNFBUF: 表示发送数据的缓冲区的大小.
 *
 * SO_RCVBUF: 表示接收数据的缓冲区的大小.
 * <p>
 * <p>
 * --
 * SO_KEEPALIVE: 表示对于长时间处于空闲状态的Socket是否要自动把它关闭.
 * <p>
 * --
 * OOBINLINE: 表示是否支持发送一个字节的TCP 紧急数据.
 * <p>
 * =====
 * 上面的所有参数属性都是通过本地方法进行设置的.应该属于网络层的参数.
 */
public class SocketParameter {

    public static void main(String[] args) {
        try {
            Socket socket = null;
            socket.setTcpNoDelay(false);
            socket.setSendBufferSize(3000);
            socket.setOOBInline(false);

            socket.setSoTimeout(111);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
