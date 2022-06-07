package com.jiamingku.j2se.oop.statics.demoinheritance;

public class Sub extends Super1 {

	// @Override
	static void staticMethod() {
		System.out.println("Sub class method");
	}

	private void privateMethod() {
		System.out.println("Sub class method");
	}


	public static void main(String args[]) {
		Super1.staticMethod();
		Sub.staticMethod();
		System.out.println(" ==================================== ");
		Super1 super1 = new Sub();
		super1.inStaticMethodPoint();
		System.out.println("=============================== ");
		super1.inPrivateMethodPoint();
	}
}