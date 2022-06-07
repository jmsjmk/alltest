package com.jiamingku.j2se.oop.statics.demoinheritance;

public class Super1 {

	/**
	 * 静态,私有方法不能被重写,这样的方法调用是没有多台性质的(继承的方法叫重写)
	 */
	static void staticMethod() {
		System.out.println("Super class method");
	}

	private void privateMethod() {

		System.out.println("Super class method");

	}

	public void inStaticMethodPoint() {
		System.out.println("this.getClass().getSimpleName() = " + this.getClass().getSimpleName());
		this.staticMethod();
		staticMethod();
	}

	public void inPrivateMethodPoint() {
		System.out.println("this.getClass().getSimpleName() = " + this.getClass().getSimpleName());
		this.privateMethod();
		privateMethod();
	}
}