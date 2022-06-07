package com.jiamingku.jvm.oomdemo;

/**
 * 在测试栈溢出的时候。没有启动-Xss大小，你调用也会溢出。所以想知道默认你不加参数的时候启动的大小值
 *
 * -XX:+PrintFlagsFinal--打印出来，搜有的。
 *
 * -XX:PrintCommandLineFlags 但是有些参数你还是打印不出来
 *
 * -Xss默认大小 其实就等于 ：-Xss is an alias for -XX:ThreadStackSize both for OpenJDK and Oracle JDK.
 *
 * https://stackoverflow.com/questions/28767905/what-is-the-difference-between-xss-and-xxthreadstacksize
 *
 * 默认大小1024K
 *
 * Created by jiamingku on 2018/9/23.
 */
public class SelectDefaultJvmParam {

    public static void main(String[] args) throws InterruptedException {

        while(true) {
            Thread.sleep(10000L);
        }
    }
}
