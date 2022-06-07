package com.jiamingku.j2se.basetype.moveoperation;

import org.junit.Test;

public class Shift {

    public static void main(String[] args) {
        System.out.println("******************正数左移在低位补0*******************");
        int a = 65;
        a = a << 2;
        System.out.println(a);
        System.out.println(Integer.toBinaryString(a));
        System.out.println("******************正数右移在高位补0********************");
        a = 15;
        a = a >> 2;
        System.out.println(a);
        System.out.println(Integer.toBinaryString(a));
        System.out.println("看看负数的移位:");

        System.out.println("***********负数的右移操作高位补1**************");
        int i = -1;
        System.out.println(i + ":");
        System.out.println(Integer.toBinaryString(i));
        i = i >> 2;
        System.out.println(i);
        System.out.println(Integer.toBinaryString(i));
        System.out.println("**********负数的左移操作低位补0*****************");
        i = i << 2;
        System.out.println(i);
        System.out.println(Integer.toBinaryString(i));
        System.out.println("*************再看看>>>操作符*************");
        System.out.println("*************负数的>>>操作高位补0***************");
        i = -1;
        System.out.println(Integer.toBinaryString(i));
        i = i >>> 10;
        System.out.println(i + ":");
        System.out.println(Integer.toBinaryString(i));
        System.out.println("*************注意：没有<<<符号**************");

        System.out.println("**********byte类型移位时要强转换*************");
        byte k = 10;
        System.out.println(Integer.toBinaryString(k));
        k = (byte) (((byte) k) >>> 2);
        System.out.println(Integer.toBinaryString(k));
    }

    /**
     * 移位操作符，是针对int 或者long 来进行操作的，
     * java中的移位操作只对int和long有效，byte、short、char升级为int后再进行移位
     */
    @Test
    public void moveByte() {
        byte b = 12;

        int c = b >> 1;
        System.out.println("c = " + c);
        // 并且移位置之后，数据是不能进行更改的
        System.out.println("b = " + b);

        byte c1 = (byte) (b >> 1);
        System.out.println("c1 = " + c1);
    }

    @Test
    public void testNo() {
        int i = 1;
        System.out.println("i = " + i + "\t" + Integer.toBinaryString(i));
        int noi = ~i;
        System.out.println("noi = " + noi + "\t" + Integer.toBinaryString(noi));
        int nonoi = ~noi;
        System.out.println("nonoi = " + nonoi);
    }

    @Test
    public void testRightmove() {
        int i = -1;
        int j = i >> 22;
        System.out.println("j = " + j);
        i = 100;
        j = i << 2;
        System.out.println("i = " + i);
    }

    @Test
    public void move() {
        int i = -1;
        int j = i >>= 1;

        System.out.println("j = " + j);
        int j1 = i >>= 200;
        System.out.println("j1 = " + j1);

        int a = 2;

        int b = Integer.numberOfLeadingZeros(a);
        System.out.println("b = " + b);
    }

    /**
     * 求最大整数1维权的2倍
     */
    @Test
    public void test1() {
        int initialCapacity = 15;
        initialCapacity |= (initialCapacity >>> 1);
        initialCapacity |= (initialCapacity >>> 2);
        initialCapacity |= (initialCapacity >>> 4);
        initialCapacity |= (initialCapacity >>> 8);
        initialCapacity |= (initialCapacity >>> 16);
        initialCapacity++;
        System.out.println("initialCapacity = " + initialCapacity);
    }

    /**
     * 求最大整数1的位权
     */
    public static int highestOneBit(int i) {
        // HD, Figure 3-1
        i |= (i >> 1);
        i |= (i >> 2);
        i |= (i >> 4);
        i |= (i >> 8);
        i |= (i >> 16);
        return i - (i >>> 1);
    }


    /**
     * 求最小的位
     *
     * @param i
     * @return
     */
    public static int lowestOneBit(int i) {
        // HD, Section 2-1
        return i & -i;
    }

    @Test
    public void test01() {
        int i= -1;
        int b = i >>> 1;
        System.out.println(b);
        int ii = 5;
        System.out.println(ii >>1);
    }
}