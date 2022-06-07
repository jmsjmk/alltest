package com.jiamingku.jvm.oomdemo;

import org.junit.Test;

/**
 * java栈大小是动态的或者固定不变的(是单个线程的大小不是总体大小)
 * 固定:
 * -Xss256  :length 1889 ("main" java.lang.StackOverflowError),此参数有尺寸限制
 * 固定: java虚拟机可以动态扩展,如果空间无法申请可以出现oom
 *
 * @see JavaVMStackOOM
 *
 * @see JavaVMStackSOFManyThread
 *
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/jiamingku/Desktop/PPP1212.hprof
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    /**
     * Exception in thread "main" java.lang.StackOverflowError
     * 递归超过一定数量就爆出来异常了。
     *
     * @param a
     */
    public void stackLeak(int a) {
        stackLength++;
        if (stackLength < 157500)
            stackLeak(a);
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable t) { //-----这个地方用异常是抓不到错误消息的,注意******
            System.out.println("stack length:" + oom.stackLength);
            throw t;
        }
    }

    @Test
    public void DiGui() {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak(10000);
        } catch (Throwable t) {
            System.out.println("stack length:" + oom.stackLength);
            throw t;
        }
    }
}