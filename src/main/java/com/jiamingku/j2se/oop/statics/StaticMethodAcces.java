package com.jiamingku.j2se.oop.statics;

/**
 * Created by jiamingku on 2019/1/8.
 */
public class StaticMethodAcces {
	private String a = 100+"";

	public StaticMethodAcces(String a) {
		this.a = a;
	}

	public void t() {
		System.out.println("true = " + a);
	}

	public static void main(String[] args) {
		// 其实道理应该可以想明白了
		// 如果静态方法里面，使用了非静态方法的🌹，非静态方法里面调用了实例变量=那就爽歪歪了
		StaticMethodAcces staticMethodAcces = new StaticMethodAcces(200+"");
		staticMethodAcces.t();
		staticMethodAcces = new StaticMethodAcces(300+"");
		staticMethodAcces.t();
	}

	public static void a() {
//		t();
	}


}
