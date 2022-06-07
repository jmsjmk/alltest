package com.jiamingku.j2se.basetype;

import org.junit.Test;

/**
 * 基本类型的测试
 *
 * Created by jiamingku on 2019/6/12.
 */
public class BaseTypeTest {

	@Test
	public void test() {
		int a = 4;
		String s = Integer.toBinaryString(-128);
		System.out.println("s = " + s);
	}
}
