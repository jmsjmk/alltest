package com.jiamingku.j2se.genneric;

class SelfBounded<T> {
    T element;

    SelfBounded<T> set(T arg) {
        element = arg;
        return this;
    }

    T get() {
        return element;
    }
}

/**
 * 目的就是将父类泛型参数--替换定义的类型
 */
class A extends SelfBounded<A> {
}

/**
 * 也是没毛病的, 类型都替换成为A
 */
class B extends SelfBounded<A> {
} // Also OK


class C extends SelfBounded<C> {
    C setAndGet(C arg) {
        set(arg);
        return get();
    }
}

class D {
}

// Can't do this:
class E extends SelfBounded<D> {
}


// Compile error: Type parameter D is not within its bound

// Alas, you can do this, so you can't force the idiom:
class F extends SelfBounded {
}

public class SelfBounding {


    public static void main(String[] args) {
        A a = new A();
        a.set(new A());
        a = a.set(new A()).get();
        a = a.get();
        C c = new C();
        c = c.setAndGet(new C());
    }
} ///:~
