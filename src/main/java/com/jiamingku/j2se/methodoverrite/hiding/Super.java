package com.jiamingku.j2se.methodoverrite.hiding;

import java.io.IOException;

public class Super {

    public Number testReturnValue() {
        return null;
    }

    protected Integer testXiuShiFu() {
        return null;
    }

    public Object testParameter(Object o) {
        return 100;
    }

    public void testbangding() {
        System.out.println(" Super bangding...  ");
    }

    // ---------------------------------------------------------------------

    /**
     * 私有的方法(默认final),子类不可以重写,只是定义了一个一模一样的方法
     * 但是如果public final 方法的话-不可能重写因为final方法不能重写
     * <p>
     * 子类相当于一种从新定义，但是不会有多态的特性，
     * <p>
     * 这种方式叫隐藏，隐藏本类方法(多态·调用不到子类的方法,相当于子类方法隐藏了)
     */
    private void privateMethod() {
        System.out.println("parent method a is invoken!!!!!");
    }

    /**
     * 子类可以定义一个一摸一样的方法,但是此方法相当于隐藏,没有任何多态的特性
     * {@link Sub#staticMethod()}
     */
    static void staticMethod() {
        System.out.println("super static method ==================== ");
    }
    /**
     * 这个也可以隐藏-----公开的就报错-
     */
    private final void finalMethod() {

    }



    // ---------------------------------------------------------------------

    /**
     * 限制子类不能定义同名的方法，返回值无所谓
     * <p>
     * final, 这个应该不是覆盖，就是限制子类不能有同名的方法
     */
     static final void finalStaticMethod() {
        System.out.println("finalStaticMethod = " + true);
    }

    /**
     * 子类可以定义一个一摸一样的-但是可以限制子类的定义同名方法
     */
    private static void privateStaticMethod() {
        System.out.println("true = " + true);
    }

    private static final void privateStaticFinalMetod() {

    }

    static void method() {
        System.out.println("Super class method");
    }

    public void a() {
        System.out.println(" super a ");
        this.privateMethod();
        this.staticMethod();
        this.testbangding();
    }

    public void e() throws IOException {
    }

    protected void p() {

    }

    public void e1() throws IOException {
    }

    public static void main(String[] args) {
        Super s = new Super();
        s.a();
        System.out.println(" ======================= ");
        s = new Sub();
        s.a();
    }
}