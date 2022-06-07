package com.jiamingku.jvm.oomdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiamingku on 2017/4/21.
 * <p>
 * <p>
 * https://blog.csdn.net/yz18931904/article/details/89445484 ===测试时候出现了一个demo
 * <p>
 * 这个方法在jdk 1。7 以后可能在不好事了。
 */
public class RuntimeConstantPoolStringOOM {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
            list.add(String.valueOf("sdfsdfsdfsdfsdsdsddsdsdsdsdsdsdssdf" + i++).intern());
        }
    }
}
