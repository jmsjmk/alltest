package com.jiamingku.j2se.accesscontroller.sonpackage;

/**
 * Created by jiamingku on 2017/7/9.
 */
public class XiuShiFuSonOtherClassAccess {
    public static void main(String[] args) {
        XiuShiFuSon x1 = new XiuShiFuSon();
	    /**
         * 解释如下
         */
        // System.out.println(x1.protected_value);
	    /**
         * 子类可见，是指在子类中可见，只会现在在子类中使用
         */
        // x1.protected_method();
    }

    public static void p() {
        System.out.println("this is static method");
    }
}
