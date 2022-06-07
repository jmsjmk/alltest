package com.jiamingku.j2se.exception;

import org.junit.Test;

import java.io.IOException;

/**
 * 下面的表现的情况,完全是兼顾java多态的特性,
 * <p>
 * Created by jiamingku on 2018/5/22.
 */
public class ExtendS1 extends Extendsexception {
    /**
     * checked,unchecked都是编译器帮你检查的(java的方法抛出checked异常，调用方法的地方就一定要捕获,编译器来强制要求你)
     * <p>
     * 父类有方法声明抛出异常,子类可以抛出，也可以不抛出异常,也可以抛出父类异常的子类型，原因是什么呢？
     * (可以说这种表现情况说明,完全是为了兼顾多态这个特性)
     * <p>
     * 父类抛出异常：
     * <p>
     * 如果是多态的引用必须的捕获这个异常，程序代码1
     * <p>
     * 如果子类对象直接引用就不需要捕获了  程序代码2
     * <p>
     * 说明编译器--是通过具体的类型去检查---如果声明的类型是父类型的话 就去检查父类的方法签名-如果是子类就检查子类的方法签名
     * ================
     * <p>
     * 抛出异常可以是子类异常，其实异常的抛出在方法重写的时候没有严格的限制，只是不能超过父类异常就行,必须是父类异常的子类型
     * 可以是对应异常的子类
     * 可以不抛出
     * 只是捕获异常的时候，根据对应的类 的方法签名进行捕获。
     */
    @Override
    public void a()  {
    }


    /**
     * finally 里面抛出的异常会屏蔽前面的异常
     */
    @Test
    public void t() {
        try {
            try {
                throw new NullPointerException();
            } finally {
                throw new IOException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void t1() {
//        a();
    }

    /**
     * 抛出的异常是子类
     *
     * @throws IOException
     */
    @Override
    public void a1() throws IOException {
    }

    /**
     * 不能超过父类的异常【可以是子类或者不抛出类型】
     * <p>
     * 解释：如果多态的方式引用话，编译器不知道应该普获那种所以就报错
     *
     * @throws Exception
     */
    @Override
    public void a3() {
//		super.a3();
    }

    /**
     * 父类不抛出一异常，子类不能抛出异常
     * <p>
     * 同样的道理是一样的
     *
     * @throws IOException
     */
//	@Override
//	public void a2() throws IOException {
//	}

    public static void main(String[] args) {

        /** 程序代码1*/
//         Extendsexception extendsexception = new ExtendS1();
//         extendsexception.a();

        /** 程序代码2*/
         ExtendS1 extendsexception1 = new ExtendS1();
         extendsexception1.a();

        // 协变
        ExtendS1 extendsexception = new ExtendS1();
        try {
            extendsexception.a1();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 逆变就是 强制类型转换
        // ExtendS1 extendS1 = (ExtendS1) extendsexception;
    }

    public static void a(Object o) {
        o.hashCode();
    }
}
