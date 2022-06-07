/**
 * Project Name:T
 * File Name:Test.java
 * Package Name:com.jiamingku
 * Date:2015-8-5下午2:14:11
 */

package com.jiamingku.j2se.oop.constract;

/**
 * debug这个流程就算知道了所有的
 * 0.单独一个类的初始化，静态成员，包括静态初始化块。(类在java虚拟机链接的准备阶段时候对静态成员就初始化了-注意这个阶段debug是看不到的），
 * 并且按照顺序初始 经历过2次
 * <p>
 * 1.类的静态成员，静态初始化(顺序按照你写的顺序来进行，其实这一步在类的链接，准备阶段进行了一次—并在初始化阶段又进行了一次）
 * <p>
 * 2.构造器方法（里面默认的调用父类构造器)
 * <p>
 * 3.类的成员变量，然后执行构造器里面的方法(先进入构造器方法，然后立马初始化成员变量，然后执行构造器剩下的代码)
 * 4. over
 */
public class SiglonClass {

    // 这句话执行完毕之后，其实进行两次赋值，一次0值，一次赋值1
    private static int a = 1;

    // 这个直接进入常量池了
    private static final int aaa = 1000;

    private static int c;

    static {
        System.out.println(" = ");
        int cc = 3;
        c = 3;
    }


    private String b = "12";

    private String qqqqq = "1111";

    {
        b = "333";
        qqqqq = "qqqq";
    }

    public static void main(String[] args) {
        System.out.println("kkkk");
        SiglonClass p = new SiglonClass();
        // TODO Auto-generated method stub
        System.out.println("ddddd");
    }

    public SiglonClass() {
        super();
        System.out.println("构造器...");
    }

    public SiglonClass(String b) {
        super();
        this.b = b;
    }
}
