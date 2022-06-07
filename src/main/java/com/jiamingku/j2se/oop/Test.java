package com.jiamingku.j2se.oop;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiamingku on 2020/7/3.
 */
public class Test {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        boolean b = CollectionUtils.isNotEmpty(list);
        System.out.println("b = " + b);

        list.add("1");;
        b = CollectionUtils.isNotEmpty(list);
        System.out.println("b = " + b);
        String s = "此评价是恶意差评直接无效orderNo3333评价评价超过72小时2020-090099090900909超过72小时33333333你中国无所33333333333333333333333";
        System.out.println("s.length() = " + s.length());
    }
}
