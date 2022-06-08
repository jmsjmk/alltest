package com.jiamingku.j2se.refelct;

import org.junit.Test;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * 1.Boolean.class, boolean.class 不相等
 * <p>
 * Integer.TYPE == int.class
 * <p>
 * {@link ArrayTest#test1()} --- 只有出现数组的时候反射打印的信息才包含描述符信息.
 * <p>
 * java 的原生类型 也有class类型信息, 想想为什存在?
 * 通过反射要获取对应的类型信息,类型里面定义的信息可能是简单类型啊, 所示简单类型也有一套类型信息
 */
public class PrimitiveTest {
    // ---com.jiamingku.j2se.refelct.bo.SimpleType
    private static final Map<Class<?>, Class<?>> primitiveWrapperTypeMap = new IdentityHashMap<Class<?>, Class<?>>(8);
    /**
     * Map with common "java.lang" class name as key and corresponding Class as value.
     * Primarily for efficient deserialization of remote invocations.
     */
    private static final Map<String, Class<?>> commonClassCache = new HashMap<String, Class<?>>(32);

    /**
     * java.lang.Character---> char
     * java.lang.Boolean---> boolean
     * java.lang.Short---> short
     * java.lang.Double---> double
     * java.lang.Integer---> int
     * java.lang.Float---> float
     * java.lang.Byte---> byte
     * java.lang.Long---> long
     */
    static {
        primitiveWrapperTypeMap.put(Boolean.class, boolean.class);
        primitiveWrapperTypeMap.put(Byte.class, byte.class);
        primitiveWrapperTypeMap.put(Character.class, char.class);
        primitiveWrapperTypeMap.put(Double.class, double.class);
        primitiveWrapperTypeMap.put(Float.class, float.class);
        primitiveWrapperTypeMap.put(Integer.class, int.class);
        primitiveWrapperTypeMap.put(Long.class, long.class);
        primitiveWrapperTypeMap.put(Short.class, short.class);
    }

    @Test
    public void test0() {
        Class<Boolean> booleanClass = Boolean.class;
        System.out.println(booleanClass.getSimpleName());
        Class<Boolean> booleanClass1 = boolean.class;
        System.out.println(booleanClass1.getSimpleName());

        boolean b = Integer.TYPE == int.class;
        System.out.println(b);
        boolean c = int.class == Integer.class;
        System.out.println("c:" + c);
        System.out.println(int.class.getName());
        System.out.println(Integer.TYPE.getName());
        System.out.println(Integer.class.getName());
        System.out.println(Integer.class.getSimpleName());
    }

    @Test
    public void test2() {
        for (Map.Entry<Class<?>, Class<?>> entry : primitiveWrapperTypeMap.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            System.out.println(key + "---> " + value);
            System.out.println("\t\t\t--> key:" + entry.getKey().getSimpleName());
            System.out.println("\t\t\t--> val:" + entry.getValue().getSimpleName());
        }
    }


    public static void main(String[] args) {
        System.out.println(" =====================简单类型如下.....");
        Class c = int.class;
        
        java.lang.reflect.Type t = int.class;

        System.out.println("c = " + c);
        System.out.println("c.getName() = " + c.getName());
        System.out.println(c.getSimpleName());
        System.out.println(c.getClassLoader());
        /**
         * 包装类型就是引用类型。所以路径很全
         */
        System.out.println(" ===============包装类型如下.....");
        c = Integer.class;
        System.out.println("c = " + c);
        System.out.println("c.getName() = " + c.getName());
        System.out.println(c.getSimpleName());
        System.out.println(c.getClassLoader());

        System.out.println(" ==================另外一种写法... Integer.TYPE == int.class");
        c = Integer.TYPE;
        System.out.println("c = " + c);
        System.out.println("c.getName() = " + c.getName());
        System.out.println(c.getSimpleName());
        System.out.println(Integer.TYPE.getClassLoader());
    }
}
