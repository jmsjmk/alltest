package com.jiamingku.j2se.innercalss;

public class Parcel6 {
	private int a = 5;

	public int a() {
		System.out.println("method is excute..");
		return 9;
	}

	// 匿名内部类 可以访问 宿主类的方法与成员变量
	public Contents cont() {
		return new Contents() {
			private int i = 11;

			@Override
			public int value() {
				System.out.println(a);
				a();
				return i;
			}
		}; // 在这里需要一个分号
	}

	public static void main(String[] args) {
		Parcel6 p = new Parcel6();
		Contents c = p.cont();
		c.value();
	}
}

interface Contents {
	int value();
}