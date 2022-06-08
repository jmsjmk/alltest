package com.jiamingku.j2se.refelct;

import com.jiamingku.j2se.refelct.bo.Son;
import com.jiamingku.j2se.refelct.property.PropertyDescriptorTest;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * 字段可以获取的信息如下
 * 1)字段所在类的信息(访问修饰符, 类型, 名称, 注解)
 * 2)变量所属类的信息(getDeclaringClass)
 * 3)设置变量的属性值得-哪怕变量没有get/set方法也是可以的.
 * 4)通过名字获取对应的对应字段信息 -- getField("t") --泛型等信息
 * 5)可以获取父类的属性，
 * <p>
 * PropertyDescriptor:::https://blog.csdn.net/weixin_42069143/article/details/82119724
 * {@link PropertyDescriptorTest}  ---代码.
 * <p>
 * java 的原生类型 也有class类型信息,想想为什存在?
 * 通过反射要获取对应的类型信息,类型里面定义的信息可能是简单类型啊, 所示简单类型也有一套类型信息
 */
public class FieldTest {
    private String x = "100";
    private Object o = new Integer(112);

    @Test
    public void test21() {
        Class c = Son.class;
        Field[] fileds = c.getDeclaredFields();
        for (Field f : fileds) {
            String name = f.getName();
            Class<?> type = f.getType();
            System.out.println("f.getName() :" + name + "  f.getType():=" + type);

            Type genericType = f.getGenericType();
            System.out.println("f.getGenericType() = " + genericType     + "    \t\t\t" +  genericType.getClass().getSimpleName());

            System.out.println("-----------------");
        }
    }

    /**
     * 1.getFields获取本类或者父类的属性-访问权限可以的(也就是反问权限可以的)
     * 2.getDeclaredFields获取本类的所有属性
     */
    @Test
    public void test2() {
        Class<Son> sonClass = Son.class;
        Field[] fields = sonClass.getFields();
        System.out.println("[getFields,方法执行开始.... 获取字段长度:] " + fields.length);
        for (Field f : fields) {
            String typeName = f.getType().getName();
            String filedName = f.getName();
            int a = f.getModifiers();
            System.out.println("typeName = " + typeName + " -------" + "filedName:" + filedName + "  访问修饰符 modefiter:" + a);

        }
        System.out.println(" 上面获取泛型信息是Object类型的. \n\n");

        // --------------------泛型相关-----------------------------------------------------------------------------------
        /**
         * 通过名字获取字段::::
         */
        try {
            Type t = sonClass.getField("t").getGenericType();
            System.out.println(t.getTypeName());

            Type t1 = sonClass.getField("o").getGenericType();
            System.out.println(t1.getTypeName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        // -------------本类中应该有的属性
        Field[] fields1 = sonClass.getDeclaredFields();
        System.out.println("");
        System.out.println("[getDeclaredFields,方法执行开始.... 获取字段长度:]" + fields1.length);
        for (Field f : fields1) {
            String typeName = f.getType().getName();
            String filedName = f.getName();
            int a = f.getModifiers();
            System.out.println("typeName = " + typeName + " -------" + "filedName:" + filedName + " Modifiers:" + a);
            Annotation[] annotations = f.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println("----注解信息:---" + annotation);
            }

            Class ccc = f.getDeclaringClass();
            System.out.println("-----所在类的信息-----" + ccc);
            System.out.println("==");
        }
    }

    public static void main(String[] args) {
    }
}
