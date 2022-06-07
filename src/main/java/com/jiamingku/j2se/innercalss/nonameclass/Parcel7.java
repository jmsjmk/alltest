package com.jiamingku.j2se.innercalss.nonameclass;

public class Parcel7 {
	/**
	 * cont()方法将下面两个动作合并在一起：返回值的生成，与表示这个返回值的类的定义！进一步说，这个类是匿名的，
	 * 它没有名字。更糟的是，看起来是你正要创建一个Contents对象：
	 * <p>
	 * return new Contents() 　但是，在到达语句结束的分号之前，你却说：“等一等，我想在这里插入一个类的定义”:
	 * return new Contents() {
	 * private int i = 11;
	 * public int value() { return i; }
	 * };
	 * 这种奇怪的语法指的是：“创建一个继承自Contents的匿名类的对象。”通过new 表达式返回的引用被自动向上转型为对Contents的引用。
	 * 匿名内部类的语法是下面例子的简略形式：
	 * <p>
	 * class MyContents implements Contents {
	 * private int i = 11;
	 * public int value() { return i; }
	 * }
	 * return new MyContents();
	 *
	 * @return
	 */
	public Contents contents() {
		return new Contents()//Contents cannot be resolved to a type
		{
			private int i = 11;

			public int value() {
				return i;
			}
		};
	}

	public static void main(String[] args) {
		Parcel7 o = new Parcel7();
		//Contents c = o.contents();
	}
}