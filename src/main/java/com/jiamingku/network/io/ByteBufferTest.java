package com.jiamingku.network.io;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;

/**
 * Created by jiamingku on 2019/7/17.
 */
public class ByteBufferTest {




    @Test
    public void testNio() {
        //        ByteBuffer buffer = ByteBuffer.allocate(1024);
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        System.out.println("postition: " + buffer.position());
        System.out.println("limit: " + buffer.limit());
        System.out.println("capacity: " + buffer.capacity());
        System.out.println("mark: " + buffer);

        buffer.put("123".getBytes());

        System.out.println("-------------put:123......");
        System.out.println("mark: " + buffer);

        buffer.flip();   //读写交替

        System.out.println("-------------flip......");
        System.out.println("mark: " + buffer);

        buffer.get();

        System.out.println("-------------get......");
        System.out.println("mark: " + buffer);

        buffer.compact();

        System.out.println("-------------compact......");
        System.out.println("mark: " + buffer);

        buffer.clear();

        System.out.println("-------------clear......");
        System.out.println("mark: " + buffer);


        buffer.mark();
    }


    public static void main(String[] args) throws Exception {
        File f = new File("test.1");
        FileInputStream fileInputStream = new FileInputStream(f);
        int a = 0;
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        while ((a = fileInputStream.read()) != -1) {
            byteBuffer.put((byte) a);
        }
        System.out.println("byteBuffer = " + byteBuffer);
        byteBuffer.flip();
        System.out.println("byteBuffer = " + byteBuffer);
        byte[] srcbytes = byteBuffer.array();


        String str = new String(srcbytes);
        System.out.println("str = " + str);


        int p = byteBuffer.position();
        int l = byteBuffer.limit();
        byte[] bbbb = new byte[l];
        System.arraycopy(srcbytes, p, bbbb, 0, l + 1);


        String str1 = new String(bbbb);
        System.out.println("str1 = " + str1);

    }

    @Test
    public void test1() {
        try {

            int a = 0xFFFF;
            System.out.println("a = " + a);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
