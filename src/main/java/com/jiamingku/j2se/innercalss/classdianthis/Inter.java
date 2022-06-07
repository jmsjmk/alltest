package com.jiamingku.j2se.innercalss.classdianthis;

/**
 * Created by jiamingku on 2018/6/17.
 */
public interface Inter {

    void displayLocvar();

	/**
	 * 默认就是 静态的
     */
    class B {
        void a() {

        }
    }

	/**
     *
     * http://blog.csdn.net/hikvision_java_gyh/article/details/8964155
     * https://blog.csdn.net/jiamingku/article/details/86160640
     生成一个静态内部类不需要外部类成员：这是静态内部类和成员内部类的区别。
     静态内部类的对象可以直接生成：Outer.Inner in = new Outer.Inner();
     而不需要通过生成外部类对象来生成。这样实际上使静态内部类成为了一个顶级类(正常情况下，你不能在接口内部放置任何代码，但嵌套类可以作为接口的一部分，因为它是static 的。只是将嵌套类置于接口的命名空间内，
     这并不违反接口的规则）


     */
    static class BB {

        public static void a() {
            System.out.println("true = " + true);
        }
    }
}
