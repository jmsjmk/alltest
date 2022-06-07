package com.jiamingku.j2se.basetype;

import io.swagger.models.auth.In;
import org.junit.Test;

/**
 * Created by jiamingku on 2019/11/6.
 */
@SuppressWarnings("all")
public class CharaterTest {


    @Test
    public void test1() {
        char a = '中';
        System.out.println("a = " + a);
        int aa = a;
        System.out.println("aa = " + aa);
        short t = 8;
        byte b = 1;
        // t = b + t;// error 类型变成了int所以出错
    }


    @Test
    public void test2() {
        int i = 1;
        long j = 2;
        // i = i + j; error
        i += j; // correct 因为符合运算符可以进行类型隐藏的转换
    }

    @Test
    public void test3() {

        char c11 = '\u0053';
        System.out.println("c11 = " + c11);

        /**
         java中 char c1 = '\\'; char c2 = '\u005c'; 请问这两句有何区别？ 为什么第二句无法通过编译？
         */

//        /**
//         * 因为\ u05c实际上就是反斜杠“\”。
//         c1:一个反斜杠字符；
//         c2:反斜杠会把后面那个单引号转义，造成c2不是一个完整的表达式。
//         */
    }

    @Test
    public void testFuZhi() {
        char c = 12;
        char c1 = '\n';
        char c2 = '\0';
        System.out.println("c2 = " + c2);
        char c3 = '\u0000';
        System.out.println("c3 = " + c3);

        char c4 = '\u0001';
        System.out.println("c4 = " + c4);
        char c5 = '\1';
        System.out.println("c5 = " + c5);
        System.out.println("(c4 == c5) = " + (c4 == c5));

        char c6 = '\u0021';
        System.out.println("c6 = " + c6);
        char c7 = '\33';
        System.out.println("c7 = " + c7);
        System.out.println("(c7 == c6) = " + (c7 == c6));
        System.out.println("'\\21' = " + '\72');
        System.out.println("c7 = " + '\u003A');
        System.out.println("('\\72' == '\\u003A') = " + ('\72' == '\u003A'));

        // ------------------------java字符表示表示方式
        // ----\ u  代表unicode字符
        // ----\ +数字 代表8进进的表示方式


    }

    @Test
    public void Int2CharDemo() {
        int num1 = 8;
        char ch1 = (char) (num1 + 48);
        char ch21 = 56;
        System.out.println("ch2 = " + ch21);
        System.out.println("ch1 = " + ch1);  // 将char类型数字8转换为int类型数字8
        // 方法一：
        Character ch2 = '8'; // char是基本数据类型，Character是其包装类型。
        int num2 = Integer.parseInt(ch2.toString());
        System.out.println("num2 = " + num2);
        // 方法二：
        char ch3 = '8';
        int num3 = ch3 - 48;
        System.out.println("num3 = " + num3);

        // 方法3
        int aaaa = Character.getNumericValue(ch2);
        System.out.println("aaaa = " + aaaa);
    }

    @Test
    public void BaseTest() {
        char a = '3';
        int b = a;
        // https://www.cnblogs.com/csguo/p/7401874.html ---对应的字符标
        System.out.println(b);
        int b1 = 51;
        char c1 = (char) b1;
        System.out.println(c1);
        char ccc = Character.forDigit(51, 10);
        System.out.println(ccc);

        // create 2 character primitives ch1, ch2
        char ch1, ch2;

        // create 2 int primitives i1, i2 and assign values
        int i1 = 3;
        int i2 = 14;


        // assign char representation of i1, i2 to ch1, ch2 using radix
        ch1 = Character.forDigit(i1, 10);
        ch2 = Character.forDigit(i2, 16);

        String str1 = "Char representation of " + i1 + " in radix 10 is " + ch1;
        String str2 = "Char representation of " + i2 + " in radix 16 is " + ch2;

        // print ch1, ch2 values
        System.out.println(str1);
        System.out.println(str2);


        int abcd = '\u0012';
        int bdsfsdf = 23234;
        char ac1 = 'd';
        int bc12 = ac1;
    }

    @Test
    public void forDigit() {
        // create 2 character primitives ch1, ch2
        char ch1, ch2;

        // create 2 int primitives i1, i2 and assign values
        int i1 = 3;
        int i2 = 14;

        // assign char representation of i1, i2 to ch1, ch2 using radix
        ch1 = Character.forDigit(i1, 10);
        ch2 = Character.forDigit(i2, 16);

        String str1 = "Char representation of " + i1 + " in radix 10 is " + ch1;
        String str2 = "Char representation of " + i2 + " in radix 16 is " + ch2;

        // print ch1, ch2 values
        System.out.println(str1);
        System.out.println(str2);
    }

    @Test
    public void tteser() {
        String s = null;
        String s1 = String.valueOf(s);
        System.out.println("s1 = " + s1);

        Integer i = Integer.valueOf(s);
        System.out.println("i = " + i);
    }
}
