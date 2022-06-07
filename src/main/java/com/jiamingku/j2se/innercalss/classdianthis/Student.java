package com.jiamingku.j2se.innercalss.classdianthis;

public class Student {
	private String name;
	
	public void run(){
		System.out.println("好好学习");
		System.out.println("name = " + name);
	}
	
	public Student() {
		super();
		System.out.println("大傻逼！");
	}

	public Student(String name) {
		super();
		this.name = name;
	}
}
