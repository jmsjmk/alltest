package com.jiamingku.j2se.methodoverrite.hiding;

import com.jiamingku.j2se.exception.ExtendS1;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Sub extends Super {

    // ----------------------------------------------------------重写的第一步 ,返回值 ,修饰符, 异常, 参数

    /**
     * 方法重写时候，返回值的在java8的时候可以是他的子类行就可以,但是不能没有返回值
     * 道理是一样的多态的原因,参考下面的描述
     *
     * @return
     */
    @Override
    public Integer testReturnValue() {
        return 111;
    }

    /**
     * 方法重写时候，返回值的在java8的时候可以是他的子类行就可以,访问权限
     * <p>
     * why？其实这个很好理解,最终设计到重写的东西都会设计到多态的那种调用
     * 如果父类可以调用，但是真正执行时候子类的访问权限低于父类,这样方法就没办法调用成功
     * ，跟异常的那个类似,父类不抛出异常,子类抛出异常
     * {@link ExtendS1#a()}
     * <p>
     * 不能低于父类的访问修饰符.---不能低于父类的访问修饰符
     *
     * @return
     */
    @Override
    public Integer testXiuShiFu() {
        return 111;
    }

    // 参数列表不能是子类型,必须是严格匹配的,这个也很简单，如果多态的那种情况，传递的类型是其他的子类型 你咋办。没办法处理
    //	@Override
    //	public Integer testParameter(String string) {
    //		return super.testParameter();
    //	}
    @Override
    public void testbangding() {
        System.out.println(" sub bangding...  ");
    }

    /**
     * 返回值可以是子类型,但是参数必须符合原始的类型
     *
     * @param o
     * @return
     */
    @Override
    public Integer testParameter(Object o) {
        return 100;
    }

    // -------------------------------------------------高级部分所谓的方法隐藏的概念

    /**
     * 不能用override修饰
     */
    private void privateMethod() {
        System.out.println("sub   method a is invoken!!!!!");
    }

    /**
     * 这也是一种方法隐藏
     */
    static void staticMethod() {
        System.out.println("sub static method  ");
    }

    private static void privateStaticMethod() {

    }

    // -------------------------------------------------还有一种更高级的做法就是限制子类定义同名方法

//
//	final static void finalStaticMethod() {
//        System.out.println("finalStaticMethod = " + true);
//    }


    private final static void privateStaticFinalMetod() {

    }


    static void method() {
        System.out.println("Sub class method");
    }

    public static void main(String args[]) {
        Super.method();
        Sub.method();

        Super.staticMethod();
        Sub.staticMethod();

        System.out.println(" ========静态方法多态的特性 ");
        // ----------------------
        // --隐藏的方法是没有程序绑定的说法的
        Super super1 = new Sub();
        super1.staticMethod();

        Sub sub = new Sub();
        sub.staticMethod();


        // --------------------------
        System.out.println(" ================================== ");

        Super s = new Sub();
        s.a();
    }

//    @Override
//	public void a() {
////		System.out.println("sub a ");
//		this.privateMethod();
//////		this.staticMethod();
//////		this.testbangding();
//	}

    /**
     * 但是子不能超过父类所定义的类型
     * <p>
     * 如果子类实现 不能抛出超过父类异常,可以父类异常的子类型
     *
     * @throws Exception
     */
    @Override
    public void e() throws FileNotFoundException {

    }

    /**
     * 可以不抛出异常
     */
    @Override
    public void e1() {
    }

//    static final void finalStaticMethod() {
//        System.out.println("finalStaticMethod = " + true);
//    }

    /**
     * 这个也可以隐藏-----
     */
    private final void finalMethod() {

    }
}
