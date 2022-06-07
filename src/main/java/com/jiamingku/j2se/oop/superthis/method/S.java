package com.jiamingku.j2se.oop.superthis.method;

import org.junit.Test;

/**
 * this. 操作属性与方法的区别
 * Created by jiamingku on 2018/9/5.
 */
public class S extends P {

    public String value = "s value";

    public static void main(String[] args) {
        S s = new S();
        s.c();
//        System.out.println(" ======================= ");
//        s.p();

    }

	/**
     * 同名属性的问题
     *
	 * 父子类中同名属性,super获取父类属性.
     */
    @Test
    public void demo1() {
        System.out.println("this.value = " + this.value);
        System.out.println("super.value = " + super.value);
        // 子类没有同名属性，可以两种方式使用
        System.out.println("this.bbbb = " + this.bbbb);
        System.out.println("super.bbbb = " + super.bbbb);
    }

    /**
     * this在操作属性的时候,当前类中有同名属性时候就是操作的当前类的属性
     *
     */
    @Test
    public void demo2() {
        System.out.println("this.value = " + this.value);
        super.a();
    }

    @Override
    protected void a() {
        System.out.println("this = " +this);
        System.out.println("this.value = " + this.value);
    }



    public void p() {
        System.out.println("value = " + value);
        System.out.println("this.value = " + this.value);
        System.out.println("value = " + super.value);
    }



    public void c() {
        super.a();
        System.out.println("super.value = " + super.value);
        System.out.println("this.bbbb = " + this.bbbb);
    }
}
