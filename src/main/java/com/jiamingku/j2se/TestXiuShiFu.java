package com.jiamingku.j2se;

import com.jiamingku.j2se.accesscontroller.XiuShiFu;

/**
 * Created by jiamingku on 2018/11/23.
 */
public class TestXiuShiFu {

    public static void main(String[] args) {

        System.out.println("TestXiuShiFu.class.getName() = " + TestXiuShiFu.class.getName());

        String[] a = {"3", "3"};
        String line = org.springframework.util.StringUtils.arrayToCommaDelimitedString(a);
        System.out.println("line = " + line);

        System.out.println("line = " + TestXiuShiFu.class);

        XiuShiFu xiuShiFu = new XiuShiFu();
        System.out.println(xiuShiFu.public_value);
    }
}
