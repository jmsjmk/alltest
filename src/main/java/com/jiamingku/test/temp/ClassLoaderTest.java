package com.jiamingku.test.temp;

/**
 * 重写loadClass,就像功能,非线程安全,并且没有缓存机制导致加载相同的类报错.所以尽量不要重写
 * 1.根据输入的地址载入类信息*注意下路径就行
 * <p>
 * 2.载入一个类的时候,它的父类也会载入(发起调用loadClass()方法jvm会根据你载入的类,对应的加载父类)
 * 也就是说loadClass方法会调用多次
 * <p>
 * 3.java.lang.ClassLoader#defineClass(java.lang.String, byte[], int, int)发现类的权限定名一样的情况下,
 * 报错attempted  duplicate class definition for name
 * <p>
 * 4.object instanceof Class ==object必须有相同的类加载器
 * <p>
 * 5.并不会进行初始化.
 * Class c = myLoader.loadClass("com.jiamingku.jvm.classloder.ClassLoaderTest1");
 */
@SuppressWarnings("all")
class p {
    static {
        System.out.println("p");
    }
}

public class ClassLoaderTest extends p {
    static {
        System.out.println("son");
    }

    public static void main(String[] args) throws Exception {

        Test111 t = new Test111();

        Test111 t1 = new Test111();

    }
}
