package com.jiamingku.j2se.refelct;

import org.junit.Test;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组也是个类:
 * 1.在直接打印class的时候显示:class [Ljava.lang.Class;
 * <p/>
 * 2.直接获取名字,时候打印Class[]
 */
public class ArrayTest {

    @Test
    public void test111() {
        String[] strs = new String[3];
        System.out.println(strs.getClass().getSimpleName());
        System.out.println(strs.getClass());
    }

    public String string = "100";

    public static void main(String[] args) {
    }

    /**
     * 说明:Integer.TYPE = int.class;
     */
    @Test
    public void test0() {
        Class integerClass = int.class;
        System.out.println("integerClass = " + integerClass);
        Class iii = Integer.TYPE;
        System.out.println("iii = " + iii);
        System.out.println(int.class);
        System.out.println(Integer.TYPE);
        System.out.println(Integer.class);

    }

    /**
     * 原始类型与包装类型
     * <p>
     * ****** --- 只有在数组时候才会使用描述符号对于简单的类型
     */
    @Test
    public void test1() {
        Integer[] integers = new Integer[10];
        int[] ints = new int[10];
        printCompare("Integer[], int[] 比较", integers, ints);

        Long[] longs = new Long[2];
        long[] longs1 = new long[3];
        printCompare("Long[], long[] 比较", longs, longs1);

        Character[] characters = {'1', '2'};
        char[] chars = {'1', '2'};
        printCompare("Character[], char[] 比较", characters, chars);

        Boolean[] booleens = {true, false, false};
        boolean[] bs = {true, false, false};
        printCompare("booleens[], bs[] 比较", booleens, bs);
    }

    @Test
    public void test2() {
        try {
            System.out.println(int.class);

            // ----整型数组可以通过这个方式进行获取.
            Class c1 = Class.forName("[I");
            System.out.println(c1);
            int[] a = {1};
            System.out.println(a.getClass());

            int[][] aa = (int[][]) Array.newInstance(c1, 2);
            System.out.println(aa.length + " class:" + aa.getClass() + "      classSimpleName:" + aa.getClass().getSimpleName());
            Arrays.stream(aa).forEach(System.out::println);
            // Class c2 = Class.forName("int");
            int[] aaaaa = (int[]) Array.newInstance(int.class, 10);
            System.out.println(aaaaa.length + " class:" + aaaaa.getClass() + "       simpleName:" + aaaaa.getClass().getSimpleName());
            Arrays.stream(aaaaa).forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printCompare(String tips, Object o1, Object o2) {
        System.out.println(tips + "----开始----");
        System.out.println(o1.getClass());
        System.out.println(o2.getClass());
        System.out.println("--");
        System.out.println(o1.getClass().getName());
        System.out.println(o2.getClass().getName());
        System.out.println("--");
        System.out.println(o1.getClass().getSimpleName());
        System.out.println(o2.getClass().getSimpleName());
        System.out.println(tips + "----结束----");
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
    }

    @Test
    public void printCompareAll() {
        try {
            String s = "test".intern();
            Method m = ArrayTest.class.getDeclaredMethod("test", null);
            boolean b = m.getName() == s;
            System.out.println(b);
            String s1 = new String("test");
            if (s1 == s) {
                System.out.println("无奈!..");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 关于泛型的测试.---------------------------------------------------------------------------------------------------------
    public List<String> a = new ArrayList<>();

    @Test
    public void testdddd() {
        try {
            Type type = ArrayTest.class.getField("a").getGenericType();
            if (type instanceof ParameterizedType) {
                System.out.println(" IS ParameterizedType  = " + type);
            } else {
                System.out.println(" IS not ParameterizedType  = " + type);
            }

            Class<?> a = ArrayTest.class.getField("a").getType();


            Type string = ArrayTest.class.getField("string").getGenericType();

            if (string instanceof Class) {
                System.out.println("is class = " + string);
            } else {
                System.out.println("is not class");
            }


            typeToClass(type);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private Class<?> typeToClass(Type src) {
        Class<?> result = null;
        // 普通类型，直接使用类
        if (src instanceof Class) {
            result = (Class<?>) src;
            // 泛型类型，使用泛型
        } else if (src instanceof ParameterizedType) {
            result = (Class<?>) ((ParameterizedType) src).getRawType();
            // 泛型数组，获得具体类
        } else if (src instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) src).getGenericComponentType();
            if (componentType instanceof Class) { // 普通类型
                result = Array.newInstance((Class<?>) componentType, 0).getClass();
            } else {
                Class<?> componentClass = typeToClass(componentType); // 递归该方法，返回类
                result = Array.newInstance(componentClass, 0).getClass();
            }
        }
        // 都不符合，使用 Object 类
        if (result == null) {
            result = Object.class;
        }
        return result;
    }
}
