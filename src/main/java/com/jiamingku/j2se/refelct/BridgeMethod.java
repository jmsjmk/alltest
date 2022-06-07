package com.jiamingku.j2se.refelct;

import java.lang.reflect.Method;

/**
 * https://www.zhihu.com/question/54895701/answer/141623158
 */
public class BridgeMethod {

    public static void main(String[] args) throws Exception {
        AClass obj = new AClass();
        Method func = AClass.class.getMethod("func", String.class);
        func.invoke(obj, "AAA");
        System.out.println(func.isBridge());
        func = AClass.class.getMethod("func", Object.class);
        func.invoke(obj, "BBB");
        System.out.println(func.isBridge());

    }

}


interface AInterface<T> {
    void func(T t);
}

class AClass implements AInterface<String> {
    @Override
    public void func(String s) {
        System.out.println(s);
    }
}


