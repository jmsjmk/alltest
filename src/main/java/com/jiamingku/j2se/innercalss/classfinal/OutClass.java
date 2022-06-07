package com.jiamingku.j2se.innercalss.classfinal;

/**
 * 局部变量-不能随便访问,只能是final的,因为局部变量是随着方法的结束就结束，
 *   但是类的声明周期是不一样的，所以是final的
 *
 * 成员变量-随便的访问. 因为内部类里会有指向外部了的引用，外部类的声明周期还大于方法的调用。
 */
public class OutClass {
	private int age = 12;

	public void outPrint(final int x) {

		int a= 100;
		class InClass {
			public void InPrint() {
				System.out.println(x);
				System.out.println("a ++  = " );

				//成员变量，谁便访问
				System.out.println(age);
				System.out.println(age++);
			}
		}
		new InClass().InPrint();
	}

	public void outPrint1( int x) {
		instanceClass instanceClass = new instanceClass();
		instanceClass.tt(x);
		// final 限制是在局部内部类中的限制
		instanceClass.tt(x++);
	}



	public class instanceClass{

		public void tt(int t) {
			System.out.println("t = " + t);
		}
	}
}
