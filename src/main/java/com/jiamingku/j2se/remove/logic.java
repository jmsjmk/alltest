package com.jiamingku.j2se.remove;

import org.junit.Test;

/**
 * Created by jiamingku on 2019/5/10.
 */
public class logic {

	public static void main(String[] args) {
		// 相同输出0 ，不相同输出1--------------------------^
		/**
		 *     异域的概念是相同为0不同为1.如果两个数值异或后的值相同，异或前可能不同。
		 比如二进制：0010^0001=0011 而0000^0011=0011。 异或要慎用

		 结果相同==参数不同==尽然有相同的结果
		 */
		System.out.println(1 ^ 1); // 输出0
		System.out.println(1 ^ 2); // 输出3，因为最后2个低位都不一样，所有输出3



		// -------------------------------&

		int a = 1;
		int b = 0;

		System.out.println("a & b = " + (a & b));

		System.out.println("a | b = " + (a | b));
	}

	@Test
	public void test1() {
		int a = 60; /* 60 = 0011 1100 */
		int b = 13; /* 13 = 0000 1101 */
		int c = 0;
		c = a & b;       /* 12 = 0000 1100 */
		System.out.println("a & b = " + c);
		c = a | b;       /* 61 = 0011 1101 */
		System.out.println("a | b = " + c);
		c = a ^ b;       /* 49 = 0011 0001 */
		System.out.println("a ^ b = " + c);
		c = ~a;          /*-61 = 1100 0011 */
		System.out.println("~a = " + c);
		c = a << 2;     /* 240 = 1111 0000 */
		System.out.println("a << 2 = " + c);
		c = a >> 2;     /* 15 = 1111 */
		System.out.println("a >> 2  = " + c);
		c = a >>> 2;     /* 15 = 0000 1111 */
		System.out.println("a >>> 2 = " + c);
	}

	@Test
	public void test2() {
		int a = 10;
		int b = 20;
		int c = 0;
		c = a + b;
		System.out.println("c = a + b = " + c);
		c += a;
		System.out.println("c += a  = " + c);
		c -= a;
		System.out.println("c -= a = " + c);
		c *= a;
		System.out.println("c *= a = " + c);
		a = 10;
		c = 15;
		c /= a;
		System.out.println("c /= a = " + c);
		a = 10;
		c = 15;
		c %= a;
		System.out.println("c %= a  = " + c);
		c <<= 2;
		System.out.println("c <<= 2 = " + c);
		c >>= 2;
		System.out.println("c >>= 2 = " + c);
		c >>= 2;
		System.out.println("c >>= a = " + c);
		c &= a;
		System.out.println("c &= 2  = " + c);
		c ^= a;
		System.out.println("c ^= a   = " + c);
		c |= a;
		System.out.println("c |= a   = " + c);
	}
}
