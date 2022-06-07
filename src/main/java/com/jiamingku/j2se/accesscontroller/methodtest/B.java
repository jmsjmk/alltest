package com.jiamingku.j2se.accesscontroller.methodtest;

import com.jiamingku.j2se.accesscontroller.methodtest.son.Bson;
import com.jiamingku.j2se.accesscontroller.sonpackage.XiuShiFuSon;

public abstract class B {

    public void a() {
        XiuShiFuSon xiuShiFuSon = new XiuShiFuSon();
        xiuShiFuSon.public_method();
        /**
         * 子类实现
         */
        Bson bson = new Bson();
        /**
         * 调用子类方法错误,因为包活的方法，只能在本类还有子类中实现
         */
        // bson.aa(); 下面的代码等效这行代码.
        aa();
    }

    protected abstract String aa();

    public static void main(String[] args) {
        // ----- 数字的新版本写法,只是为了好看-书写数字好看.
        int newVersion = (1 + 1) % 1_000_000; // version范围0~999999
        System.out.println("newVersion = " + newVersion);

        System.out.println("newVersion = " + newVersion);
        int x2 = 5_2;
        System.out.println("x2 = " + x2);

        // ---------测试这个方法访问权限--这个感觉好像是在调用了子类的方法，其实他是调用的父类的aa,在程序的运行过程中通过程序绑定来进行执行的
        B b = new Bson();
        b.aa();
    }
}