package com.jiamingku.test;

import com.jiamingku.j2se.accesscontroller.sonpackage.XiuShiFuSon;

public class XiuShiFuSonTest {
    public static void main(String[] args) {
        XiuShiFuSon x = new XiuShiFuSon();


        System.out.println(x.public_value);
        x.public_method();

        /**
         *
         * {@link XiuShiFuSon}
         */
        // 子类可见，是在子类中可见,应该是在子类，类里面可以
         //  x.protected_method();


        /**
        XiuShiFu shiFu = new XiuShiFu();
        shiFu.protected_method();
         */
    }
}