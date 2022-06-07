package com.jiamingku.j2se.oop.getlineclassname;

/**
 * 这种方式获取代码行数，在代码16行的未知上面创建异常获取站消息
 * <p>
 * 这时候战 的顶部元素 是 getInofo
 * <p>
 * <p>
 * 这个方法获取战信息时候。跟进去代码会看到
 * StackTraceElement[] trace = Thread.currentThread().getStackTrace();
 */
public class GetCodeline1 {
    public static void main(String args[]) {
        System.out.println("This is " + getLineInfo());
    }

    public static String getLineInfo() {
        StackTraceElement[] array = new Throwable().getStackTrace();

        StackTraceElement ste = array[1];

        System.out.println("0:" + array[0].getLineNumber());
        return ste.getFileName() + ": Line " + ste.getLineNumber();
    }


}