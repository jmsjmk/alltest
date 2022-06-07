package com.jiamingku.j2se.refelct.type;

import org.junit.Test;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

/**
 * 1.TypeVariable代表着泛型中的变量，而ParameterizedType则代表整个泛型;
 * <p/>
 * <p>
 * 2.
 */
public class TypeVariableBean<K extends InputStream & Closeable, V> {
    // K 的上边界是 InputStream
    K key;
    //    Map<K,V> key;
    // 没有指定的话 ，V 的 上边界 属于 Object
    V value;
    // 不属于 TypeTypeVariable
    V[] values;
    String str;
    List<K> kList;

    /**
     * 泛型类的信息, 通过class是获取不到反省的信息的。只能通过类型变量去获取.
     */
    @Test
    public void test1() {
        Type genericSuperclass = TypeVariableBean.class.getGenericSuperclass();
        System.out.println("genericSuperclass = " + genericSuperclass);
    }

    /**
     * 编译时候确定类型
     */
    static TypeVariableBean<FileInputStream, String> bean1 = new TypeVariableBean<FileInputStream, String>();


    @Test
    public void base1() {
        try {
            // 通过类进行获取::::-----------------------------------------------------------------------------------------------------------
            Field fk = TypeVariableBean.class.getDeclaredField("key");
            /* TypeVariable代表着泛型中的变量，而ParameterizedType则代表整个泛型；*/
            TypeVariable keyType = (TypeVariable) fk.getGenericType();
            System.out.println("fk.getGenericType.getName():" + keyType.getName());
            System.out.println("fk.getGenericType.getGenericDeclaration():" + keyType.getGenericDeclaration());
            String s2 = fk.getType().toString();
            System.out.println("s2 = " + s2);
            System.out.println("-----------------------");

            // 通过实例进行获取::::::-----------------------------------------------------------------------------------------------------------
            // 也就是说你指定了--具体的类型--在获取的时候就----运行时才能
            fk = bean1.getClass().getDeclaredField("key");
            /* TypeVariable代表着泛型中的变量，而ParameterizedType则代表整个泛型；*/
            TypeVariable keyType1 = (TypeVariable) fk.getGenericType();
            System.out.println("fk.getGenericType.getName():" + keyType1.getName() + "  ");
            System.out.println("fk.getGenericType.getGenericDeclaration():" + keyType1.getGenericDeclaration());

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    /**
     * 通过方法内的定义是获取不到具体的类型信息.
     */
    @Test
    public void base() {
        try {
            /** 运行时确定类型*/
            TypeVariableBean<FileInputStream, String> bean = new TypeVariableBean<FileInputStream, String>();

            // 通过类进行获取::::-----------------------------------------------------------------------------------------------------------
            Field fk = TypeVariableBean.class.getDeclaredField("key");
            /* TypeVariable代表着泛型中的变量，而ParameterizedType则代表整个泛型；*/
            TypeVariable keyType = (TypeVariable) fk.getGenericType();
            System.out.println("fk.getGenericType.getName():" + keyType.getName());
            System.out.println("fk.getGenericType.getGenericDeclaration():" + keyType.getGenericDeclaration());
            String s2 = fk.getType().toString();
            System.out.println("s2 = " + s2);
            System.out.println("-----------------------");

            // 通过实例进行获取::::::-----------------------------------------------------------------------------------------------------------
            // 也就是说你指定了--具体的类型--在获取的时候就----运行时才能
            fk = bean1.getClass().getDeclaredField("bean1");
            String s = fk.getGenericType().toString();
            System.out.println("ddd-----" + s);
            ParameterizedType keyType1 = (ParameterizedType) fk.getGenericType();

            String s1 = keyType.getGenericDeclaration().toString();
            System.out.println(s1);
            for (TypeVariable<?> typeParameter : keyType.getGenericDeclaration().getTypeParameters()) {
                System.out.println("&&");
                System.out.println(typeParameter.getName());
                System.out.println(typeParameter.getTypeName());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


}

