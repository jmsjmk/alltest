package com.jiamingku.j2se.innercalss.classdianthis;

public class Outer {
	private static int i = 1;
	private String b = "12";
	private int j = 10;

	Runnable t = new Runnable() {
		@Override
		public void run() {
		}
	};

	// 10 Consumer<Integer> b1 = System::exit;

	// Outer o = Outer::new;
	public static void outer_f1() {
	}


	public void outer_f2() {
	}

	// 静态内部类可以用public,protected,private修饰
	// 静态内部类中可以定义静态或者非静态的成员
	static class Inner {
		static int inner_i = 100;
		int inner_j = 200;
		static int i =100;
		static void inner_f1() {
			//静态内部类只能访问外部类的静态成员(包括静态变量和静态方法)
			System.out.println("Outer.i" + i);
			inner_i++;
			// inner_j ++; 静态方法不能访问非静态
			i++;
			// j ++;
			System.out.println("inner_i = " + inner_i);
			// 这个很好理解，静态方法不能使用非静态成员，这个道理很容易想通
			// System.out.println("inner_j = " + inner_j);
			outer_f1();
		}

		void inner_f2() {
			// 静态内部类不能访问外部类的非静态成员(包括非静态变量和非静态方法)
			// System.out.println("Outer.i"+j);
			//
			// outer_f2();
			inner_j++;
			// 访问自己没问题的
			System.out.println("inner_j = " + inner_j);
			int a = 3;
			Runnable t = new Runnable() {
				@Override
				public void run() {
//					System.out.println("a++ = " + a++);
				}
			};
		}
	}

	class tt {
		void a() {
			System.out.println("this.i = " + Outer.this.b);
		}
	}
	public void outer_f3() {
		class tt1 {
			private String c;
			void a() {
				System.out.println("c = " + this.c);
			}
		}


		// 外部类访问内部类的静态成员：内部类.静态成员
		System.out.println(Inner.inner_i);
		//	Inner.inner_f1();
		// 外部类访问内部类的非静态成员:实例化内部类即可
		Inner inner = new Inner();
		inner.inner_f2();
	}


	public static void main(String[] args) {
		new Outer().outer_f3();
	}
}

