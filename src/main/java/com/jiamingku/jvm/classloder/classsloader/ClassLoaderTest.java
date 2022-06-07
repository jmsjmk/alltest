package com.jiamingku.jvm.classloder.classsloader;

import java.io.InputStream;

/**
 * 其实这种属于破坏双亲委派原则:::
 * -------------------------------
 * 1.重写loadClass:(线程安全,缓存机制)都需要注意,相同的类不能重复加载.所以不建议重写loadClass方法.
 * <p/>
 * 2.如何能通过一个.class文件返回一个Class对象(读取到流,defineClass)
 * <p/>
 * 3.类加载器在defineClass时候会加载到Object类型, 任何需要加载的类都会加载到Object类型(内部保证Object对象是唯一的也就是说与其他类加载器公用)
 * <p/>
 * 4.java.lang.ClassLoader#defineClass(java.lang.String, byte[], int, int)发现类的权限定名一样的情况下, 报错attempted duplicate class definition for name
 * <p>
 * 5.object instanceof Class ==object必须有相同的类加载器
 */
@SuppressWarnings("all")
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {

        ClassLoaderTest classLoaderTest = new ClassLoaderTest();

        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    if (!name.contains("jiamingku")) {
                        return super.loadClass(name);
                    }
                    ClassLoader c1 = getClass().getClassLoader();
                    // InputStream is = c1.getResourceAsStream(fileName);
                    String fileName = "com/jiamingku/jvm/classloder/classsloader/ClassLoaderTest1.class";
                    InputStream is = c1.getResourceAsStream(fileName);
                    if (is == null) {
                        System.out.println("没有发现[" + fileName + "],进入双亲委派模式:");
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    // ---加载一个类-他会找到直接的父类--这里面会保证基础类唯一.
                    return defineClass(name, b, 0, b.length);
                } catch (Exception e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Class c = myLoader.loadClass("com.jiamingku.jvm.classloder.classsloader.ClassLoaderTest1");
        Object object = c.newInstance();
        // 试图加载两次就报错
        // 试图加载两次就报错
        // Object object1 = myLoader.loadClass("com.jiamingku.jvm.classloder.ClassLoaderTest1").newInstance();

        // ---------------
        System.out.println("自定义加载器构建的对象::::::::" + object.getClass());
        System.out.println("object instanceof ClassLoaderTest1:" + (object instanceof ClassLoaderTest1));
        // ----------------
        boolean instance = c.isInstance(object);
        System.out.println("instance = " + instance);


        // 用那个类，那个类的加载器就应该确定
        ClassLoader classLoader = object.getClass().getClassLoader();
        System.out.println("自定义的加载器:: classLoader = " + classLoader);
        System.out.println("---判断两个类是否相同,除了class是否相同,还有对应的类加载器是否相等!");
        boolean b = object.getClass() == ClassLoaderTest1.class;
        System.out.println("系统加载器加载的:" + object.getClass().getClassLoader());
        System.out.println(ClassLoaderTest1.class.getClassLoader());
        System.out.println(b);

        /**
         *  defineClass(name, b, 0, b.length); 在加载类的时候,虽然加载到了Object，但是保证基础类是唯一的.
         */
        boolean b1 = (object.getClass().getSuperclass() == Object.class);
        System.out.println("b1 = " + b1);
    }
}
