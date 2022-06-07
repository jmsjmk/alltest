package com.jiamingku.j2se.innercalss.demo1;

@SuppressWarnings("all")
public class Outer {
	private int s = 100;
	private int sssss= 10000000;
	private int out_i = 1;
	private static int a = 1;

	static class test {
		/* 可以定义普通的方法*/
		public void t() {
			System.out.println();
		}
		/*
		静态内部类不能使用非静态的属性，就跟普通类的静态方法不能是用非静态属性一样
		public void ttt() {
			System.out.println("false = " + s);

		}
		 */
		/* 可以定义静态的方法*/
		public static void testStaticMethod() {

		}
	}

	class test1 {
		// 非静态的内部类不能声明静态的
		// static String a = "33";

		// 但是可以访问
		public void t() {
			System.out.println(a);
		}
	}
	/**
	 * 定义一个内部类在一个方法中
	 */
	public void fun(int q) {
		int i = 123;
		String s = 1000 + "";
		Object obj = new Object();
		class LocalClass {
			public void localFun() {
				int i = 100;
				System.out.println(++i);
				System.out.println("s = " + s);
				System.out.println("q++ = " + q);
				System.out.println("sssss++ = " + sssss++);
				sssss = 1000  * 12*1;
				System.out.println("sssss* 100 + 20 = " + sssss * 100 + 20);
			}
		}

		new LocalClass().localFun();
	}


	public void f(final int k) {
		final int s = 200;
		int i = 1;
		i = 100;
		final int j = 10;
		// 定义在方法内部(局部的内部类，或者匿名类才有使用变量是final的要求)
		// 方法可以访问外部变量，所以呢局部类也可以访问外部变量。
		class Inner {
			int s = 300; // 可以定义与外部类同名的变量
			// static int m = 20; //不可以定义静态变量
			Inner(int k) {
				inner_f(k);
			}

			int inner_i = 100;
			void inner_f(int k) {
				// 如果这个i变量没有变化的，也是可以的。但是只要你要变化就提示出错
				// i = 100;
				//如果内部类没有与外部类同名的变量，在内部类中可以直接访问外部类的实例变量
				out_i = 100;
				s = 32323;
				// i ++;
				// System.out.println("i = " + i);
				this.inner_i++;
				System.out.println(out_i);
				//可以访问外部类的局部变量(即方法内的变量)，但是变量必须是final的
				System.out.println(j);
				// System.out.println(i);
				//如果内部类中有与外部类同名的变量，直接用变量名访问的是内部类的变量
				System.out.println(s);
				//用this.变量名访问的也是内部类变量
				System.out.println(this.s);
				System.out.println(Inner.this.s);
				//用外部类名.this.内部类变量名访问的是外部类变量
				System.out.println(Outer.this.s);
			}
		}
		new Inner(k);
	}


	public static void main(String[] args) {
		// 访问局部内部类必须先有外部类对象
		Outer out = new Outer();
		out.f(3);

		System.out.println(" ============================== " );
		Outer o = new Outer();
		o.fun(55);

		test test1 = new test();
	}
}

