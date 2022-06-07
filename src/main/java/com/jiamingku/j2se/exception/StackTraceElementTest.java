package com.jiamingku.j2se.exception;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by jiamingku on 2018/11/2.
 * 获取线程的值栈信息
 * <p>
 * 1.栈追踪信息主要在异常输出的时候会帮助我们锁定问题
 * 在日志的打印时候获取的值栈信息
 * <p>
 * 2. 这个信息是如何获取的呢？
 */
public class StackTraceElementTest {

    public static void main(String[] args) {

        NullPointerException t = new NullPointerException("DDD");

        String s = JSON.toJSONString(t);
        System.out.println("s = " + s);
        throw new NullPointerException("33");
    }

    /**
     * @see java.lang.Throwable#getOurStackTrace
     * @see java.lang.Throwable#getStackTrace
     * @see Thread#currentThread();
     * @see Thread#getStackTrace()
     * 获取代码行数，都是通过异常进行获取的,借助本地方法获取数据的长度，在添加异常信息
     * <p>
     * <p>
     * org.apache.log4j.FileAppender#setFile(java.lang.String, boolean, boolean, int) —输出日志文件的位置就是通过
     * fileoutputstream进行的。文件的名字等
     * <p>
     * <p>
     * @see StackTraceElementTest#getStackTraceByThread()
     * @see StackTraceElementTest#getStackTraceByException()
     * 这个两个方法本质都是通过异常获取的
     */
    @Test
    public void getStackTraceByThread() throws IOException {
        // 记录当前获取信息的栈信息
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            System.out.println("stackTraceElement = " + stackTraceElement);
            System.out.println("stackTraceElement.getClassName() = " + stackTraceElement.getClassName());
            System.out.println("stackTraceElement.getClass() = " + stackTraceElement.getClass());
            System.out.println("stackTraceElement.getFileName() = " + stackTraceElement.getFileName());
            System.out.println("stackTraceElement.getMethodName() = " + stackTraceElement.getMethodName());
            System.out.println("stackTraceElement.getLineNumber() = " + stackTraceElement.getLineNumber());
            System.out.println(" ----------- ");
        }

        System.out.println(" ======== ");

        BufferedWriter bw = new BufferedWriter(new PrintWriter(System.out));

        for (int i = 0; i < stackTraceElements.length; i++)
            bw.write("\tat (" + i + ")" + stackTraceElements[i] + "\n");

        bw.flush();
    }

    @Test
    public void t() {
        Thread.currentThread().getStackTrace();
    }

    @Test
    public void tt() {
        Thread t = new Thread();

        t.getStackTrace();
    }

    @Test
    public void getStackTraceByException() {
        // 记录当前获取信息的栈信息--异常产生的一刻堆栈信息就产生了
        Exception e = new NullPointerException("dd");
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            System.out.println("stackTraceElement = " + stackTraceElement);
        }
    }


    @Test
    public void getStackTraceByThread1() {
        StackTraceElement[] stackTraceElements = getThread();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            System.out.println("stackTraceElement = " + stackTraceElement);
        }
    }

    public StackTraceElement[] getThread() {
        return Thread.currentThread().getStackTrace();
    }


    /**
     * 发生caused 的地方就是异常的包装
     * <p>
     * 其实相当于jdk 给你进行了异常转译
     *
     * @throws IOException
     */
    @Test
    public void getException() throws IOException {
        try {
            String s;
            s = null;
            int a = s.lastIndexOf("d");
        } catch (Exception e) {
            e.printStackTrace();
            IOException ioException = new IOException("111", e);
            // 多级别cause by
            IOException ioException1 = new IOException("111", ioException);
            throw ioException1;
        }
    }


    @Test
    public void getClassName() throws IOException {
        int line = getLine();
        System.out.println("line = " + line);
    }

    public int getLine() {
        return Thread.currentThread().getStackTrace()[2].getLineNumber();
    }

}
