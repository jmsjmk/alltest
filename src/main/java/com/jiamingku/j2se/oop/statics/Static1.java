package com.jiamingku.j2se.oop.statics;

/**
 * aaaaa
 * Created by jiamingku on 2018/6/13.
 */
public class Static1 {

	/**
	 * 子类不可以重写,但是可以定义一个一摸一样的方法
	 * {@link com.jiamingku.j2se.oop.statics.Static2#privatea1()}
	 */
	public static void privatea1() {
		System.out.println(" === ");
	}

	/**
	 * 静态的方法，虽然不能被继承(但是子类可以定义一个一摸一样的方法）
	 * 但是 +final 可以限制子类定义相当的方法签名，这个叫隐藏，不是重写
	 * <p>
	 * 再次注意如果访问权限是private static final 的就不会有隐藏限制。
	 */
	public final static void a() {
		String aa = "";
		System.out.println("aa = " + aa);
	}


	private final static void b() {
		String aa = "";
		System.out.println("aa = " + aa);
	}

	/**
	 * 方法不能被继承，子类可以定义一摸一样的方法签名
	 * <p>
	 * 虽然子类定义了一个一模一样的方法但是没有多态的性质了
	 */
	private final void ssa() {
		Static1 static1 = new Static1();

	}

	private final static void ttt() {

	}

	private void tt() {

	}

	public static void a1() {
		System.out.println("aa = ");
	}

	/**
	 * 方法可以被继承，所以子类不能从写。
	 */
	public final void c() {

	}

}
