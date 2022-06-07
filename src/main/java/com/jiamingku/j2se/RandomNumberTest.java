package com.jiamingku.j2se;

import org.junit.Test;

import java.security.SecureRandom;
import java.util.Random;

/**
 * http://blog.sina.com.cn/s/blog_93dc666c0101h3gd.html
 * https://blog.csdn.net/u011240877/article/details/52971166
 * <p>
 * <p>
 * 1.java.util.Random类中实现的随机算法是伪随机，也就是有规则的随机，所谓有规则的就是在给定种(seed)的区间内随机生成数字；
 * 2.相同种子数的Random对象，相同次数生成的随机数字是完全相同的；
 * 3.Random类中各方法生成的随机数字都是均匀分布的，也就是说区间内部的数字生成的几率均等；
 */
public class RandomNumberTest {

    /**
     * 种子就是随机数的起源,没啥关系,
     * <p>
     * netInt是产生数的上线--如果指定参数的话
     */
    @Test
    public void base() {
        // http://blog.sina.com.cn/s/blog_93dc666c0101h3gd.html
        Random r1 = new Random(201);
        Random r2 = new Random(201);
        Random r3 = new Random();
        System.out.println(r1.nextInt());
        System.out.println(r2.nextInt());
        System.out.println(r3.nextInt());

        System.out.println(r1.nextInt(100));
        System.out.println(r2.nextInt(100));
        System.out.println("int: " + r3.nextInt(33)); // 按均匀分布产生长整数 System.out.println("long: " + rd1.nextLong());

        // 这个不能产生上限
        long l1 = r1.nextLong();
        System.out.println("l1 = " + l1);
        boolean b = r1.nextBoolean();
        System.out.println("b = " + b);
        System.out.println("r1.nextDouble() = " + r1.nextDouble());

        for (int i = 0; i < 100; i++) {
//           int n = Math.abs(r1.nextInt() % 10);
//            System.out.println("n = " + n);
            System.out.println("r3.nextInt() = " + r3.nextInt(100));
        }
    }

    /**
     * netInt 如果不指定参数就随便的随机一个证书
     */
    @Test
    public void testNoParameter() {
        Random r1 = new Random(201);
        for (int i = 0; i < 10000; i++) {
            int a = r1.nextInt(100);
            if (a < 0) {
                System.out.println(a);
            }
        }
    }

    @Test
    public void test() {
        int max = 100;
        int min = -100;
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int s = random.nextInt(max) % (max - min + 1) + min;
            System.out.println(s);
            if (s > 0) {
                System.out.println("===================================");
                System.out.println("s = " + s);
            }
        }
    }

    @Test
    public void test1() {
        int max = 100;
        int min = -100;
        Random random = new Random(11);
        Random random1 = new Random(11);
        int s = 0;
        for (int i = 0; i < 1000; i++) {

            s = random.nextInt(max) % (201);
            System.out.println("===================================");
            System.out.println("s = " + s);
            System.out.println("random = " + random1.nextInt(100));
        }

    }


    @Test
    public void testRandom() {
        SecureRandom random = new SecureRandom();
        //声明盐数组变量   12
        byte[] salt = new byte[12];
        for (byte b : salt) {
            System.out.println("b = " + b);
        }
        System.out.println(" ================================ ");
        //将随机数放入盐变量中
        random.nextBytes(salt);

        for (byte b : salt) {
            System.out.println("b = " + b);
        }

    }


    /**
     * 如果想获取0-150数据包含150的话如何实现？
     */
    @Test
    public void test1To150() {
        for (int i = 0; i < 1000; i++) {
            int a = (int) (Math.random() * 151);
            if (a == 150)
                System.out.println("a = " + a);
            if (a == 0) {
                System.out.println("a = " + a);
            }
        }
    }

    /**
     * [0.0, 1.0) 左面是闭区间,右面是开区间
     * <p>
     */
    public void test1223() {
        // [0.0, 1.0)--是一个区间不包含1
        double d = Math.random() * 10;
        System.out.println(d);
        double d1 = Math.random();
        System.out.println("d1 = " + d1);
        for (int i = 0; i < 100; i++) {
            System.out.println("Math.random() * 10 = " + (int) (Math.random() * 10));
        }
    }

    public static void main(String[] args) {
        // 使用java.lang.Math的random方法生成随机数
        System.out.println("Math.random(): " + Math.random());
        // 使用不带参数的构造方法构造java.util.Random对象
        System.out.println("使用不带参数的构造方法构造的Random对象:");
        Random rd1 = new Random();
        // 产生各种类型的随机数
        // 按均匀分布产生整数
        System.out.println("int: " + rd1.nextInt());
        // 按均匀分布产生长整数
        System.out.println("long: " + rd1.nextLong());
        // 按均匀分布产生大于等于0，小于1的float数[0, 1)
        System.out.println("float: " + rd1.nextFloat());
        // 按均匀分布产生[0, 1)范围的double数
        System.out.println("double: " + rd1.nextDouble());
        // 按正态分布产生随机数
        System.out.println("Gaussian: " + rd1.nextGaussian());
        // 生成一系列随机数 System.out.print("随机整数序列:");
        for (int i = 0; i < 5; i++) {
            System.out.print(rd1.nextInt() + " ");
        }
        System.out.println();
        // 指定随机数产生的范围
        System.out.print("[0,10)范围内随机整数序列: ");
        for (int i = 0; i < 10; i++) {
            // Random的nextInt(int n)方法返回一个[0, n)范围内的随机数
            System.out.print(rd1.nextInt(10) + " ");
        }
        System.out.println();
        System.out.print("[5,28)范围内随机整数序列: ");
        for (int i = 0; i < 10; i++) {
            // 因为nextInt(int n)方法的范围是从0开始的，
            // 所以需要把区间[5,28)转换成5 + [0, 23)。
            System.out.print(5 + rd1.nextInt(23) + " ");
        }
        System.out.println();
        System.out.print("利用nextFloat()生成[0,99)范围内的随机整数序列: ");
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.print((int) (rd1.nextFloat() * 100) + " ");
        }

        System.out.println();
        // 使用带参数的构造方法构造Random对象
        // 构造函数的参数是long类型，是生成随机数的种子。
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("使用带参数的构造方法构造的Random对象:");
        Random ran2 = new Random(10);
        // 对于种子相同的Random对象，生成的随机数序列是一样的。
        System.out.println("使用种子为10的Random对象生成[0,10)内随机整数序列: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(ran2.nextInt(10) + " ");
        }
        System.out.println();
        Random ran3 = new Random(10);
        System.out.println("使用另一个种子为10的Random对象生成[0,10)内随机整数序列: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(ran3.nextInt(10) + " ");
        }
        System.out.println(); // ran2和ran3生成的随机数序列是一样的，如果使用两个没带参数构造函数生成的Random对象，
        // 则不会出现这种情况，这是因为在没带参数构造函数生成的Random对象的种子缺省是当前系统时间的毫秒数。
        // 另外，直接使用Random无法避免生成重复的数字，如果需要生成不重复的随机数序列，需要借助数组和集合类
    }
}