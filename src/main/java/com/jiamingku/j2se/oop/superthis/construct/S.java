package com.jiamingku.j2se.oop.superthis.construct;

/**
 * this:
 * 1.可以出现在方法的调用，
 * 2.还有就是构造器调用,区别就是构造器的调用没有"点"符号
 * 这也导致构造器,方法调用 this的所代表的意识是不同的
 * <p>
 * 方法调用方面来看:this是指定当前的对象,即使出现在父类，或者爷爷对象中也是指调用方法的对象
 * 构造器方面来看:this是代表本类的其他构造方法{@link ThisTest#ThisTest(java.lang.String)}
 * <p>
 * 同样的道理：super：1.可以出现在方法调用中,方法调用这时候需要的是使用点
 * 2.可以出现在构造器中
 * super的作用就是：可以调用父类方法或变量的一个java关键字，但是并不指向父类对象(语法规定在构造器中必须出现在第一行)
 * <p>
 * <p>
 * Created by jiamingku on 2019/6/5.
 */
public class S extends P {

    private Integer var;

    public String sameName = "Sname";

    /**
     * this 起到了区分变量的作用
     *
     * @param var
     */
    public S(Integer var) {
        this.var = var;
    }

    public S(String p1) {
        super(p1);
        this.a();
        super.t();
        System.out.println("p1 = " + p1);
    }

    /**
     * 构造器用this调用是不用点操作的
     */
    public S() {
        this("ddd");
    }

    public static void main(String[] args) {
        S s = new S();
        System.out.println("======================================================= ");
        s.a();
        System.out.println(" = ");
        System.out.println(" = ");
        System.out.println(" = ");
        System.out.println(" = ");
        System.out.println(" = ");
        System.out.println(" = 属性是没有多态的特性的");
        P p = new S();
        System.out.println("s.sameName = " + p.sameName);
        System.out.println("super.sameName = " + ((S) p).sameName);
    }

    public void a() {
        p();
        System.out.println("======================================================= ");
        // 事实上，super.getClass()是表示调用父类的方法。getClass方法来自Object类，它返回对象在运行时的类型。
        // 因为在运行时的对象类型是Test，所以this.getClass()和super.getClass()都是返回Test。
        System.out.println("super.getClass().getSimpleName() = " + super.getClass().getSimpleName());
        System.out.println("========================打印父类信息的按照下面的方法打印=============================== ");
        pointParentObject();
    }

    /**
     * super 并不是指向父类对象的引用,否则System.out.println(super);会出现编译错误
     * <p>
     * 同样super.super.method（）这样的语法是不成立的是错误的,因为不能代表父类的引用
     */
    public void pointParentObject() {
        // System.out.println(super);
        // super.super.
        // 可以获取到父类的名字
        String superName = getClass().getSuperclass().getName();
        System.out.println("superName = " + superName);

        String name = super.getClass().getName();
        System.out.println("name = " + name);

        name = this.getClass().getName();
        System.out.println("name = " + name);
    }


    public void t() {
        System.out.println("super.getClass().getName() = " + super.getClass().getName());
        super.t();
        ttt();
    }

    public static void ttt() {
    }
}
