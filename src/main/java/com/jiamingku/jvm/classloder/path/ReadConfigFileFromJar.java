package com.jiamingku.jvm.classloder.path;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class ReadConfigFileFromJar {

    private static Properties properties;

    public static String getObject(String prepKey) {
        return properties.getProperty(prepKey);
    }

    /**
     * user.dir = /Users/jiamingku/IdeaProjects/alltest, 就是java命令运行的所在目录,
     * <p>
     * 基于user.dir这种使用方式读取资源文件,你运行java的时候如果命令所在的目录 不同可能得到的结果不同
     * 最好用相对路径的方式去获取。
     */
    @Test
    public void readByUserDir() {
        try {
            Properties properties = new Properties();
            System.out.println("System.getProperty(\"user.dir\") = " + System.getProperty("user.dir"));
            String filePath = System.getProperty("user.dir") + "/config/init.properties";
            System.out.println("filePath = " + filePath);
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            properties.load(in);
            System.out.println(properties.getProperty("testsql"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取jar包中的文件,通过classLoader去获取
     * URL:是定位一个资源的,URL可以获取path,但是通过这个path你直接构建流对象有可能发生错误，
     * 例如: javaFile方式获取不到-file:/Users/jiamingku/IdeaProjects/alltest/src/lib/testreadjar.jar!/132.properties3333
     * 这时候我们如果获取到流呢?直接通过url.open获取流信息, 就跟如何获取jar包中的class一样jdk提供了对应的方法
     * <p>
     * ClassPathLoader，URLClassPath(可以观看源代码)根据jar包中文件的格式来加载。
     * <p>
     * 同样我们获取到对应的url 直接open，就可以，就是jar包的中文件，也会有对应的获取流的方法，jdk提供。
     */
    @Test
    public void readByResourceByAppClassLoader() {
        try {
            /**
             * 获取应用程序加载器-appClassLoader
             *
             * URLClassPath
             */
            URL url = ClassLoader.getSystemResource("132.properties");
            System.out.println("url = " + url);
            System.out.println("path = " + url.getPath());
            Properties properties = new Properties();

            // 这种方式获取不到，你必须的通过对应的加载器去加载URLClassPath
            // System.out.println("path = " + path);
            // InputStream in = new BufferedInputStream(new FileInputStream(path));
            properties.load(url.openStream());
            System.out.println(properties.getProperty("testsql"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * user.dir = /Users/jiamingku/IdeaProjects/alltest
     * https://blog.csdn.net/u012903926/article/details/46889847  ---读取jar包中属性文件的顺序
     */
    @Test
    public void readConfigFileFromJarByGetResource() {
        try {
            Properties properties = new Properties();
            URL url = ReadConfigFileFromJar.class.getResource("/132.properties");
            // path = /Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/classes/132.properties

            String path = url.getPath();
            System.out.println("path = " + path);
            // uri = jar:file:/Users/jiamingku/IdeaProjects/alltest/src/lib/testreadjar.jar!/132.properties
            // 这是系统获取的jar的路径--但是你通过这个获取不到资源
            //  java是通过实现类JarURLConnection 实现的--
            properties.load(url.openStream());
            System.out.println(properties.getProperty("testsql"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 注意下路径,没啥的就是找到对应的文件就行了呗.
     */
    @Test
    public void readByResource() {
        try {
            Properties properties = new Properties();
            String path = ReadConfigFileFromJar.class.getResource("/init.properties").getPath();
            System.out.println("path = " + path);
            InputStream in = new BufferedInputStream(new FileInputStream(path));
            properties.load(in);
            System.out.println(properties.getProperty("testsql"));

            path = ReadConfigFileFromJar.class.getClassLoader().getResource("init.properties").getPath();
            System.out.println("path = " + path);
            properties.load(in);
            System.out.println(properties.getProperty("testsql"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 不能以反斜线开头,一层层的获取路径.
     */
    @Test
    public void readByClassLoader() {
        try {
            Properties properties = new Properties();
            properties.load(ReadConfigFileFromJar.class.getClassLoader().getResourceAsStream("init.properties"));
            System.out.println(properties.getProperty("testsql"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readConfigFileFromJarByClassLoaderStream() {
        try {
            Properties properties = new Properties();
            //properties.load(Config.class.getResourceAsStream("/132.properties"));
            properties.load(ReadConfigFileFromJar.class.getClassLoader().getResourceAsStream("132.properties"));
            System.out.println(properties.getProperty("testsql"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ttt() {
        String bootClassPath = System.getProperty("sun.boot.class.path");
        System.out.println("bootClassPath = " + bootClassPath);
  }
}