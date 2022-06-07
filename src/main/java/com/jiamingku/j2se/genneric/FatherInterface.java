package com.jiamingku.j2se.genneric;

/**
 * Created by jiamingku on 2018/6/21.
 */
public interface FatherInterface<T, Q> {

    T get();

    Q v();
}

/**
 * 子接口可以不写任何范型信息
 */
interface Son extends FatherInterface {

    //---这个只是满足重写的标记就行了
    @Override
    String get();
}

/**
 * 父接口可以不写任何范型信息(那父类就相当于Object)
 */
interface Son0<Sk, Tk> extends FatherInterface {
    @Override
    Sk get();

    @Override
    Tk v();
}

/** 这种就相当于泛型类自己定义了一套新的泛型类型.*/
interface Son1<S, Y> extends FatherInterface<String, String> {
    //        S get();
    String get();

    S get(int i);
}


interface Son2 extends FatherInterface<String, String> {
    String get();
}

/**
 * 同样也可以写
 *
 * @param <K>
 * @param <Y>
 */
interface Son3<K, Y> extends FatherInterface<K, Y> {
    K get();
}

