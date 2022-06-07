package com.jiamingku.j2se.genneric;

import com.jiamingku.j2se.genneric.bo.*;
import org.junit.Test;

import java.util.*;

/**
 * 泛型通配符
 * │
 * ├──1.? extends interface
 * │      │
 * │      ├──interface已知,通配符上限,[不可以添加add]
 * │
 * ├──2.T extends interface
 * │      │
 * │      ├──interface已知,类型上限,[可以添加add]
 * │
 * ├──3.? extends T
 * │     │
 * │     ├──T未知,泛型上限,[不可以添加add]
 * │
 * ├──4.? super T
 * │     │
 * │     ├──T未知,泛型下限,[可以添加add]
 * <p>
 * <p/>
 * <p/>
 * 泛型
 *
 * @param <T>
 */
public class WildcardTest<T> {

    /**
     * 协变, 逆变
     */
    public static void main(String[] args) {
        // 1.编译错误
        // 泛型不支持协变
        // List<Object> list = new ArrayList<String>();
        List list1234 = new ArrayList<Object>();

        /**
         * 2.虽然泛型不支持协变的，但是可以通过通配符进行模拟：
         *
         * 注意：? extends Object的含义是：运行Object的子类，也包括Object，作为泛型参数。
         */
        List<? extends Object> list1 = new ArrayList<String>();
        // 虽然泛型不支持协变的，但是可以通过通配符进行模拟：
        List<? extends Object> list2 = new ArrayList<Object>();
        // 菱形写法,编译器推断能力增强可以简写成为下面的写法
        List<? extends Object> list2other = new ArrayList<>();

        /**
         * 但是这么写，只能是引用，不能读取里面的元素。
         *
         * 逆变：在Java中不允许将父类变量赋值给子类变量。泛型自然也不支持逆变。但是在泛型中可以通过通配符进行模拟，如下例子：
         */
        List<? super Integer> list = new ArrayList<Number>();
        List<? super Integer> list3 = new ArrayList<Integer>();
        list3.add(1);
    }


    /**
     * 为什么list<String> 并不是 list<Object> 的子类?(数组是可以的，Integer[] i = new Integer[5]; Number [] n = i;)
     * 安全的角度来说如果list<object> = list<String>  list<Object> = list<Integer>
     * 所以来说 list<Object>最终的类型你不知道是什么,也就缺乏了安全性，所以泛型不支持协变逆变
     * <p>
     * <p/>
     * 为了支持协变，逆变 出现了通配符(***)
     */
    @Test
    public void test0() {
        List<? extends Fruit> flist0 = Arrays.asList(new Apple());
        List<? extends Fruit> flist1 = new ArrayList<Apple>();
        List<Orange> oranges = new ArrayList<>();
        List<? extends Fruit> flist2 = oranges;

        Apple a = (Apple) flist0.get(0); // No warning
        flist0.contains(new Apple()); // Argument is 'Object'
        flist0.indexOf(new Apple()); // Argument is 'Object'
        flist0.contains(new Object());
        // flist0.add(new Orange());
    }

    /**
     * 类型通配符添加,泛型与多态不冲突
     */
    @Test
    public void test1() {
        List<InterfactC> list = new ArrayList<>();
        list.add(new InterfactC1());
        list.add(new InterfactC2());

        System.out.println();
        // list.add(new Object());

        List<InterfactC> list1 = new ArrayList<>();
        List<InterfactC1> list2 = new ArrayList<>();
        /**
         * 虽然泛型不支持协变逆变.但是上面的添加是没问题
         */
        // list1 = list2;
    }
// -----------------------------------------------------------------------------------

    /**
     * 使用的时候一定要使用确切的类型
     */
    public void test2() {
        List<? extends Object> lst = new ArrayList<>(); // This works
        // 使用的时候必须的指定具体的类型
//         List<T extends Object> lst1 = new ArrayList<Object>(); // Compilation fails?，错误很明显啊,方法的实参

        // 1. no add
        List<? extends Number> list = new ArrayList<>();
        // list.add(Integer);

        List<? super Integer> list1 = new ArrayList<Number>();
        list1.add(new Integer("1"));
    }

    public <T extends Number> void get1(List<T> list) {
        T t = null;
        list.add(t);
    }

    /**
     * 可以强制类型转换---前提是你知道他属于那种类型
     * 1)
     * String a = "2333";
     * Integer a1 = (Integer) a;
     * 这种情况你明显转型发生错误,道理很简单么,
     * 2)
     * 所以在进行转型的时候，必须是一种相互有关系的类型才可以进行转型
     * 就拿泛型来说就是类型通配符之类的进行转型.
     */

    @Test
    public void test4() {
        Number num1 = new Integer(0);
        Number[] num2 = new Integer[10];

        List<? extends Number> foo3 = new ArrayList<>();
        Number n = foo3.get(0);

        Object oooo = foo3.get(0);
        List<? super Integer> foo31 = new ArrayList<Integer>();

        Object o = foo31.get(1);

        String oo1 = (String) o;
    }

    /**
     * 类型参数推断:
     * public <T> T getRes(T t) {…}
     * Integer i = getRes(1),
     * Double d = getRes(1.0)
     * 对应的T就是integer，double，在调用泛型方法的时候，会执行类型推断工作.
     * Map<String, String> map = new HashMap<>(); 这个也就是会进行类型推断, 推断的目的就是在必须要的地方插入checkCast指令
     * <p>
     * jdk1.7以前：map<string,sting> map = new hashmap<String,String>; 类型参数写两份所以1.7出现了棱形写法简化写法.
     * jdk1.7的推断特点: 这个泛型方法也是作用于赋值语句时候会进行推断,返回值传递不进行类型推导
     */
    public void test5() {
        /*
         * List<String> ls = Collections.checkedList(new ArrayList<>(), String.class); —1.7 error,编译器推断不出来具体的类型，认为<>是object类型
         * List<String> ls = Collections.checkedList(new ArrayList<>(), String.class); —1.8  可以,编译器能推断出来具体的类型
         * 1.7与1.8类型的推断进行很大的优化。。 类型推导在1.7时代，只会在赋值语句时候进行类型推断，把函数的返回值进行传递是不进行类型推到，但是对于1.8之后就相对于放宽了政策
         */

        // Set<Integer> si = true ? Collections.singleton(23) : Collections.<Integer>emptySet();
        Set<Integer> si = true ? Collections.singleton(23) : Collections.emptySet();

        List<String> ls = Collections.checkedList(new ArrayList<>(), String.class);//  —1.8  可以,编译器能推断出来具体的类型
    }

    @Test
    public void test6() {
        HashMap<String, String> map = new HashMap<>();
        // map接口在get的时候 是不会进行泛型检查的,因为key的类型是object
        map.get(100);
    }

    /**
     * 擦除的例子:
     * <p>
     * ================================泛型擦除的例子
     * https://stackoverflow.com/questions/17751980/does-type-erasure-of-java-generics-cause-full-type-casting
     * https://stackoverflow.com/questions/4343202/difference-between-super-t-and-extends-t-in-java
     */
    @Test
    public void test7() {

    }


    public void test222(List<String>... list) {
        List[] list1 = list;
    }
}
