package com.jiamingku.test;

import com.jiamingku.j2se.accesscontroller.XiuShiFu;

public class XiuShiFuSonOtherPackage extends XiuShiFu {

    /**
     * protected修复符在子
     *
     * @param args
     */
    public static void main(String[] args) {
        XiuShiFuSonOtherPackage x = new XiuShiFuSonOtherPackage();
        System.out.println(x.protected_value);
        System.out.println(x.public_value);
        x.public_method();
        x.protected_method();

        /**
         * 注意：在子类中可见,必须是子类对象.protected
         *
         * 那怕，是父类对象也是不可以
         *
         */
        // XiuShiFu shiFu = new XiuShiFu();
        // shiFu.protected_method();

    }
    public void a() {
        System.out.println(protected_value);
    }
}