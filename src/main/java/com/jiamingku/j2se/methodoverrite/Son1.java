package com.jiamingku.j2se.methodoverrite;


public class Son1 extends Father {

    private Object o;

    private static boolean a = false;
    public void method() {
        System.out.println("子类方法，对象类型：" + this.getClass());
        System.out.println(" ==================== ");
        super.method();
    }

	/**
	 * 写不写override都是重写,可以有效的防止代码写错名
     *
     */
    @Deprecated
    public void methOd() {

    }

    public static void main(String[] args) {
        Father sample = new Son1();//向上转型
        sample.method();
        System.out.println("调用的成员：" + sample.name);


        Son1 son1 = new Son1();

	    /**
	     * 会有对应的提示
         */
        son1.methOd();;
    }
}