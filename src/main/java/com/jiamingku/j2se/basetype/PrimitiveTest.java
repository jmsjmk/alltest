package com.jiamingku.j2se.basetype;

import org.junit.Test;

/**
 * Created by jiamingku on 2019/6/12.
 */
public class PrimitiveTest {

	/**
	 * java 的赋值操作,int类型的 8,16,2进制操作
	 */
	@Test
	public void fzInt() {
		/*
		  赋二进制
		 */
		int a2_1 = 0b1111;
		System.out.println("a2_1 = " + a2_1);
		int a2_2 = 0B1111;
		System.out.println("a2_2 = " + a2_2);
		int a3_3 = 0B0000001;
		System.out.println("a3_3 = " + a3_3);

		int a16_1 = 0x12A;
		System.out.println("a16_1 = " + a16_1);
		int a16_2 = 0X12A;
		System.out.println("a16_2 = " + a16_2);


		int a8_1 = 017;
		System.out.println("a8_1 = " + a8_1);
		/*
		  字符类型
		 */
		char c1 = 'c';
		System.out.println("c1 = " + c1);
		char c2 = '你';
		System.out.println("c2 = " + c2);
		char c3 = '\n';
		System.out.println("c3 = " + c3);
		//char r2 = '\u0005';//有了u之后就代表是要输入的是unicode编码（encoding）
		char c4 = '\u3303';
		System.out.println("c4 = " + c4);
	}

	/**
	 * java 的赋值操作,int类型的 8,16,2进制操作
	 */
	@Test
	public void fzChar() {
		char a = '\n';
		char b = 'b';
		char c = '\u0001';
		// 因为计算机底层都是二进制，所以还可以用数字表示
		// 相当于一个16进制的无符号数
		int d = 23323;
		char e = '严';
		int ee = e;
		System.out.println("ee = " + ee);
		System.out.println("e = " + e);
		char eeee = 20005;
		System.out.println("eeee = " + eeee);

	}

	/**
	 * 强制类型转换
	 * 1:大的转换小的，其实就相当于截断操作
	 * 2:小的转换大的,其实就是符号位没变化,只是变量占用的内存空间变大了
	 */
	@Test
	public void testQZZH() {
		int a = 0B11111111;
		System.out.println("a = " + a);

		byte b = (byte) a;
		System.out.println("b = " + b);

		int c = b;
		System.out.println("c = " + c);

		/**
		 * 因为最后面有一个L参数,所以呢,
		 */
		long l = 0b10000000000000000000000000000011L;
		System.out.println("l = " + l);
	}

	/**
	 * 浮点数的默认数据类型是 double类型的
	 *
	 */
	@Test
	public void fzFloat() {
		/**
		 * 浮点数必须包含小数点，否则会当成整数处理
		 */
		float f = 1;
		System.out.println("f = " + f);
		float f1 = 1.0f;
		/* 这样也可以，*/
		float f0 = .32f;
		System.out.println("f0 = " + f0);
		/**
		 * 只有浮点类型的才可以使用科学计数法
		 */
		float f2 = 2.12e2f;
		float f3 = -2.12e2f;
		/**
		 * 浮点数/0 +无穷大或者-无穷大
		 * 一个整数/0 则会抛出异常
		 */
		System.out.println("f2 / 0.0 = " + f2 / 0.0);
		System.out.println("f2 / 0.0 = " + f3 / 0.0);



	}
	@Test
	public void defaultIntType() {
		// ----------所有的直接量,在进行编译的时候都会进行校验
		int a = 10;
		// -----直接将小的整数,自动转换成为byte,short
		// -----系统默认就是整型,所以呢系统会自动把这个小整型给你转换对应的类型
		byte b = 12;
		short s = 21;
		// int alarge = 12222222222222222222222; 巨大的数超出了范围,就不能编译通过
		// 如果你不加l 或者 L字符，系统不会按照长整型进行对待。
		// long l = 3323122222222222;
		long l = 3323122222222222L;

		String as = null;
//		(int)100L;
		int ttt = 0B00011;
		System.out.println("ttt = " + ttt);
		byte b1 = 0b01111111;
		System.out.println("b1 = " + b1);
//		byte b2 = 0b100000000;
//		System.out.println("b2 = " + b2);
		// 出现了
		byte c =  (byte)0b10_00_00_00; //8-bit it works fine if casted.
		System.out.println("c = " + c);

		byte b3 = 127;

		byte b4 = 1;

		byte  b33 = (byte) (b3 + b4);
		System.out.println("b33 = " + b33);

		byte dd = 0x2;
//		byte dd2 = 0b10000000;
	}

	// P50
}
