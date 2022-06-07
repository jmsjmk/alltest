package com.jiamingku.j2se.innercalss;

public class Fk {
	private String x;

	public Fk(String x) {
		this.x = x;
	}

	@Override
	public String toString() {
		return "Fk{" +
				"x='" + x + '\'' +
				'}';
	}
}

class Test4 {
	public Fk hehe() {
		//把后面的一对大括号去掉呢，呵呵 ，new FK() 等价于 new Fk('fk'){};
		// 虽然是等价但是效果大不相同。一个是创建了Fk，一个是匿名类对象。
		return new Fk("fk") {
		};
	}

	public static void main(String[] args) {
		Test4 t = new Test4();
		Fk f = t.hehe();
		System.out.println(f);
	}
}

