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
            System.out.println("keyType.getName():" + keyType.getName());
            System.out.println("keyType.getGenericDeclaration():" + keyType.getGenericDeclaration());
            String s2 = fk.getType().toString();
            System.out.println("s2 = " + s2);
            System.out.println("-----------------------");

            // 通过实例进行获取::::::-----------------------------------------------------------------------------------------------------------
            // 也就是说你指定了--具体的类型--在获取的时候就----运行时才能
            fk = bean1.getClass().getDeclaredField("bean1");
            /* TypeVariable代表着泛型中的变量，而ParameterizedType则代表整个泛型；*/
            Type key = fk.getGenericType();
            if (key instanceof ParameterizedType) {
                System.out.println(" ======== ");
                ParameterizedType p = (ParameterizedType) key;
                System.out.println(p);
            }
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

            // ----这个给按照绑定的上线-返回Class对象
            String s2 = fk.getType().toString();
            System.out.println("s1 ===== " + s2);

            // ----这个按照
            String s = fk.getGenericType().toString();
            System.out.println("s1 ===== " + s);

            System.out.println("-----------------------");

            // 通过实例进行获取::::::-----------------------------------------------------------------------------------------------------------
            // 也就是说你指定了--具体的类型--在获取的时候就----运行时才能
            fk = bean1.getClass().getDeclaredField("bean1");
            ParameterizedType keyType1 = (ParameterizedType) fk.getGenericType();
            System.out.println("keyType1 = " + keyType1);
            for (TypeVariable<?> typeParameter : keyType.getGenericDeclaration().getTypeParameters()) {
                System.out.println("&&--查看绑定的每一层参数----");
                System.out.println(typeParameter.getName());
                System.out.println(typeParameter.getTypeName());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    /**
     * 在方法内定义的变量--代泛型的--能取出来。但是我不会，spring可以获取到.
     * <p>
     * 就是运行期方法内的 一般都是获取不到的，但是想类， 局部变量 在编译的时候可能留下了一些标记 你可以获取到.
     */
    @Test
    public void base3() {
        try {
            /** 方法内定义的几乎是获取不到的 ---这种是获取不到的*/
            TypeVariableBean<FileInputStream, String> bean = new TypeVariableBean<FileInputStream, String>();

            // -----
            Type genericSuperclass = SonTest.class.getGenericSuperclass();
            System.out.println("genericSuperclass = " + genericSuperclass);
            System.out.println("genericSuperclass.getClass().getSimpleName() = " + genericSuperclass.getClass().getSimpleName());

            // --- 不存在
            Type[] genericInterfaces = SonTest.class.getGenericInterfaces();
            for (Type t : genericInterfaces) {
                System.out.println("t = " + t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

