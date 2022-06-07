package com.jiamingku.j2se.oop.superthis;


/**
 * Created by mingku.jia on 2017/12/25.
 */
public class Son extends Person {


    public void f() {
        System.out.println("Son");
    }

    /**
     * super必须在在第一行,调用其他的构造器,super调用构造器时候不需要使用点符号
     * super不是指定父类对象，只是代表你只能访问父类方法,同样构造器中this也必须放在第一行中
     *
     * <p>
     * super并没有代表超类的一个引用的能力(只是代表调用父类的方法而已)所以在子类的方法中，不能这样用
     * 1.System.out.println(super);
     * 2.super.super.mathod();
     * <p>
     * 事实上，super.getClass()是表示调用父类的方法。getClass方法来自Object类，它返回对象在运行时的类型。因为在运行时的对象类型是Test，
     * 所以this.getClass()和super.getClass()都是返回Test。
     * <p>
     * 此外，由于getClass()在Object类中定义成了final，子类不能覆盖该方法，所以，在test方法中调用getClass().getName()方法，
     * 其实就是在调用从父类继承的getClass()方法，等效于调用super.getClass().getName()方法，所以，super.getClass().getName()方法返回的也应该是Test。 
     * <p>
     * 如果想得到父类的名称，应该用如下代码:: getClass().getSuperClass().getName();
     */
    public Son(String name, int age) {
        // 除了注视 任何代码不能放在第一行
        super(name, age);
        System.out.println("super.getClass() = " + super.getClass());
        System.out.println("super.toString() = " + super.getClass().getName());
        System.out.println("this.getClass() = " + this.getClass());
        System.out.println("this.getClass().getName() = " + this.getClass().getName());
        this.name = "";
        System.out.println("this.name = " + this.name);
        System.out.println("super.staticNameValue = " + super.staticNameValue);

    }

    private void b() {
        System.out.println(" =... ");
        System.out.println(" =... ");
        System.out.println(" =... ");
        System.out.println(" =... ");
        System.out.println("son b method ..");
    }


    public Son(String type) {
        super.testPPPP();
    }

    public Son() {
        super();
    }

    /**
     * 如果这时候调用this.aaa()死循环了
     * <p>
     * 调用时候如果从写了方法，想调用父类的方法就是使用super
     */
    @Override
    public void aaa() {
        super.aaa();
        super.toString();
    }


    public static void main(String[] args) {
        /**
         *  this：代表调用该方法的这个对象，哪怕this传递到父类中也代表真正运行的对象，
         *
         *  this: 再次理解，this指向实际调用的对象,并不一定指向当前类(this并不是指向当前类)
         */
        Son son = new Son("son", 1);
        son.aaa();
        System.out.println("==================================");

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        Person person = new Son();
        person.test();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        //
        Son son1 = new Son("100");

    }

    @Override
    public String toString() {
        String a = "Son{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
        return a;
    }
}
