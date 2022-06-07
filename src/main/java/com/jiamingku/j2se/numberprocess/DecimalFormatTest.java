package com.jiamingku.j2se.numberprocess;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by jiamingku on 2018/12/29.
 */
public class DecimalFormatTest {

    public static void main(String[] args) {
        int a = 1;
        a += (3 / 2);
        System.out.println("a = " + a);

        System.out.println("(16/3) = " + (16 / 3));

        float b = 3 / 2;
        System.out.println("b = " + b);
        int d = (3 / 2);
        System.out.println("d = " + d);

        float ddd = a;
        System.out.println("ddd = " + ddd);

        int a1 = 0;
        byte b12 = 3;

        float dddd = 3.323F;
    }
    // ---------------------------------------------------------------------------------------------------------

    // 特点：
    // DecimalFormat 类主要靠 # 和 0 两种占位符号来指定数字长度。0 表示如果位数不足则以 0 填充，
    // # 表示只要有可能就把数字拉上这个位置。上面的例子包含了差不多所有的基本用法，如果你想了解更多，请参考 DecimalFormat 类的文档。
    /**
     格式化小数的时候，可以指定输入模式，
     DecimalFormat df  = new DecimalFormat("0") ;//保留一位整数
     df.setRoundingMode(RoundingMode.HALF_EVEN);//舍入模式(西方)银行家的算法
     String val = df.format(2.5F);//格式化的数字
     System.out.println("value is:" + val);//value is:2
     DecimalFormat df1  = new DecimalFormat("0") ; //保留一位数字
     df.setRoundingMode(RoundingMode.HALF_UP);//舍入模式(四舍五入)
     String val2 = df.format(2.5F);//格式化的数字
     System.out.println("value is:" + val2);//value is:3
     DecimalFormat df2  = new DecimalFormat("0") ;//保留一位数字,默认的舍入模式
     String val3 = df.format(2.5F); //格式化的数字
     System.out.println("value is:" + val3); //value is:3
     */
    // ---------------------------------------------------------------------------------------------------------


    /**
     * decimalFormat就是一种格式化小数的方式，就是格式化小数
     * <p>
     * NumberFormat 是所有数值格式的抽象基类, DecimalFormat 是 NumberFormat 的一个具体子类，
     * 用于格式化十进制数字。其实处理格式化小数的。
     * <p>
     * 特点：
     * 1．可以设置默认输出的值。
     * 2．0是特殊模式字符，没有数字填充时，默认填写0。
     */
    @Test
    public void test1() {
        // 这个有点像固定前缀的意识，固定后缀的意识,
        String va0 = new DecimalFormat("￥990.05").format(12);
        System.out.println(va0); // ￥9912.05
        String va00 = new DecimalFormat("￥990.05").format(11112.11);
        System.out.println(va00); // ￥9912.05
        // 保留两位小数的意识
        String val = new DecimalFormat("0.00").format(12.234234234);
        System.out.println(val); // 12.00
        String va2 = new DecimalFormat("990.05").format(12);
        System.out.println(va2); // 9912.05
    }

    /**
     * 使用场景：
     * 1．在报表中，设置默认小数点后输出几位0的方式，。保留一位或者两位小数
     */
    @Test
    public void test2() {
        String val = new DecimalFormat("0.00").format(1112.5);
        System.out.println(val); // 1112.50
        // 保留三位小数
        String val1 = new DecimalFormat("0.###").format(12.5678007);
        System.out.println("value is:" + val1); // 12.568
    }


    /**
     * 使用场景：
     * 1．在报表中，设置默认小数点后输出几位0的方式，。保留一位或者两位小数
     */
    @Test
    public void test3() {
        double pi = 3.1415927; //圆周率
        //取一位整数
        System.out.println(new DecimalFormat("0").format(pi)); //3
        //取一位整数和两位小数
        System.out.println(new DecimalFormat("0.00").format(pi)); //3.14
        //取两位整数和三位小数，整数不足部分以0填补。
        System.out.println(new DecimalFormat("00.000").format(pi)); // 03.142
        //取所有整数部分
        System.out.println(new DecimalFormat("#").format(pi));//3
        //以百分比方式计数，并取两位小数
        System.out.println(new DecimalFormat("#.##%").format(pi));//314.16%

        long c = 299792458;//光速
        //显示为科学计数法，并取五位小数
        System.out.println(new DecimalFormat("#.#####E0").format(c));//2.99792E8
        //显示为两位整数的科学计数法，并取四位小数
        System.out.println(new DecimalFormat("00.####E0").format(c));//29.9792E7
        //每三位以逗号进行分隔。 299,792,458
        System.out.println(new DecimalFormat(",###").format(c));//299,792,458
        //将格式嵌入文本 光速大小为每秒299,792,458米。
        System.out.println(new DecimalFormat("光速大小为每秒,###米。").format(c));
    }

    @Test
    public void testOps() {
        BigDecimal b1 = new BigDecimal("1333.3563555");
        BigDecimal b2 = new BigDecimal("12.22");

        BigDecimal b3 = b1.divide(b2, MathContext.DECIMAL64); // 小数点后面的长短问题
        System.out.println("b3 = " + b3);

        b3 = b1.divide(b2, MathContext.DECIMAL32); // 小数点后面长短的问题
        System.out.println("b3 = " + b3);

        BigDecimal b31 = b1.setScale(2, RoundingMode.HALF_DOWN);
        System.out.println("b31 = " + b31);

        BigDecimal b312 = b1.setScale(2, RoundingMode.HALF_UP);
        System.out.println("b312 = " + b312);
    }
}
