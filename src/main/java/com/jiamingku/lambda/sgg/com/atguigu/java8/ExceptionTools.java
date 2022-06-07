package com.jiamingku.lambda.sgg.com.atguigu.java8;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class ExceptionTools {

    public static Supplier<Exception> t(Integer code, String value, BiFunction<Integer, String, Exception> biFunction) {
        return () -> biFunction.apply(code, value);

    }
}
