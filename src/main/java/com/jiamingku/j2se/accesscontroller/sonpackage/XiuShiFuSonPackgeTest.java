package com.jiamingku.j2se.accesscontroller.sonpackage;

import com.jiamingku.j2se.accesscontroller.XiuShiFu;
import com.jiamingku.j2se.accesscontroller.XiuShiFuSamePackgeTest;


/**
 * Created by jiamingku on 2018/1/24.
 */
public class XiuShiFuSonPackgeTest {
    public static void main(String[] args) {
        XiuShiFu x = new XiuShiFu();
        /**
         * 必须是同包下面--就是子包都不行
         * {@link XiuShiFuSamePackgeTest}
         * */
        // x.protected_method();
        /**
         * 必须是同包下面，就是子包中也不能访问 default的参数
         */
        //  x.no_method();
        x.public_method();
        System.out.println("x.public_value = " + x.public_value);
    }
}
