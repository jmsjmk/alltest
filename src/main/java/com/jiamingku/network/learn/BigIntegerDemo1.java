package com.jiamingku.network.learn;
 
import org.junit.Test;

import java.math.BigInteger;
 
public class BigIntegerDemo1 {
 
	public static void main(String[] args) {
		System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
		BigInteger bi1 = new BigInteger("123456789") ;	// 声明BigInteger对象
		BigInteger bi2 = new BigInteger("2147383647") ;	// 声明BigInteger对象
		//                                   2147483647
		System.out.println("加法操作：" + bi2.add(bi1)) ;	// 加法操作
		System.out.println("减法操作：" + bi2.subtract(bi1)) ;	// 减法操作
		System.out.println("乘法操作：" + bi2.multiply(bi1)) ;	// 乘法操作
		System.out.println("除法操作：" + bi2.divide(bi1)) ;	// 除法操作
		System.out.println("最大数：" + bi2.max(bi1)) ;	 // 求出最大数
		System.out.println("最小数：" + bi2.min(bi1)) ;	 // 求出最小数
		BigInteger result[] = bi2.divideAndRemainder(bi1) ;	// 求出余数的除法操作
		System.out.println("商是：" + result[0] + 
			"；余数是：" + result[1]) ;
	}

	@Test
	public void test1() {
		byte b = -1;
		int a = Byte.toUnsignedInt(b);

		int b2 = b;
		System.out.println("b2 = " + b2);
		System.out.println("a = " + a);


		int c = 255;
		byte d= (byte)c;
		System.out.println("d = " + d);






	}
}