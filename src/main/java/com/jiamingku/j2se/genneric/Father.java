package com.jiamingku.j2se.genneric;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型与多态不冲突,这句话如何理解呢?
 * CCCC<ShengWu> cccc = new CCCC<ShengWu>();
 * T 定义成为生物, 但是你setWhat时候可以设置子类.
 *
 * @param <T>
 */
class CCCC<T> {

    T twhat;

    void setTwhat(T t) {
        this.twhat = t;
    }

    T get() {
        System.out.println("true = " + true);
        return null;
    }

    public static void main(String[] args) {
        CCCC<CCCC<String>> cccccccc = new CCCC<>();
        // a行数据
        CCCC<String> a = cccccccc.get();
        System.out.println("cccccccc = " + cccccccc);
        List list = new ArrayList<>();
        List<Integer> list1 = (List<Integer>) list;
    }
}

/**
 * 1)泛型的本质:给编译器使用
 * 2)定义描述父子关系等,是否可以省略等
 */
public abstract class Father<T1, T2> {
    T1 name;

    public abstract void test(T1 t);
}

// ---------------------------------------------------------------------------------------------------------------------
/**
 * 错误:找不到，S，Q
 */
//class Child11 extends Father<S, Q> {
//    String t2;
//
//    @Override
//    public void test(String t) {
//
//    }
//}


/**
 * 子类不是泛型类：必须的指定具体的泛型类型。
 * 子类声明时指定指定具体类型
 * 属性的类型为具体类型
 * 方法同理
 */
class Child1 extends Father<String, Integer> {
    String t2;

    @Override
    public void test(String t) {

    }
}


/**
 * Created by jiamingku on 2018/5/27.
 * <p>
 * 继承泛型的父类，但是 继承时候没有指定 类型 ----编译通过
 * <p>
 * 如果这时候，没有指定 parent的类型T
 * 会出现什么问题呢？
 * <p>
 * 不写 的话就是原始的类型，你这个有一个固定的类型，不符合覆盖的要求。。。
 */

class Child444<T, S> extends Father {

    @Override
    public void test(Object t) {

    }


    //    @Override  因为这时候不符合逻辑要求.
//    public void test(T t) {
//        this.t = t;
//
}

/**
 * 子类与父类同时擦除
 *
 * @author dell
 */
class Child4 extends Father {
    String name;

    @Override
    public void test(Object t) {
        // TODO Auto-generated method stub
    }
}
// ---------------------------------------------------------------------------------------------------------------------

/**
 * 子类为泛型类，类型使用时确定
 *
 * @param <T2>
 * @param <T1>
 * @author dell
 */
class Child2<T2, T1> extends Father<T1, T2> {
    String t2;

    @Override
    public void test(T1 t) {
        // TODO Auto-generated method stub
    }
}

/**
 * 子类为泛型类，父类不指定类型(也可以说是父类的泛型擦除),会自动使用Object替换
 *
 * @author dell
 */
class Child3<T1, T2> extends Father {
    T1 name;

    @Override
    public void test(Object t) {
        // TODO Auto-generated method stub
    }
}


/**
 * 错误：子类擦除，父类使用泛型,他不知道 T，T1是什么
 *
 * @author dell
 * <p>
 * class Child5 extends Father<T,T1>{
 * String name;
 * <p>
 * }
 * <p>
 * Created by jiamingku on 2018/10/16.
 * 方法定义使用形式参数,使用时候必须传递真实参数.这个也是一样的.
 * <p>
 * public class Son3<T> extends Parent<T> {
 * }
 */

/**
 * Created by jiamingku on 2018/10/16.
 * 方法定义使用形式参数,使用时候必须传递真实参数.这个也是一样的.
 * <p>
 * public class Son3<T> extends Parent<T> {
 * <p>
 * }
 */
//public class Son3123 extends Parent<T> {
//
//