package com.jiamingku.j2se.innercalss.instance;

/**
 * Created by jiamingku on 2017/7/8.
 */
public class GetInstanceTest {
    public static void main(String[] args) {
        // ===================================非静态内部类
        // 访问权限控制
        T1.Content inner = new T1().new Content();
        // 默认的
        T1.defaultClass inner1 = new T1().new defaultClass();
        // 保护的
        T1.protectedClass inner2 = new T1().new protectedClass();


        // -----------------------------访问权限的控制是修饰符的限制，内部类可以理解为一个类的成员
        T1 t1 = new T1();
        T1.Content content = t1.new Content();

        // =====================================静态内部类
        T1.publicStaticClass publicStaticClass = new T1.publicStaticClass();
        T1.protectedStaticClass protectedClass = new T1.protectedStaticClass();
        T1.defaultStaticClass defaultStaticClass = new T1.defaultStaticClass();
    }
}
