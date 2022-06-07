package com.jiamingku.jvm.classloder.classsloader;

import org.junit.Test;
import sun.misc.URLClassPath;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;

/**
 * jvm提供了三种加载器
 * 1.bootstrap--启动加载器(c++编写)
 * <p>
 * 2.extClassLoader--扩展加载器(java编写)
 * <p>
 * 3.appClassLoader--应用程序加载器(java编写)
 * <p>
 * 这里面需要注意一点, 2,3两个加载器是java编写的,他们也需要被加载,他们是bootStrap加载器加载初始化的
 */
public class PathInfoTest {

    @Test
    public void testC() {
        ClassLoader classLoader = PathInfoTest.class.getClassLoader();
        while (classLoader != null) {
            System.out.println(classLoader);
            classLoader = classLoader.getParent();
        }
    }

    /**
     * jvm 自定义属性::::: -D传递给jvm 通过system.getProperties()获取
     */
    @Test
    public void testConfigPrintSystem() {
        Object s = System.getProperties().get("what");
        System.out.println("s = " + s);
        // 传递的属性，是可以获取到的
        int cnt = 0;
        for (Map.Entry<Object, Object> entry : System.getProperties().entrySet()) {
            System.out.println("entry.getKey() = " + entry.getKey() + "\t\t\t\t\t\t |" + entry.getValue());
            cnt++;
        }
        System.out.println("cnt = " + cnt);
    }

    /**
     * jvm 系统启动的时候会有很多系统参数.大概的有几十个甚至更多
     * <p>
     * 编译时候直接去%JAVA_HOME%/lib路径,系统默认的去搜索jar与*.class等文件
     * java虚拟机的BootStrapLoader加载java的核心类库(JAVA_HOME环境变量来定位核心库的位置)
     * http://www.linuxidc.com/Linux/2011-08/41684.htm 经典,tomcat的类加载路径问题
     * <p>
     * ---------------------------------------
     * 下面获取classLoader的两种方式获取的根加载路径一样.
     */
    @Test
    public void testPrintBootClassLoaderPath() {

        URLClassPath urlClassPath = sun.misc.Launcher.getBootstrapClassPath();

        System.out.println("urlClassPath = " + urlClassPath);
        for (URL url : urlClassPath.getURLs()) {
            System.out.println("url = " + url);
        }
        System.out.println(" = ");
        System.out.println(" = ");

        String s = System.getProperties().get("sun.boot.class.path").toString();
        String[] array = s.split(":");
        for (String s1 : array) {
            System.out.println(s1);
        }

        System.out.println("----------------------------");
        System.out.println("----------------------------");
        System.out.println("----------------------------");


        // System.out.println("System.getProperties().size() = " + System.getProperties().size());
        for (Map.Entry<Object, Object> entry : System.getProperties().entrySet()) {
            if (entry.getKey().equals("user.dir")) {
                System.out.println("entry.getKey() = " + entry.getKey());
                System.out.println("entry.getValue().toString() = " + entry.getValue().toString());
                System.out.println(" === ");
            }
        }
        // 根加载器获取是null
        ClassLoader classLoader = Integer.class.getClassLoader();
        System.out.println("classLoader = " + classLoader);
    }

    /**
     * 扩展的加载器
     */
    @Test
    public void printExtClassLoaderPath() {
        String s = System.getProperty("java.ext.dirs");
        String[] strings = s.split(":");
        for (String s1 : strings) {
            System.out.println("" + s1);
        }
        System.out.println("-------上面是获取加载的路径, 下面是将路径下面的东西给你详细的载入出来.");
        System.out.println("-------上面是获取加载的路径, 下面是将路径下面的东西给你详细的载入出来.");
        System.out.println("-------上面是获取加载的路径, 下面是将路径下面的东西给你详细的载入出来.");
        //取得扩展类加载器
        URLClassLoader extClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader().getParent();
        URL[] arrays = extClassLoader.getURLs();
        for (URL u : arrays) {
            System.out.println(u.toString());
        }
    }

    /**
     * 重要
     * <p>
     * — 在命令行输入的参数 -cp 或者-classpath都可以通过这个属性获取
     * <p>
     * — Java程序需要class文件,需要载入这些东西，必须的告诉一个路径
     */
    @Test
    public void printAppClassLoaderPath() {
//        String s = System.getProperty("java.class.path");
//        System.out.println(" =========== ");
//        String[] strings = s.split(":");
//        for (String s1 : strings) {
//            System.out.println("" + s1);
//        }

        //取得扩展类加载器
        URLClassLoader extClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        URL[] arrays = extClassLoader.getURLs();
        for (URL u : arrays) {
            System.out.println(u.toString());
        }
    }


}
