package com.jiamingku.j2se.annotation.learn.Inherited;

//@Inheritable
public class InheritableTest extends Base {
    public static void main(String[] args) {
        // 打印TestInheritable类是否具有@Inheritable修饰
        // 因为inheritable是可继承的注解，所以在修饰父类的时候，子类就相当于也被修饰了
        System.out.println(InheritableTest.class.isAnnotationPresent(Inheritable.class));
    }
}

