package com.jiamingku.j2se.genneric;

import java.lang.reflect.Array;

/**
 * unchecked警告-----会发生在什么位置?简单的来说就是使用泛型的地方你没有使用泛型就报错,java编译器暴露出来的
 * // Unchecked call to set(T) as a member of the raw type Holder 这个编译器警告如何去理解？
 * 1.使用泛型类的地方编译器都会检查,如果编码时候没有指定泛型就会发出对应的警告
 * set(T) 作为原生holder的一个成l员被调用，因为holder你没有指定任何参数类型，所以没办法检查：所以报警
 * com.jiamingku.j2se.genneric.Wildcards#rawArgs —代码的例子
 * <p>
 * <p>
 * 2.第二种情况,转型的时候为什么要带着泛型,或者目的是什么?
 * 转型的目的就是可以拿到泛型数据,为了后面的类型推断准备
 * 非泛型的转型是不会报unchecked警告的，但是如果你转型的东西包含了泛型，那就会爆出来
 * List list = new ArrayList<>();
 * List<Integer> list1 = (List<Integer>)list;
 *
 * /**
 *  * 在什么地方会出现uncheked警告?
 *  * 简答的来说就是,泛型的地方你没有传递泛型,那就发生unchecked就例如下面的
 *  * 这个方法 发出unchecked异常
 *  * <p>
 *  * Created by jiamingku on 2019/2/28.
 *
 */
class Holder<T> {
    T t;

    T get() {
        return t;
    }

    void set(T t) {
        this.t = t;
    }

}

public class UncheckedTest {
    @SuppressWarnings("unchecked")
    private static <T> T getT(T t) {
        System.out.println("t = " + t);
        Object o = new Object();
        return (T) o;
    }

    /**
     * 转型例子
     */
    public void test1() {
        Object o = "ssd";
        String a1 = (String) o; //是不会发出unchecked警告的， 所以说unchecked只有在使用泛型的时候会发出来
        String[] s = (String[]) Array.newInstance(String.class, 10);  //这个也不会抛出unchecked警告
    }

    // Raw argument:
    static void rawArgs(Holder holder, Holder<String> h1, Object arg) {
        h1.set("d");
        // Warning:  Unchecked call to set(T) as a member of the raw type Holder
        holder.set(arg);
        holder.set(new Object()); // Same warning

        // Can't do this; don't have any 'T':
        // T t = holder.get();

        h1.set("333");

        // OK, but type information has been lost:
        Object obj = holder.get();
        String sss = h1.get();
    }

    static void rawArgs(Holder holder, Object arg) {
        holder.set(arg); // Warning:  Unchecked call to set(T) as a member of the raw type Holder
        // holder.set(new Wildcards()); // Same warning

        // Can't do this; don't have any 'T':
        // T t = holder.get();

        // OK, but type information has been lost:
        Object obj = holder.get();
    }

    // Similar to rawArgs(), but errors instead of warnings:
    static void unboundedArg(Holder<?> holder, Object arg) {
//		 holder.set(arg); // Error:
        //   set(capture of ?) in Holder<capture of ?>
        //   cannot be applied to (Object)
        // holder.set(new Wildcards()); // Same error

        // Can't do this; don't have any 'T':
        // T t = holder.get();

        // OK, but type information has been lost:
        Object obj = holder.get();
    }

    static <T> T exact1(Holder<T> holder) {
        T t = holder.get();
        return t;
    }

    static <T> T exact2(Holder<T> holder, T arg) {
        holder.set(arg);
        T t = holder.get();
        return t;
    }

    static <T> T wildSubtype(Holder<? extends T> holder, T arg) {
        // holder.set(arg); // Error:
        //   set(capture of ? extends T) in
        //   Holder<capture of ? extends T>
        //   cannot be applied to (T)
        T t = holder.get();
        return t;
    }

    static <T> void wildSupertype(Holder<? super T> holder, T arg) {
        holder.set(arg);
//		 T t = holder.get();  // Error:
        //   Incompatible types: found Object, required T

        // OK, but type information has been lost:
        Object obj = holder.get();
    }


    public static void main(String[] args) {

        Holder raw = new Holder<Long>();
        // Or:
        raw = new Holder();
        Holder<Long> qualified = new Holder<Long>();
        Holder<?> unbounded = new Holder<Long>();
        Holder<? extends Long> bounded = new Holder<Long>();
        Long lng = 1L;

        qualified = raw;

        rawArgs(raw, lng);
        rawArgs(qualified, lng);
        rawArgs(unbounded, lng);
//		unbounded.set("dd");
        rawArgs(bounded, lng);
//		rawArgs(qualified, "String");

        unboundedArg(raw, lng);
        unboundedArg(qualified, lng);
        unboundedArg(unbounded, lng);
        unboundedArg(bounded, lng);

        // Object r1 = exact1(raw); // Warnings:
        //   Unchecked conversion from Holder to Holder<T>
        //   Unchecked method invocation: exact1(Holder<T>)
        //   is applied to (Holder)
        Long r2 = exact1(qualified);
        Object r3 = exact1(unbounded); // Must return Object
        Long r4 = exact1(bounded);

        // Long r5 = exact2(raw, lng); // Warnings:
        //   Unchecked conversion from Holder to Holder<Long>
        //   Unchecked method invocation: exact2(Holder<T>,T)
        //   is applied to (Holder,Long)
        Long r6 = exact2(qualified, lng);
        // Long r7 = exact2(unbounded, lng); // Error:
        //   exact2(Holder<T>,T) cannot be applied to
        //   (Holder<capture of ?>,Long)
        // Long r8 = exact2(bounded, lng); // Error:
        //   exact2(Holder<T>,T) cannot be applied
        //   to (Holder<capture of ? extends Long>,Long)

        // Long r9 = wildSubtype(raw, lng); // Warnings:
        //   Unchecked conversion from Holder
        //   to Holder<? extends Long>
        //   Unchecked method invocation:
        //   wildSubtype(Holder<? extends T>,T) is
        //   applied to (Holder,Long)
        Long r10 = wildSubtype(qualified, lng);
        // OK, but can only return Object:
        Object r11 = wildSubtype(unbounded, lng);
        Long r12 = wildSubtype(bounded, lng);

        // wildSupertype(raw, lng); // Warnings:
        //   Unchecked conversion from Holder
        //   to Holder<? super Long>
        //   Unchecked method invocation:
        //   wildSupertype(Holder<? super T>,T)
        //   is applied to (Holder,Long)
        wildSupertype(qualified, lng);
        // wildSupertype(unbounded, lng); // Error:
        //   wildSupertype(Holder<? super T>,T) cannot be
        //   applied to (Holder<capture of ?>,Long)
        // wildSupertype(bounded, lng); // Error:
        //   wildSupertype(Holder<? super T>,T) cannot be
        //  applied to (Holder<capture of ? extends Long>,Long)
    }
} ///:~
