package com.jiamingku.j2se.refelct;

import com.jiamingku.j2se.refelct.bo.Son;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;


public class ConstructorTest {
    /**
     * 构造器: 构造器是没有继承的
     * <p>
     * class.newInstance也是调用的构造器去进行初始化的。
     */
    @Test
    public void testInstance1() {
        try {
            Class<Son> sonClass = Son.class;
            /**
             *  不传递参数--就是获取无参数构造器
             */
            Constructor<Son> constructor = sonClass.getConstructor();
            int parameterCount = constructor.getParameterCount();
            System.out.println("constructor.getParameterCount():" + parameterCount);

            /**
             * 获取所有的构造器--不限制访问修饰符
             */
            Constructor<?>[] declaredConstructors = sonClass.getDeclaredConstructors();
            System.out.println("declaredConstructors = " + declaredConstructors.length);

            /**
             * 获取构造器--public
             */
            Constructor<?>[] constructors = sonClass.getConstructors();
            System.out.println("constructors = " + constructors.length);
            Object o = constructor.newInstance();
            System.out.println(o);

            /**
             * class.newInstance==sonClass.getConstructor().newInstance()--点进去方法就能看出来
             */
            sonClass.newInstance();
            sonClass.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 构造器:的执行不需要指定被调用对象,但是普通方法调用时候需要指定被调用对象,里面有个参数绑定的问题
     */
    @Test
    public void testInstance() {
        try {
            Class<Son> sonClass = Son.class;
            Son son = sonClass.newInstance();
            Constructor constructor = sonClass.getConstructor(null);
            System.out.println(constructor.getName() + "    ======   " + constructor.getTypeParameters().length);

            Object o1 = constructor.newInstance();
            System.out.println(o1.getClass());
            Constructor<Son> stringClass = sonClass.getDeclaredConstructor(String.class);
            stringClass.setAccessible(true);
            Son s1 = stringClass.newInstance("1100");
            System.out.println(s1);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取构造器
     */
    @Test
    public void testGetConstructors() {
        Class sonClass = Son.class;

        Constructor[] cons = sonClass.getConstructors();

        for (Constructor c : cons) {
            System.out.println(c.getName() + ", 参数数量:" + c.getGenericParameterTypes().length);
        }
        System.out.println("==");

        for (Constructor c : cons) {
            System.out.println(c.getName() + ", 参数数量:" + c.getParameterTypes().length);
        }

        System.out.println("==");
        Constructor[] ccc = sonClass.getDeclaredConstructors();

        for (Constructor c : ccc) {
            System.out.println(c.getName() + ", 参数数量:" + c.getGenericParameterTypes().length);
        }
    }

    /**
     * 获取构造器
     */
    @Test
    public void testGetDeclaredConstructors() {
        Class sonClass = Son.class;
        Constructor[] cons = sonClass.getDeclaredConstructors();
        for (Constructor c : cons) {
            System.out.println(c.getName() + ", 参数数量:" + c.getGenericParameterTypes().length);
            Type[] types = c.getGenericParameterTypes();
            System.out.println(" ddddddddddddddddddddddddd  " + types.getClass().getSimpleName());
            System.out.println(" ddddddddddddddddddddddddd  " + types.getClass());
            for (Type t : types) {
                System.out.println(t.getTypeName());
            }
        }
    }

    @Test
    public void test111() {
        String[] strs = new String[3];
        System.out.println(strs.getClass().getSimpleName());
        System.out.println(strs.getClass());
    }
}
