package com.jiamingku.j2se.casttype;

import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jiamingku on 2019/1/16.
 */
public class SonCastTypeTest extends Super implements A, B {
    private final int bb;
    private final Integer integer;

    {
        integer = 10;
        bb = 10;
    }

    private List<String> list = new ArrayList<>();


    /**
     * instanceof 关键字就是检查对象与类型的关系,父子类型的关系.
     *
     * @param args
     */
    public static void main(String[] args) {
        Object as = "ddddd";
        if (as instanceof Object) {
            System.out.println("is Object");
        }

        if (as instanceof String) {
            System.out.println("is String");
        }

        System.out.println(" =========================================================== ");
        a = 1000;
        B b = new SonCastTypeTest();
        if (b instanceof B) {
            System.out.println("is B");
        }
        if (b instanceof A) {
            System.out.println("is A");
        } else {
            System.out.println(" is not A ");
        }
        if (b instanceof Super) {
            System.out.println("is Supser");
        }
        a(b);

        /**
         * 关于造型的理解
         * https://stackoverflow.com/questions/30374083/whats-the-meaning-of-the-character-in-the-returned-value
         *
         * 一个父类，多个接口类型，这么写就说明可以使用多个接口引用
         */
        Super b1 = (Super & B & Serializable & Map) b;
        Map b1Map = (Super & B & Serializable & Map) b;
        B b1Super = (Super & B & Serializable & Map) b;
        Serializable b1Serializable = (Super & B & Serializable & Map) b;

        System.out.println("b1 = " + b1);
        A b1a = (Super & A & B) b;
        System.out.println("b1a = " + b1a);
        Super super1 = (Super & A & B) b;
        System.out.println("super1 = " + super1);
    }


    public SonCastTypeTest() {
    }


    //-----------------------不纠结--- instanceOf 的另外一种写法-------------------------
    @Test
    public void test2() {
        Super b = new Super();
        if (SonCastTypeTest.class.isInstance(b)) {
            System.out.println("b = yese");
        }

        if (Object.class.isInstance(b)) {
            System.out.println(" ====== ");
        }
    }

    /**
     * unchecked这个警告是编译器编译发出来的,
     * <p>
     * 在使用泛型的时候，你使用了泛型，但是编译器推断不出来时候，就发出警告
     */
    @Test
    public void test1() {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(1);
        integers.add(1);
        integers.add(1);
        integers.add(1);
        Object o = integers;
        /**
         * 泛型的造型，只是编译时候的一种写法限制下
         */
        // List<Double> doubles = (List<Integer>) o; 感觉= String s = (Integer) oInteger;  有点类似
        Object oInteger = 1;

        // 泛型的造型是有用的，这样可以使用编译器检查
        List<Double> doubles = (List<Double>) o;
        // 编译器给你检查。。。
        // doubles.add("100");

        // 这样的造型 就不会有类型检查了。
        List oo = (List) o;
        oo.add("ddd");

        List<Integer> ii = (List<Integer>) o;
        List l = (List) o;
        System.out.println("l = " + l);
    }

    public static void a(Object o) {
        System.out.println("=================================================");
        SonCastTypeTest b = (SonCastTypeTest) o;
        if (b instanceof B) {
            System.out.println("is B");
        }
        if (b instanceof A) {
            System.out.println("is A");
        }
        if (b instanceof Super) {
            System.out.println("is Supser");
        }
        System.out.println("son = " + b);
    }
}
