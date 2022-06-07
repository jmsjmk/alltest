package com.jiamingku.j2se;


import java.util.ArrayList;
import java.util.List;

/**
 * 可变参数,
 * <p>
 * 不传递:size=0,传递null使用时候注意.
 */
public class Varargs {


    public void test3() {
        // error 错误的
        Object a = String.class;
    }

    /**
     * 泛型与可变参数
     */
    public static <T> List<T> makeList(T... t) {
        List<T> list = new ArrayList<>();
        return list;
    }

    /**
     * 可变参数(这三个...),空格无所谓的
     * <p>
     * 当t 不传递的时候，他的size=0
     * 当t 传递null的时候, 操作的时候要注意
     *
     * @param p
     * @param t 参数可以不传递
     */
    public void a(String p, String... t) {
        System.out.println(p);
        if (null == t) {
            System.out.println(" is null ");
        } else {
            System.out.println("不传递---数组的长度==t.length = " + t.length);
        }
        // 新循环会null会产生异常
        for (String s : t) {
            System.out.println(s);
        }
        System.out.println("t.getClass().getSimpleName() = " + t.getClass().getSimpleName());
    }

    public void aa(String... t) {
        if (null == t) {
            System.out.println(" is null ");
        } else {
            System.out.println("t.length = " + t.length);
        }
        // 新循环会null会产生异常
        for (String s : t) {
            System.out.println(s);
        }

        System.out.println("t.getClass().getSimpleName() = " + t.getClass().getSimpleName());
    }

    /**
     * 主要是看...的位置，与定义变量的位置关系
     */
    public void a1(String p, String... t) {
    }

    public void a2(String p, String... t) {
    }

    public void a3(String p, String... t) {
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Varargs varargs = new Varargs();
        try {
            // 可变参数-是可以不传递参数，语法的支持
            varargs.a("d");
        } catch (Exception e) {
            e.getMessage();
        }
        System.out.println(" ========================================== ");

        // --- 不传递就是个空数组
        varargs.aa();
    }
}
