/**
 * Project Name:T
 * File Name:Test.java
 * Package Name:com.jiamingku
 * Date:2015-8-5下午2:14:11
 */

package com.jiamingku.j2se.oop.constract;

/**
 * 继承的配置关系，
 * <p>
 * 1.先初始化父类的静态成员，静态初始化块，然后子类的静态成员，静态初始化块 主要目的完成类的加载属性
 * 使用这个类必须加载这个类,在类加载的初始化阶段就完成静态属性的初始化, 注意加载也是有层级关系的,一定是最顶层的先加载
 * <p>
 * 2.子类创建对象，先调用父类的构造器，父类构造器初始化成员变量，然后执行构造器里面的代码，
 * 原理就是：进入构造器之后，
 * 2.1：执行完super()进入父类的构造器，初始化父类成员变量，
 * 2.2：执行构造器剩下的代码。
 * <p>
 * 3.然后子类的成员变量，子类的构造器中的语句
 * =========================================
 * 总结:就是jclasslib插件查看,可以看到加载的方法调用关系.
 */
public class Son extends Parent {

    private static int a = 1;
    private static int c;

    static {
        System.out.println(" = ");
        int cc = 3;
        c = 3;
    }

    private String b = "12";
    private int ttt;
    private int ttty = 1;
    private String qqqqq = "1111";

    public static void main(String[] args) {
        System.out.println("kkkk");
        Son p = new Son();
        // TODO Auto-generated method stub
        System.out.println("ddddd");
    }


    public Son() {
        /**
         * 构造器方法进来的时候，this 对象里面的变量会进行系统默认的初始化
         *
         * 这时候ttt 与 ttty 都等于0
         */
        System.out.println("构造器...");
    }

    public Son(String b) {
        super();
        this.b = b;
    }
}
