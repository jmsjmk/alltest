package com.framework.log;

/**
 * 首先要明白标准输入输出
 * java Print 2>> 1.txt  ---error会在1.txt中
 * java Print 1>> 1.txt  ---out会在日志中
 * java Print >> 1.txt 2>&1  --- error,out都会出现在日志中
 *
 * ----必须要了解的就是异常
 *
 */
public class Consol {
    public static void main(String[] args) {
        System.out.println("out");
        System.err.println("error");
    }
}
