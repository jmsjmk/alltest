package com.jiamingku.j2se.refelct.type;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;

public class WildcardTypeBean {
    private List<? extends Number> a;  // a没有下界,
    //  没有指定的话，上边界默认是 Object ,下边界是  String
    private List<? super String> b;

    private List<String> c;

    private Class<?> aClass;

    @Test
    public void fieldTest() {
        Field[] fields = WildcardTypeBean.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            Type type = field.getGenericType();
            String nameString = field.getName();
            PrintUtils.print("处理属性:::: field.getName() " + field.getName() + " 类型:" + field.getGenericType().getClass().getSimpleName());

            ParameterizedType parameterizedType = (ParameterizedType) type;
            System.out.println("获取参数数组:::");
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            for (Type t: actualTypeArguments) {
                System.out.println("t.getTypeName()   = " + t.getTypeName() + "      t.Class:" + t.getClass().getSimpleName());
                if (t instanceof WildcardType) {
                    PrintUtils.printTypeArr((WildcardType)t);
                }
            }
        }
    }

    public static void main(String[] args) {

        try {

            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//            private List<? extends Number> a;  // a没有下界,
//            //  没有指定的话，上边界默认是 Object ,下边界是  String
//            private List<? super String> b;
            Field fieldA = WildcardTypeBean.class.getDeclaredField("a");
            Field fieldB = WildcardTypeBean.class.getDeclaredField("b");
            // 先拿到范型类型
            PrintUtils.print(fieldA.getGenericType() instanceof ParameterizedType); // true
            PrintUtils.print(fieldB.getGenericType() instanceof ParameterizedType); // true
            ParameterizedType pTypeA = (ParameterizedType) fieldA.getGenericType();
            ParameterizedType pTypeB = (ParameterizedType) fieldB.getGenericType();
            // 再从范型里拿到通配符类型
            PrintUtils.print(pTypeA.getActualTypeArguments()[0] instanceof WildcardType); // true
            PrintUtils.print(pTypeB.getActualTypeArguments()[0] instanceof WildcardType); // true
            WildcardType wTypeA = (WildcardType) pTypeA.getActualTypeArguments()[0];
            WildcardType wTypeB = (WildcardType) pTypeB.getActualTypeArguments()[0];
            // 方法测试
            System.out.println(wTypeA.getUpperBounds()[0]);
            System.out.println(wTypeB.getLowerBounds()[0]);
            // 看看通配符类型到底是什么, 打印结果为: ? extends java.lang.Number
            System.out.println(wTypeA);
        } catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }
    }
}