package com.jiamingku.jvm.classloder.classsloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 1.就是给你class文件,你如何载入到jvm内部. 你继承然后可以用父类方法,省去很多麻烦事.
 * <p>
 * 2.自定义加载的父类就是:appClassLoader
 */
@SuppressWarnings("all")
public class ReadFileTest extends ClassLoader {
    private static String path = "/Users/jiamingku/IdeaProjects/alltest/target/classes";
    private static String className = "com.jiamingku.jvm.classloder.Test";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println(" ------------------------------------ ");
        String fileName = getFileName(name);
        File file = new File(path, fileName);
        try {
            FileInputStream is = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len = 0;
            try {
                while ((len = is.read()) != -1) {
                    bos.write(len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] data = bos.toByteArray();
            is.close();
            bos.close();
            Class<?> c = null;
            /**
             * 这句话非常重要，最终会调用一个本地方法，会直接将classloader设置到 class里面去
             *
             * 这样class就可以获取classloader了。
             */
            c = defineClass(name, data, 0, data.length);
            System.out.println("c.getClassLoader() = " + c.getClassLoader());
            return c;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    //将包名转换为实际路径
    private String getFileName(String name) {
        name = name.replaceAll("\\.", "/");
        return name + ".class";
    }

    /**
     * 这个demo 不是双亲委派模型，他只是通过jdk体统的代码，来载入一个类。但这个类的类加载器已经被设置上了
     * <p>
     * 虽然通过findClass来加载的class，
     * </p>
     *
     * @param args
     */
    public static void main(String[] args) {
        ReadFileTest classLoaderTest = new ReadFileTest();

        try {
            // 没有破坏双亲委派原则,下面的是app加载
            Class c = classLoaderTest.loadClass(className);
            System.out.println(c.getClassLoader() + "---> parent --> " + c.getClassLoader().getParent());

            // find  就会爆出重复异常--define那个类会出现异常--下面是自定义加载
            Class c1 = classLoaderTest.findClass(className);
            // Class c2 = classLoaderTest.findClass(className);
            System.out.println(c1.getClassLoader() + "---> parent --> " + c1.getClassLoader().getParent());

            if (c1 == c) {
                System.out.println("类型相等!");
            } else {
                System.out.println("类型不相等!");
            }

            Object obj = c.newInstance();
            System.out.println("obj.getClass().getClassLoader() = " + obj.getClass().getClassLoader());
            System.out.println("c1==" + c1.getClassLoader());
            Method method1 = c.getDeclaredMethod("main", String[].class);
            Method method2 = c.getDeclaredMethod("fun", null);
            method1.invoke(obj, (Object) new String[]{});
            method2.invoke(obj, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}