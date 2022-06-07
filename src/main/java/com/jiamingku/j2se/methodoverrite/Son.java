package com.jiamingku.j2se.methodoverrite;

/**
 * 1. 先初始化父类的静态成员，静态初始化块，然后子类的静态成员，静态初始化块 主要目的完成类的加载属性（使用类必须加载，在类加载的初始化阶段就完成了，
 *    静态属性的初始化，一定是最顶成的先加载—这种都是在类进行两次初始化之后的操作了）
 * <p>
 * 2.子类创建对象，先调用父类的构造器，父类构造器初始化成员变量，然后执行构造器里面的代码，
 * 原理就是：进入构造器之后，
 * 2.1：执行完super()进入父类的构造器，初始化父类成员变量，
 * 2.2：执行构造器剩下的代码。
 * 3.然后子类的成员变量，子类的构造器中的语句。
 */
public class Son extends Father {

	public static String b = "dd";
	public String c = "";

//    public abstract  static void stt();

	public static void t() {
		Son son = new Son("d");
		son.method();

		byte b = 1;
		int c = b >> 1;

	}

	public Son() {
		a = "33";
	}

	//    static {
//        b = "dddd";
//        c = "dd";
//    }
	public final String a;

	public Son(String a) {
		this(a, "c");
//        this.(a, "c");
		String tt = super.name;

	}

	public Son(String a, String c) {
		this.a = a;
		this.c = c;
	}

	protected String name = "儿子属性";

	public void method() {
		System.out.println("子类方法，对象类型：" + this.getClass());
	}

	public static void main(String[] args) {
		Father sample = new Son();//向上转型
		System.out.println("调用的成员：" + sample.name);
	}
}