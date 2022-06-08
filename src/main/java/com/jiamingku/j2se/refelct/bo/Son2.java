package com.jiamingku.j2se.refelct.bo;

/**
 * 构造器:
 * 1)获取构造器的时候,不能获取父类的构造器.
 * 2)其他的方法可以获取继承的属性.
 * <p>
 * 字段值:
 * 1)字段值可以获取到父类,但是构造器获取不到.
 */
public class Son2<T, OO> extends Parent {


    public T t;
    public OO o;

    public Son2(T t, OO o) {
        this.t = t;
        this.o = o;
    }


}
