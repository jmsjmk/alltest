package com.jiamingku.j2se.oop;

/**
 * 1.接口中可以定义的元素有那些
 * <p>
 * Created by jiamingku on 2019/11/5.
 */
public interface InterfaceDefineMeta {

    // ---------------------------------------接口中可以定义的东西-对应的默认添加属性------------------------------

    /**
     * 定义变量默认就是 public static final 的
     **/
    public static final int a = 10;
    int b = 100;

    /**
     * 无论你写不写public 都会给你默认的添加上public abstract，
     * 对于方法默认的就给你加上public abstract的方法了。
     */
    void t();

    public abstract void tt();


    /**
     * 接口中可以定义内部类。 一看就是内部静态类
     */
    class In {
    }

    public static class InStacit {
    }

    // 接口中可以定义静态方法
    public static void tttt() {
        System.out.println("a = " + a);
    }

    // 接口中可以定义枚举
    enum enumTest {
        A("1", "2");

        private String a;
        private String b;

        enumTest(String a, String b) {
            this.a = a;
            this.b = b;
        }
    }
    //  ---------------------------------------------------------------------

    /**
     * 可以定义接口并且是 公共的静态接口
     */
    interface innerInterface {
        void what();
    }


    public interface innerInterface1 {

    }

    interface innerInterface2 {

    }

    default void t3ttt() {
        System.out.println("what");
    }

}

/**
 * 接口的实现中可以完全忽略调子接口的实现。
 */
class InterfaceDefineMetaUse implements InterfaceDefineMeta {

    @Override
    public void t() {

    }

    @Override
    public void tt() {

    }

    public static void main(String[] args) {
//        InterfaceDefineMeta.innerInterface t1 = new T1();
        // InterfaceDefineMeta.innerInterface1 t2 = new T2();

        // 静态内部类的作用
        //  T3 t3 = new T3();
//        MyClass myClass = new MyClass();
    }
}


class InterfaceDefineMetaUse1 implements InterfaceDefineMeta.innerInterface {

    @Override
    public void what() {

    }

    public static void main(String[] args) {
    }
}

