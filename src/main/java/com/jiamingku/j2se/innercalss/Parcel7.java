package com.jiamingku.j2se.innercalss;
public class Parcel7 {

	public int a = 100;

	public Wrapping wrap(int x) {
		// Base constructor call:
		// Pass constructor argument.
		return new Wrapping(x) {
			@Override
			public int value() {
				String name = Parcel7.this.getClass().getSimpleName();
				System.out.println("name = " + name);

				System.out.println("this = " + this);

				System.out.println("this.getClass() = " + this.getClass());

				// System.out.println("this.a = " + );
				System.out.println("name = " + this.getClass().getName());
				System.out.println(a);
				return super.value() * 47;
			}
		}; // Semicolon required
	}

	public static void main(String[] args) {
		Parcel7 p = new Parcel7();
		p.a = 10000;
		Wrapping w = p.wrap(10);
		System.out.println(w.value());
//            Demo1 d = new Demo1();
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}
}