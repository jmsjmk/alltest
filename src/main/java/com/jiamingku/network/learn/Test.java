package com.jiamingku.network.learn;

import com.rabbitmq.tools.json.JSONUtil;

public class Test {

    public static void main(String[] args) {
        byte b = -1;
        int a = b & 0xff;
        System.out.println("a = " + a);
        
        int cc = 0x5400;
        int dd = 0xfc00;


        System.out.println("Integer.toBinaryString(cc) = " + Integer.toBinaryString(dd));
    }


}

// 1111     1100         0000    0000