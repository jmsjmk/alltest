package com.jiamingku.j2se;

import org.junit.Test;

/**
 * Created by jiamingku on 2019/6/8.
 */
public class FinalTest {

	/**
	 * 初始化可以 在声明的时候初始化
	 * ,也可以在初始化块中初始化
	 * ,也可以在构造器中初始化
	 * 原则就是使用前一定进行初始化
	 */
	private final String a = "testa";

	private final String b;

	private final String c = "c";

	private final String d;

	{
		b = "3";
	}


//	public FinalTest() {
//	}

	/* 构造器中可以使用*/
	public FinalTest(int c) {
		this.d = c+"1";
	}
	// =============================================宏替换
	@Test
	public void test() {
		String s1 = "abc";
		String s2 = "a" + "bc";
		String s3 = "a" + "b" + "c";
		System.out.println(s1 == s2); // true
		System.out.println(s1 == s3); // true

		String ps1 = "a";
		String ps2 = "bc";
		String ps3 = ps1 + ps2;
		System.out.println(s1 == ps3); // false
		// 增加了final 的字符之后，就相当于直街量
		final String p1 = "a";
		final String p2 = "bc";
		ps3 = p1 + p2;
		System.out.println(s1 == ps3); // true
	}

	/**
	 * java都是传递值,所以这个值的怎么变化都不会影响啥的
	 */
	public void finalTest(Integer i) {
		i = new Integer(100);
		System.out.println("i = " + i);

	}

	public void finalTest1(final Integer i) {
		// 连叫你修改的机会都没有。
		// i =new Integer(1000);
	}
}
