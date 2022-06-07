package com.jiamingku.j2se.innercalss;

public class TestLambdaExpression {

	public String variable = "Class level variable";

	public static void main(String[] args) {

		new TestLambdaExpression().test();

	}

	public void test() {
		String variable = "Method local Variable";
		new Thread(() -> {
			int a = 100;
			System.out.println("a = " + a);
			a++;
			System.out.println("a = " + a);
			System.out.println("->" + variable);
			System.out.println("->" + this.variable);
		}).start();
	}

}