package com.jiamingku.j2se.exception;

import org.junit.Test;

import java.util.Stack;

/**
 * Created by jiamingku on 2018/11/2.
 * 获取线程的值栈信息
 * <p>
 * 1.栈追踪信息主要在异常输出的时候会帮助我们锁定问题
 * 在日志的打印时候获取的值栈信息
 * <p>
 * 2. 这个信息是如何获取的呢？
 */
public class StackTraceElementBase {

    public static void main(String[] args) {
        System.out.println(" = ");
        Stack s = new Stack();
        a();
    }

    public static void a() {
        if (true) {
            throw new NullPointerException("ddd");
        }
    }

    @Test
    public void printException() {
        NullPointerException nullPointerException = new NullPointerException("33");
        System.out.println(nullPointerException);
        System.out.println("=============================== ");
        nullPointerException.printStackTrace();
    }
}
