package com.framework.log;

import org.junit.Test;

public class PrintColor {
    public static void main(String[] args) {
//        System.out.println("\033[30;4m" + "我滴个颜什" + "\033[0m");
//        System.out.println("\033[31;4m" + "我滴个颜什" + "\033[0m");
//        System.out.println("\033[32;4m" + "我滴个颜什" + "\033[0m");
//        System.out.println("\033[33;4m" + "我滴个颜什" + "\033[0m");
//        System.out.println("\033[34;4m" + "我滴个颜什" + "\033[0m");
//        System.out.println("\033[35;4m" + "我滴个颜什" + "\033[0m");
//        System.out.println("\033[36;4m" + "我滴个颜什" + "\033[0m");
//        System.out.println("\033[37;4m" + "我滴个颜什" + "\033[0m");
//        System.out.println("\033[40;31;4m" + "我滴个颜什" + "\033[0m");
//        System.out.println("\033[41;32;4m" + "我滴个颜什" + "\033[0m");
//        System.out.println("\033[42;33;4m" + "我滴个颜什" + "\033[0m");
//        System.out.println("\033[43;34;4m" + "我滴个颜什" + "\033[0m");
//        System.out.println("\033[44;35;4m" + "我滴个颜什" + "\033[0m");
//        System.out.println("\033[45;36;4m" + "我滴个颜什" + "\033[0m");
//        System.out.println("\033[46;37;4m" + "我滴个颜什" + "\033[0m");
//        System.out.println("\033[47;4m" + "我滴个颜什" + "\033[0m");
        System.err.println("ddddddddddddd=======================================================================ddddddd");
        // https://www.cnblogs.com/wenxin1120/p/10908323.html
        System.err.println("\033[0;30;0m" + "我滴个颜什" + "\033[0m");
        System.err.println("\033[0;30;4m" + "11111111111111111111111111111我滴个颜什" + "\033[0m");
        System.err.println("[30;4m" + "我滴个颜什" + "[0m");
        System.err.println("\033[31;4m" + "我滴个颜什" + "\033[0m");
        System.err.println("\033[32;4m" + "我滴个颜什" + "\033[0m");
        System.err.println("\033[33;4m" + "我滴个颜什" + "\033[0m");
        System.err.println("\033[34;4m" + "我滴个颜什" + "\033[0m");
        System.err.println("\033[35;4m" + "我滴个颜什" + "\033[0m");
        System.err.println("\0FinallyReturnException33[36;4m" + "我滴个颜什" + "\033[0m");
        System.err.println("\033[37;4m" + "我滴个颜什" + "\033[0m");
        System.err.println("\033[40;31;4m" + "我滴个颜什" + "\033[0m");
        System.err.println("\033[41;32;4m" + "我滴个颜什" + "\033[0m");
        System.err.println("\033[42;33;4m" + "我滴个颜什" + "\033[0m");
        System.err.println("\033[43;34;4m" + "我滴个颜什" + "\033[0m");
        System.err.println("\033[44;35;4m" + "我滴个颜什" + "\033[0m");
        System.err.println("\033[45;36;4m" + "我滴个颜什" + "\033[0m");
        System.err.println("\033[46;37;4m" + "我滴个颜什" + "\033[0m");
        System.err.println("\033[47;4m" + "我滴个颜什" + "\033[0m");
    }

    @Test
    public void test1() {
        System.err.println("\033[0;30;2m" + "11111111111111111111111111111我滴个颜什" + "\033[0m");
        System.err.println("\033[1;30;3m" + "11111111111111111111111111111我滴个颜什" + "\033[0m");
        System.err.println("\033[4;30;6m" + "11111111111111111111111111111我滴个颜什" + "\033[0m");
        System.err.println("\033[5;30;1m" + "11111111111111111111111111111我滴个颜什" + "\033[0m");
        System.err.println("\033[7;30;4m" + "11111111111111111111111111111我滴个颜什" + "\033[0m");
        System.err.println("\033[8;30;4m" + "11111111111111111111111111111我滴个颜什" + "\033[0m");
    }
}
