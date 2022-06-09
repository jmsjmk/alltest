package com.jiamingku.j2se.refelct;

import com.jiamingku.j2se.refelct.bo.Peo;
import com.jiamingku.j2se.refelct.bo.Son;
import com.jiamingku.j2se.refelct.bo.Stu;
import org.junit.Test;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jiamingku on 2020/3/23.
 */
public class GetClass {
    public static final String SPLIT = "---------------------------------------------------------------------------------------";

    /**
     * https://stackoverflow.com/questions/61296911/how-to-use-class-forname-method-in-java-with-primitive-type-int-float-doub?noredirect=1&lq=1
     * <p>
     * https://stackoverflow.com/questions/5032898/how-to-instantiate-class-class-for-a-primitive-type
     * <p>
     * <p>
     * 1.类的全限定名字可以获取一个class对象，但是原生的类型获取不到.\
     * <p>
     * String name = int.class.getName();
     * System.out.println(name);
     * System.out.println("int.class = " + int.class);
     * <p>
     * Class<?> anInt = Class.forName("I"); // class.forName("int");
     * System.out.println("anInt.getSimpleName() = " + anInt.getSimpleName());
     */
    @Test
    public void testOO() {
        try {

            String name1 = int.class.getName();
            System.out.println("name1 = " + name1);
            System.out.println("int.class = " + int.class);


            // Class<?> i = Class.forName("I");       // 异常
            // Class<?> anInt = Class.forName("int"); // 异常
            // System.out.println(anInt);

            System.out.println(SPLIT);
            // ----获取字符串
            int[] ar = {};
            Class t = ar.getClass();
            System.out.println(t);
            // 反射获取数据.获取数组.
            Class<?> aClass = Class.forName("[I"); // [I 就相当于获取了字节数组
            System.out.println(aClass.getName());
            System.out.println(t == aClass);

            System.out.println(SPLIT);

            String[][] string2 = new String[1][];
            String simpleName = string2.getClass().getSimpleName();
            System.out.println(simpleName);

            Class<?> aClass1 = Class.forName("[[Ljava.lang.String;");
            System.out.println(aClass1.getSimpleName());

            System.out.println("(aClass1 = string2.getClass()) = " + (aClass1 == string2.getClass()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取class对象的几种方式
     */
    @Test
    public void getClassObjType() {
        try {
            // 1.
            String path = "com.jiamingku.j2se.refelct.bo.Peo";
            Class c = Class.forName(path);
            System.out.println(c.hashCode());

            // 2.
            Class c1 = Peo.class;
            System.out.println(c1);

            // 3.
            Peo peo = new Peo();
            Class c3 = peo.getClass();
            System.out.println(c3.hashCode());

            // 4.
            ClassLoader classLoader = peo.getClass().getClassLoader();
            Class c4 = classLoader.loadClass(path);
            System.out.println(c4);

            // 5.基本类型？
            Class<Integer> integerClass = int.class;
            System.out.println(integerClass.toString());

            Class<Integer> integerClass1 = Integer.class;
            System.out.println(integerClass1.toString());

            // 6.
            Class<Integer> type = Integer.TYPE;
            System.out.println(integerClass == type);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getInstance() {
        try {
            Class sonClass = Son.class;
            Son s = (Son) sonClass.newInstance();
            System.out.println("s = " + s);

            s = (Son) sonClass.getConstructor(null).newInstance();
            System.out.println(s.toString());

            Constructor constructor = sonClass.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);
            s = (Son) constructor.newInstance("3333");
            System.out.println(s.publicString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 几乎任何类型都有class对象
     */
    @Test
    public void test2() {
        Class<String> cs1 = String.class;//外部类
        Class<Serializable> cls2 = Serializable.class;// 接口
        Class<Integer[]> cls3 = Integer[].class; //数
        System.out.println("cls3 = " + cls3);
        Class<float[][]> cLs4 = float[][].class;//二维数组
        Class<Deprecated> cls5 = Deprecated.class;// 注解
        Class<Thread.State> cl6 = Thread.State.class; // 内部类
        Class<Long> cLs = Long.class;
        Class<Void> cLs8 = void.class;  // void
        Class<Class> cLs9 = Class.class;  // 。。
    }

    @Test
    public void testPackage() {
        try {
            String path = "com.jiamingku.j2se.refelct.bo.Peo";
            Class c = Class.forName(path);
            // 1.
            String packageName = c.getPackage().getName();
            System.out.println("package:   " + packageName);

            // 2.
            String className = c.getName();
            System.out.println("className = " + className);

            String simpleName = c.getSimpleName();
            System.out.println("simpleName = " + simpleName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testSuperClass() {
        try {
            Class cson = Stu.class;
            Class parent = cson.getSuperclass();
            System.out.println("parent = " + parent);

            Class[] interfaces = cson.getInterfaces();
            for (Class c : interfaces) {
                System.out.println(c.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAnnotationClass() {
        try {
            Class cson = Stu.class;
            Annotation[] annotations = cson.getAnnotations();
            System.out.println("annotations.length() = " + annotations.length);
            Arrays.stream(annotations).forEach(System.out::println);

            System.out.println(" = ");
            System.out.println(" = ");
            System.out.println(" = ");

            /**
             * 获取接口信息
             */
            AnnotatedType[] annotatedType = cson.getAnnotatedInterfaces();


            System.out.println("annotatedType.length = " + annotatedType.length);
            for (AnnotatedType annotatedType1 : annotatedType) {
                System.out.println(annotatedType1.getType().getTypeName());
            }
            for (Annotation a : annotations) {
                System.out.println(a.annotationType().getName());
            }

            String s = cson.getCanonicalName();
            System.out.println(s);
            System.out.println(cson);
            System.out.println(cson.getName());


            Class superClass = cson.getSuperclass();
            System.out.println(superClass.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取类型信息:
     */
    @Test
    public void testGenericType() {
        List<String> o = new ArrayList<>();
        TypeVariable<?>[] typeparms = o.getClass().getTypeParameters();
        StringBuilder sb = new StringBuilder();
        if (typeparms.length > 0) {
            boolean first = true;
            sb.append('<');
            for (TypeVariable<?> typeparm : typeparms) {
                if (!first)
                    sb.append(',');
                sb.append(typeparm.getTypeName());
                System.out.println("typeparm.getTypeName() ==" + typeparm.getTypeName() + "   typeparm.getName()" + typeparm.getName());
                Arrays.stream(Arrays.stream(typeparm.getBounds()).toArray()).forEach(System.out::println);
                System.out.println("----");
                first = false;
            }
            sb.append('>');
        }
        System.out.println(sb.toString());
    }
}
