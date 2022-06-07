package com.jiamingku.j2se.innercalss.classdianthis;

public class Test {

	public static void main(String[] args) {
		Student s = new Student() {
			public void run() {
				System.out.println("我是学生，我要好好学习！");
			}
			//调用无参构造器
		};
		s.run();

		s = new Student("hello") {
			@Override
			public void run() {
				super.run();
			}
		};
		s.run();
	}
}
