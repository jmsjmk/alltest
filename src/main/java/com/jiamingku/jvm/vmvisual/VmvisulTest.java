package com.jiamingku.jvm.vmvisual;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiamingku on 2017/4/27.
 */
public class VmvisulTest {
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];

    }

    public static void fillHeap() {
        List<OOMObject> list = new ArrayList<>();

        for(int i = 0 ; i < 1000; i ++) {
            try {
                Thread.sleep(50L);
                list.add(new OOMObject());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//       System.gc();
//
//        for(int i = 0 ; i < 10000; i ++) {
//            try {
//                Thread.sleep(50L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public static void main(String[] args) {
        fillHeap();

        System.gc();

        for(int i = 0 ; i < 10000; i ++) {
            try {
                Thread.sleep(50L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
