package com.jiamingku.j2se.innercalss;

class WithInner {
	class Inner {
		Inner() {
			System.out.println("this is a constructor in WithInner.Inner");
		}

		;
	}
}


public class InheritInner extends WithInner.Inner {
	// ! InheritInner() {} // Won't compile
	InheritInner(WithInner wi) {
		wi.super();
		System.out.println("this is a constructor in InheritInner");
	}


//	InheritInner() {
//		wi.super();
//		System.out.println("this is a constructor in InheritInner");
//	}


	public static void main(String[] args) {
		WithInner wi = new WithInner();
		InheritInner ii = new InheritInner(wi);
	}
}
