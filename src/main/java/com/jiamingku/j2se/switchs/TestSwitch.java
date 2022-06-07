package com.jiamingku.j2se.switchs;

import org.junit.Test;

/**
 * Created by jiamingku on 16/12/2.
 */
public class TestSwitch {
    /**
     * switch会产生掉落的情况
     *
     * @param args
     */
    public static void main(String[] args) {
        int code = 5;
        switch (code) {
            case 3:
                System.out.println(3);
            case 5:
                System.out.println(5);
            case 4:
                System.out.println(4);
                break;
            case 31:
                System.out.println(31);
            default:
                System.out.println("default");
        }
    }

    @Test
    public void testIong() {
        String code = "3";
        switch (code) {
            case "3":
                System.out.println(3);
            default:
                System.out.println("default");
        }
    }
}
