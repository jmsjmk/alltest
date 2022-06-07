package com.jiamingku.j2se.innercalss.classdianthis;

/**
 * Created by jiamingku on 2019/1/9.
 */
public class TestInter implements Inter  {
	@Override
	public void displayLocvar() {

	}

	// 匿名内部类
	TestInter i = new TestInter() {

	};

	public static void main(String[] args) {
		Inter i = new TestInter();

		Inter.BB.a();

		Class c = Inter.B.class;
		System.out.println("c = " + c);

		i.getClass();
	}
}
