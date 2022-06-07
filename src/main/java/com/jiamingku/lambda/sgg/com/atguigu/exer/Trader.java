package com.jiamingku.lambda.sgg.com.atguigu.exer;

import java.util.List;

//交易员类
public class Trader {

	private String name;
	private String city;

	public Trader() {
	}

	public Trader(String name, String city) {
		this.name = name;
		this.city = city;
	}

	public static void main(String[] args) {
		List<Integer> list = null;

		list.forEach(System.out::print);
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Trader [name=" + name + ", city=" + city + "]";
	}

}
