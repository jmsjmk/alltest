package com.jiamingku.j2se.refelct.bo;

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
public class Son<T, OO> extends Parent {

//     private String string;
    @Resource
    public String publicString;

    public String getPublicString() {
        return publicString;
    }

    public void setPublicString(String publicString) {
        System.out.println(" =sdfsdfsfsdf-sdfds************** " );
        this.publicString = publicString;
    }

    private String privateString;
    protected String protectedString;
    String defaultString;
    public T t;
    public OO o;

    public Son(T t, OO o) {
        this.t = t;
        this.o = o;
    }

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

    public String getString() {

        return "ddd";
    }

    public void setString(String string) {
        ;
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

    public Son(String publicString, String privateString, String protectedString, String defaultString) {
        this.publicString = publicString;
        this.privateString = privateString;
        this.protectedString = protectedString;
        this.defaultString = defaultString;
    }

    public Son() {
    }

    private Son(String privateString) {
        this.publicString = privateString;
    }

    protected Son(String privateString, String privring) {
        this.protectedString = privateString;
        this.privateString = privring;
    }

    public String getMethod(String m) {
        System.out.println("dddd");
        return 1000 + "d";
    }

    public static void main(String[] args) {
        Method[] methods = Son.class.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m.getName());
        }
    }

    @Override
    public String toString() {
        System.out.println("publicString = " + publicString);
        return null;
    }
}
