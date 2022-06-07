package com.jiamingku.j2se.refelct.type;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ManyInterfaceTest extends AA implements E<String>, D {

    Integer integer;

    public Integer isInteger() {
        return 10;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    //    public boolean isTradeType() {
//        return true;
//    }
//
//    private boolean getTradeType() {
//        return true;
//    }


    private boolean testb;
    private Boolean aBoolean;

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public boolean isTestb() {
        return testb;
    }

    public boolean getTestb() {
        return true;
    }

    public void setTestb(boolean testb) {
        this.testb = testb;
    }

    @Override
    public void c() {

    }

    @Override
    public void d() {

    }

    @Override
    public String e() {
        return null;
    }

    static String getSignature(Method method) {
        StringBuilder sb = new StringBuilder();
        // 返回类型
        Class<?> returnType = method.getReturnType();
        if (returnType != null) {
            sb.append(returnType.getName()).append('#');
        }
        // 方法名
        sb.append(method.getName());
        // 方法参数
        Class<?>[] parameters = method.getParameterTypes();
        for (int i = 0; i < parameters.length; i++) {
            if (i == 0) {
                sb.append(':');
            } else {
                sb.append(',');
            }
            sb.append(parameters[i].getName());
        }
        return sb.toString();
    }


    public List<String> getList() {

        return null;
    }

    public static void main(String[] args) throws Exception {
        Method m = ManyInterfaceTest.class.getMethod("getList");

        Class c = m.getReturnType();
        System.out.println(c);

        System.out.println(m.getGenericReturnType());
        System.out.println(m.getReturnType());
        ParameterizedType t = (ParameterizedType) m.getGenericReturnType();
        System.out.println(t.getRawType());

        for (Type actualTypeArgument : t.getActualTypeArguments()) {

            System.out.println(actualTypeArgument);
        }


    }
}

class AA {
    Integer tradeType;

    private Integer getTradeType() {
        return tradeType;
    }

    private boolean isTradeType() {

        return true;
    }

}

interface A {
    boolean isTestb();

    void a();
}

interface B {
    void b();
}

interface C {
    void c();
}

interface D extends C {
    void d();
}

interface E<T> {
    boolean isTestb();

    T e();
}