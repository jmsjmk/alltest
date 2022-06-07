package com.jiamingku.j2se.accesscontroller.sonpackage;

import com.jiamingku.j2se.accesscontroller.XiuShiFu;

public class XiuShiFuSon extends XiuShiFu {

	/**
	 * protected修复符在子包中子类进行访问
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * 对于子类可见，但是在别的类中使用子类对象是不可见的
		 * 2020-3-24--就是说只能在子类的类中使用其他的都不能使用.除非在同包中
		 * 同包中你就不用区分什么子类不子类的了直接使用就行了
		 * {@link }
		 */
		XiuShiFuSon x = new XiuShiFuSon();
		System.out.println(x.protected_value);
		System.out.println(x.public_value);
		x.public_method();
		x.protected_method();

		XiuShiFu x1 = new XiuShiFu();


		// x.no_method(); 子包都不可见，其实无论是子包还是其他包，只要不在同一个包中都不可见

		/**
		 * 注意：在子类中可见,必须是子类对象.protected
		 *
		 * 那怕，是父类对象也是不可以
		 *
		 */
//		 XiuShiFu shiFu = new XiuShiFu();
//		 shiFu.protected_method();
//		 shiFu.protected_value;
	}

	public void a() {
		System.out.println(protected_value);
	}

	protected  void bb() {

	}
}