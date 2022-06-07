package com.jiamingku.jvm.oomdemo;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiamingku on 2019/6/29.
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024 * 10;

    public static void main(String[] args) throws Exception {
//		Field f = Unsafe.class.getDeclaredFields()[0];
//		f.setAccessible(true);
//		Unsafe unsafe = (Unsafe) f.get("null");
//
//		ByteBuffer.allocate(_1MB);
//		List<ByteBuffer> list = new ArrayList<>();
//		while(true) {
//			unsafe.allocateMemory(_1MB);
//			ByteBuffer b1 = ByteBuffer.allocate(_1MB);
//			list.add(b1);
//			Thread.sleep(3L);
//		}

        //----------------------------上面是堆上分配的内存--------------------------

//        ByteBuffer.allocate(_1MB);
//        List<ByteBuffer> list = new ArrayList<>();
//        int count=0;
//        while (true) {
//            ByteBuffer b1 = ByteBuffer.allocateDirect(_1MB);
//            list.add(b1);
//            Thread.sleep(3L);
//            System.out.println("b1 = " + count++);
//        }

        // ---------------------------------------------------------------


        String path = "/Users/jiamingku/testfileio/out.txt";
        RandomAccessFile raf = new RandomAccessFile(path, "rw");
        raf.write("hello mashibing\n".getBytes());
        raf.write("hello seanzhou\n".getBytes());
        System.out.println("write------------");
        System.in.read();

        raf.seek(4);
        raf.write("ooxx".getBytes());

        System.out.println("seek---------");
        System.in.read();

        FileChannel rafchannel = raf.getChannel();
        //mmap  堆外  和文件映射的   byte  not  objtect
        // MappedByteBuffer map = rafchannel.map(FileChannel.MapMode.READ_WRITE, 0, 4096);
        List<MappedByteBuffer> maps = new ArrayList<>();
        int count = 0;
        while (true) {
            MappedByteBuffer map = rafchannel.map(FileChannel.MapMode.READ_WRITE, 0, 4096 * 1000);
            maps.add(map);
            System.out.println("count++ = " + count++);
        }

    }
}
