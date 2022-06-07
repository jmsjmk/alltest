package com.jiamingku.j2se.oop.superthis.method;

/**
 * Created by jiamingku on 2018/9/5.
 */
public class P {
	/**
	 * 子类S中有个同名的属性
	 */
	public String value = "p value";
	/**
	 * 子类中没有同名的属性
	 */
	public String bbbb = "33";

	/**
	 * 子类调用这个方法,this还是指向运行时候的对象，
	 * 但是通过this.操作属性时候，只能是当前类中的属性，否者编译都通不过
	 */
	protected void a() {
		System.out.println("this = " + this);
		System.out.println("this.value " + this.value);
		this.a();
	}


	protected final void findLoadedClass(String name) {
		System.out.println("this = " + this);
		findLoadedClass0(name);

		System.out.println(" ======================== ");

		this.findLoadedClass0("tttttt");
	}

	private final void findLoadedClass0(String name) {
		System.out.println("this = " + this);

		System.out.println("name = " + name);
	}
}
