package com.jiamingku.j2se.oop.statics;

/**
 * Created by jiamingku on 2018/6/13.
 */
public class Static2 extends Static1 {

	private static String aa;

	public static void privatea1() {
		System.out.println(" === ");
	}

	private final static void b() {
		String aa = "";
		System.out.println("aa = " + aa);
	}

	/**
	 * 不能重写但是可以重载
	 *
	 * @param aa
	 */
	public final void c(String aa) {

	}

	public static void main(String[] args) {
		Static2 static2 = new Static2();
		static2.a();
	}


	private final static void ttt() {

	}

//    @
//    public static void a() {
//        String aa = "";
//        System.out.println("aa = " + aa);
//    }

	public static void a1() {
		System.out.println("aa = " + aa);
	}


	/**
	 * 方法不能被继承，但是可以定义一个摸一样的方法
	 */
	private void tt() {

	}

	private final void ssa() {

	}



	public void bb() {
		super.a();
	}
}
