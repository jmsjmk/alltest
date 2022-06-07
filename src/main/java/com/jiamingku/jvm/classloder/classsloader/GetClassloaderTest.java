package com.jiamingku.jvm.classloder.classsloader;

import org.junit.Test;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

@SuppressWarnings("all")
public class GetClassloaderTest {

    /**
     * 获取classLoader
     *
     * @param argLauncherTests
     */
    public static void main(String[] args) throws Exception {
        // 1.
        GetClassloaderTest.class.getClassLoader();

        // 2.
        ClassLoader a = Thread.currentThread().getContextClassLoader();
        System.out.println("a = " + a);
//        a.loadClass("dddd");

        // 3.
        a = ClassLoader.getSystemClassLoader();
        System.out.println("a = " + a);
    }

    /**
     * 核心类的加载器
     */
    @Test
    public void test1() {
        // 如果是bootstrap加载器 加载的类的话返回null ，rt.jar
        ClassLoader classLoader1 = Runnable.class.getClassLoader();
        System.out.println("classLoader1 = " + classLoader1);

        ClassLoader classLoader2 = Object.class.getClassLoader();
        System.out.println("classLoader2 = " + classLoader2);
    }

    /**
     * 获取各种父类加载器
     */
    @Test
    public void testParent() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader + " [parent] : " + classLoader.getParent());

        ClassLoader classLoader1 = classLoader.getParent();

        System.out.println(classLoader1 + " [parent] : " + classLoader1.getParent());
    }

    /**
     * 本地加载类信息
     */
    @Test
    public void testJarLoad() {
        try {
            URL url = new URL("file:/Users/jiamingku/IdeaProjects/alltest/src/lib/testreadjar.jar");
            URLClassLoader classLoader = new URLClassLoader(new URL[]{url});
            System.out.println("classLoader.getParent() = " + classLoader.getParent());
            Class c = classLoader.loadClass("com.test.Test");
            com.test.Test t = (com.test.Test) c.newInstance();
            System.out.println("t.getClass() = " + t.getClass());
            System.out.println("c.getClassLoader() = " + c.getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 类加载双亲委派信息,没有上下级的继承关系
     */
    @Test
    public void testSuperClass() {
        try {
            Class c1 = Thread.currentThread().getClass().getSuperclass();
            System.out.println(c1.getSimpleName());
            while (c1 != null) {
                c1 = c1.getSuperclass();
                System.out.println(c1.getSimpleName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 原生类型的比较
     */
    @Test
    public void testYs() {
        ClassLoader cl = GetClassloaderTest.class.getClassLoader();
        System.out.println("用户自定义类： = " + cl);
        while (cl != null) {
            System.out.println(cl + "-----> parent ClassLoader ----->" + cl.getParent());
            cl = cl.getParent();
        }
        ClassLoader c = Integer.class.getClassLoader();
        System.out.println("c = " + c);

        c = String.class.getClassLoader();
        System.out.println(c);
        c = List.class.getClassLoader();
        System.out.println(c);

        boolean b = (Integer.class == int.class);
        System.out.println(Integer.class);
        System.out.println(int.class);
        System.out.println(Integer.TYPE);
        System.out.println("b = " + b);
    }

    /**
     * 加载扩展包
     * @throws Exception
     */
    @Test
    public void testClassFileClassLoader() throws Exception {
        URL url = new URL("file:" + "/Library/Java/Extensions/");
        @SuppressWarnings("resource")
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[] { url }); // 加载外部的jar包
        Class<?> clazz = urlClassLoader.loadClass("com.jiamingku.j2se.refelct.classloader.demo6.BBAC");
        Object obj = clazz.newInstance();
        Method method = clazz.getMethod("toString");
        Object o = method.invoke(obj);
        System.out.println(o);
    }


    @Test
    public void testClassFileClassLoader1() throws Exception {

        Class<?> aClass = GetClassloaderTest.class.getClassLoader().loadClass("com.jiamingku.jvm.classloder.classsloader.Bo");

        Class<?> aClass1 = GetClassloaderTest.class.getClassLoader().loadClass("com.jiamingku.jvm.classloder.classsloader.Bo");

    }

}
