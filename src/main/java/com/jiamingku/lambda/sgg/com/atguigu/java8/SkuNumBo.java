package com.jiamingku.lambda.sgg.com.atguigu.java8;

public class SkuNumBo {

	private int id;
	private int age;

	public SkuNumBo(int id, int age) {

		this.age = age;
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "SkuNumBo{" +
				"id=" + id +
				", age=" + age +
				'}';
	}
}
