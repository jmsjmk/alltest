package com.jiamingku.j2se.accesscontroller;

import com.jiamingku.j2se.accesscontroller.sonpackage.XiuShiFuSonPackgeTest;

/**
 * Created by jiamingku on 2018/1/24.
 */
public class XiuShiFuSamePackgeTest {

    /**
     * {@link XiuShiFuSonPackgeTest}
     *
     * @param args
     */
    public static void main(String[] args) {
        XiuShiFu x = new XiuShiFu();

        /**
         * 同一个包中可见
         */
        x.protected_method();
        x.public_method();
        x.no_method();
        System.out.println("x.no_xiushifu = " + x.no_xiushifu);
        System.out.println("x.public_value = " + x.public_value);
        System.out.println("x.protected_value = " + x.protected_value);
        // 私有的方法不能访问
//         x.private_method();
    }

}
