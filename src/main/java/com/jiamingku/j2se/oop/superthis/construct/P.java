package com.jiamingku.j2se.oop.superthis.construct;

/**
 * Created by jiamingku on 2019/6/5.
 */
public class P {

	public String sameName="Pname";

	private String p;
	private String p1;

	public String getP1() {
		return p1;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public P() {
	}

	public P(String p1) {
		this("11","33");
	}

	public P(String p1, String p) {
		System.out.println("父类打印:this.getClass().getSimpleName() = " + this.getClass().getSimpleName());
		System.out.println("父类打印:super.getClass().getName() = " + super.getClass().getName());
		this.p1 = p1;
		this.p = p;
	}

	public void p() {
		System.out.println("父类打印:super.getClass().getName() = " + super.getClass().getName());
	}
	public void t() {
		System.out.println("super.getClass().getName() = " + super.getClass().getName());
	}
}
