package com.jiamingku.j2se.refelct.bo;

import com.google.common.base.Objects;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 构造器:
 * 1)获取构造器的时候,不能获取父类的构造器.
 * 2)其他的方法可以获取继承的属性.
 * <p>
 * 字段值:
 * 1)字段值可以获取到父类,但是构造器获取不到.
 */
public class Son1<T, OO> extends Parent {

    @Resource
    public String publicString;
    private String privateString;
    protected String protectedString;
    String defaultString;
    public T t;
    public OO o;

    /* 方法-----------*/
    public static void staticMethod() {
    }

    public void publicMethod() {
        System.out.println("publicMethod..");
    }

    private void privateMethod() {
    }

    protected void protectedMethod() {
    }

    void defaultMethod() {
    }

    /* 方法-----------*/
    public void sonA() {
    }

    public void test() {
        System.out.println("test");
    }

    private void test1() {
        System.out.println("test");
    }

    public String getString(int a, byte b, String s) {

        return "ddd";
    }

    @Th(a = "333")
    public void setIds(@Th(a = "1") List<String> stringList) {

    }

    public <T> T testTT(T t) {
        System.out.println("dddddd");
        return null;
    }

    public <Tsy> void setIdss(Tsy t) {
        System.out.println(t);
    }

    protected void sonProtectedMethod() {
    }

    public Son1(String publicString, String privateString, String protectedString, String defaultString) {
        this.publicString = publicString;
        this.privateString = privateString;
        this.protectedString = protectedString;
        this.defaultString = defaultString;
    }

//    public Son1() {
//    }

    private Son1(String privateString) {
        this.publicString = privateString;
    }

    public Son1(String privateString, String privring) {
        this.protectedString = privateString;
        this.privateString = privring;
    }

    public String getMethod(String m) {
        System.out.println("dddd");
        return 1000 + "d";
    }

    public static void main(String[] args) {
        Method[] methods = Son1.class.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m.getName());
        }
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("privateString", privateString)
                .toString();
    }
}
