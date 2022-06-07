package com.jiamingku.j2se.basetype;


import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by jiamingku on 2018/11/23.
 * 经度丢失
 */
public class LossOfLongitude {

    /**
     * 数据明显的丢失了一些东西,数据精度不准的问题
     */
    @Test
    public void testIntToFloat() {
        int a = 167772199;
        float b = a;
        System.out.println("b = " + b);
        System.out.println(16777219399f);
    }

    /**
     * 明显能显示数据的的
     * <p>
     * 10.0f-9.6f 的时候剩下 0.4用二进制表示0.4是个无线循环小数，
     * 所以出现精度问题，就是是为什么会出现精度丢失的原因
     */
    @Test
    public void testJian() {
        float a = 10.0f;
        float b = 9.6f;
        float c = a - b;
        System.out.println("c = " + c);
    }

    @Test
    public void testFloat1() {
        float a = 1222.67777777777777777777F;
        float a1 = 1222.67779F;
        System.out.println(a);
        System.out.println("a1 = " + a1);
        float e = 1.6777778F;
        float b = 1.6777777F;
        float c = 1.6777776F;
        float d = 1.6777775F;
        float d1 = 1.6777774F;
        System.out.println("==================");
        System.out.println("a:" + a);
        System.out.println("e:" + e);
        // b = 1.6777777F; 但是打印 b:1.6777776
        System.out.println("b:" + b);
        System.out.println("c:" + c);
        System.out.println("d:" + d);
        System.out.println("d1:" + d1);
    }

    /**
     * 在没有指数出现的情况 (科学计数法 )。：小数点后面最多 8位。上面是 5四舍五入过来， 9进 1的结果。
     * 有效数字是8位 ，
     */
    @Test
    public void testFloat2() {
        float a = 1.23456795F;
        // 1.23456780
        System.out.println("a = " + a);

        float a1 = 1.23456794F;
        // 1.23456780
        System.out.println("a1 = " + a1);
    }

    /**
     * 所以在使用浮点数计算的时候就直接给他弄上 bigdecimal就可以了。
     * <p>
     * 不要使用float进行计算
     */
    @Test
    public void testFloat3() {
        float a = 3.3F;
        // 1.23456780
        System.out.println("a = " + a);

        // 1.23456780
        System.out.println(0.033 * 100);

//        float ff = 10.022;
    }


    @Test
    public void tt() {
        BigDecimal b1 = new BigDecimal(10.111111111111111111111);
        BigDecimal b2 = new BigDecimal(12.1111111111111111111101);
        BigDecimal totalAccountAmount = b1.subtract(b2, MathContext.DECIMAL64);
        System.out.println("totalAccountAmount = " + totalAccountAmount);

        BigDecimal bd = new BigDecimal(1);
        BigDecimal bd2 = new BigDecimal(3);

        BigDecimal result = bd.divide(bd2, MathContext.DECIMAL32);
        System.out.println(result);
        result = bd.divide(bd2, MathContext.DECIMAL64);
        System.out.println(result);
        result = bd.divide(bd2, MathContext.DECIMAL128);
        System.out.println(result);
        result = bd.divide(bd2, MathContext.UNLIMITED);
        System.out.println(result);
    }

}
