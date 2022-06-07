package com.jiamingku.j2se.string;

import com.jiamingku.j2se.numberprocess.MessageFormateTest;
import com.jiamingku.j2se.numberprocess.NumberFormatTest;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jiamingku on 2018/12/29.
 */
public class StringFormateTest {

    // ----------------------------------------------String.format();-----------------------------------------------------

    /**
     * String类的format()方法用于创建格式化的字符串以及连接多个字符串对象
     * {@link MessageFormateTest}
     * {@link NumberFormatTest}
     * {@link StringFormateTest}
     * CharacterToInt
     */
    @Test
    public void test1() {
        String str = null;
        str = String.format("Hi,%s", "王力");
        System.out.println(str);
        str = String.format("Hi,%s:%s.%s", "王南", "王力", "王张");
        System.out.println(str);
        // ----打印一个字符
        System.out.printf("字母a的大写是：%c %n", 'b');
        System.out.printf("字母a的大写是：%d %n", 10); // 因为不是字符
        System.out.printf("3>7的结果是：%b %n", 3 > 7);
        str = String.format("3>7的结果是：%b %n", 3 > 7);
        System.out.println("str = " + str);
        System.out.printf("100的一半是：%d %n", 100 / 2);
        System.out.printf("100的16进制数是：%x %n", 100);
        System.out.printf("100的8进制数是：%o %n", 100);
        System.out.printf("50元的书打8.5折扣是：%f 元%n", 50 * 0.85);
        System.out.printf("上面价格的16进制数是：%a %n", 50 * 0.85);
        System.out.printf("上面价格的指数表示：%e %n", 50 * 0.85);
        System.out.printf("上面价格的指数和浮点数结果的长度较短的是：%g %n", 50 * 0.85);
        System.out.printf("上面的折扣是%d%% %n", 85);
        System.out.printf("字母A的散列码是：%h %n", 'A');
        Integer i = 100;
    }

    @Test
    public void testFormat() {
        String s = "--%02d--";
        System.out.println(String.format(s, 1));
        System.out.println(String.format(s, 99));
    }

    /**
     * 搭配转换符的标志
     */
    @Test
    public void test2() {
        String str = null;
        //$使用
        str = String.format("格式参数$的使用 %115d", 99);
        System.out.println(str);
        //+使用
        System.out.printf("显示正负数的符号：%+d与%d%n", 99, -99);
        //补O使用
        System.out.printf("最牛的编号是：%03d%n", 7);
        //空格使用
        System.out.printf("Tab键的效果是：%08d%n", 7);
        //.使用
        System.out.printf("整数分组的效果是：%,d%n", 9989997);
        //空格和小数点后面个数
        System.out.printf("一本书的价格是：% 50.5f元%n", 49.8);
    }


    /**
     * 原来我们使用的对比，参考
     */
    @Test
    public void test3() {
        String str1 = null;
        str1 = String.format("Hi,%s", "王力");
        System.out.println(str1);

        String str = "5300_2_0_[{1}]_10_0";
        String s = MessageFormat.format(str, "fuck", "ddd");
        System.out.println("s = " + s);
    }

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList = null;
        for (String s : stringList) {
            System.out.println("s = " + s);
        }
    }

    /**
     * http://blueram.iteye.com/blog/441683
     * <p>
     * 格式化字符串一般我们使用的类就是：其实说白就是把大括号给替换成为对应的东西
     */
    @Test
    public void test4() {
        String s3 = "BatchInsertOrderApportion error, [table apportion_order] OrderId[{0}] insertCnt[{1}] Callback!";

        String a = MessageFormat.format(s3, 11, 22);
        System.out.println(a);

        System.out.println(MessageFormat.format("Today is {0}", new Date()));
        System.out.println(MessageFormat.format("My age is {0},I was borm at {1}.", 26, 1979));
        String[] s = {"1", "2"};
        List<String> list = new ArrayList<String>();
        list.add("100");
        list.add("200");
        System.out.println(MessageFormat.format("My age is {0},I was borm at {1}.", s));
        System.out.println(MessageFormat.format("My age is {0},I was borm at {1}{2}-------------------------{1}.", "d---", "c---", "d---"));
    }
}
