package com.jiamingku.j2se.refelct;

import com.jiamingku.j2se.refelct.bo.Son;
import com.jiamingku.j2se.refelct.bo.Son2;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.TypeVariableImpl;

import java.lang.reflect.*;

/**
 *  + "\n, c.getGenericParameterTypes().length:" + c.getGenericParameterTypes().length
 *
 *                     + "\n, c.getTypeParameters().length:" + c.getTypeParameters().length
 *
 *                     + "\n, c.getParameters().length:" + c.getParameters().length
 *
 *                     + "\n, c.getParameterTypes().length:" + c.getParameterTypes().lengt
 *
 *                     下面的这几个方法没整明白
 */
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
            /**
             * 传递null, 与不传递效果是一样的.
             */
            Constructor constructor = sonClass.getConstructor(null);
            System.out.println(constructor.getName() + "    ======   " + constructor.getTypeParameters().length);
            Constructor<Son> constructor1 = sonClass.getConstructor();
            System.out.println(constructor1.getName() + "    ======   " + constructor1.getTypeParameters().length);

            // -------------------------------------------------------
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
        Constructor[] ccc = sonClass.getDeclaredConstructors();

        for (Constructor c : ccc) {

            System.out.println(c.getName()
                    + ",  c.getGenericParameterTypes().length:" + c.getGenericParameterTypes().length
                    + ",  c.getTypeParameters().length:" + c.getTypeParameters().length);
        }
    }

    /**
     * 获取构造器
     */
    @Test
    public void testGetDeclaredConstructors() {
        Class sonClass = Son2.class;
        Constructor[] cons = sonClass.getDeclaredConstructors();
        for (Constructor c : cons) {
            System.out.println(c.getName()
                    + "\n, c.getGenericParameterTypes().length:" + c.getGenericParameterTypes().length

                    + "\n, c.getTypeParameters().length:" + c.getTypeParameters().length

                    + "\n, c.getParameters().length:" + c.getParameters().length

                    + "\n, c.getParameterTypes().length:" + c.getParameterTypes().length


            );

            // -------------------------------------------
             Parameter[] parameters = c.getParameters();

            Type[] genericParameterTypes = c.getGenericParameterTypes();

            TypeVariable[] typeParameters = c.getTypeParameters();
            System.out.println("typeParameters = " + typeParameters.length + "------------------fuck 0");
            Class[] parameterTypes = c.getParameterTypes();


            // ----------------------------------------------------------------


            for (Parameter parameter : c.getParameters()) {
                System.out.println("parameter = " + parameter);
                System.out.println("parameter = " + parameter.getType());
            }

            Type[] types = c.getGenericParameterTypes();
            for (Type t : types) {
                System.out.println("t = " + t.getClass());
                System.out.println(t.getTypeName());
            }
            System.out.println("-----------------------------------");
            System.out.println();


            for (TypeVariable typeParameter : c.getTypeParameters()) {
                System.out.println("typeParameter = " + typeParameter);
            }

            Son2 son1 = new Son2<String, String> ("1","1");

            for (Constructor<?> constructor : son1.getClass().getConstructors()) {
                for (Class<?> parameterType : constructor.getParameterTypes()) {
                    System.out.println("parameterType = " + parameterType);
                }
            }

            for (Constructor<?> constructor : son1.getClass().getConstructors()) {
                for (Type parameterType : constructor.getGenericParameterTypes()) {
                    System.out.println("parameterType = " + parameterType);
                    System.out.println("parameterType = " + parameterType.getTypeName());
                    TypeVariableImpl typeVariable = (TypeVariableImpl) parameterType;
                    String name = typeVariable.getName();
                    System.out.println("name = " + name);
                    GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
                    System.out.println("genericDeclaration = " + genericDeclaration);
                }
            }
        }
    }


}
