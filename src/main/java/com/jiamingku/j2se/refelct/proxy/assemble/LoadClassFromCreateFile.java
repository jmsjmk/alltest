package com.jiamingku.j2se.refelct.proxy.assemble;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * <p>
 * 1.生成一个类文件，
 * 2.然后编译在载入进来。
 * 3.调用执行方法执行
 * <p>
 * =====================
 * 类名字都是自己固定的jdk是$开头$Proxy1类似这样的
 * <p>
 * 这个demo解决了一个什么问题呢？通过一个文件(动态的生成文件，然后编译，载入，运行，动态代理分析的第一步)
 */
public class LoadClassFromCreateFile {

    private static String fileName = "/Users/jiamingku/testtempsvn/com/jiamingku/proxy/proxySecond1/Test1.java";

    /**
     * 动态代理的思想
     * <p>
     * 1.创建文件
     * 2.编译
     * 3.构建出来然后执行对应的方法
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            // 1.创建.java文件
            createClassFile();

            // 2.compile
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
            Iterable units = fileMgr.getJavaFileObjects(fileName);
            JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
            t.call();
            fileMgr.close();

            fileName = "/Users/jiamingku/testtempsvn/";
            URL[] urls = new URL[]{new URL("file://" + fileName)};
            URLClassLoader ul = new URLClassLoader(urls);
            Class c = ul.loadClass("com.jiamingku.proxy.proxySecond1.Test1");
            System.out.println(c);

            // 3.
            Object o = c.newInstance();
            Method m1 = c.getDeclaredMethod("a");
            m1.invoke(o);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //1.生成一个java文件。
    public static void createClassFile() {
        String calssStr = "package com.jiamingku.proxy.proxySecond1;\n" +
                "public class Test1 {\n" +
                "    public void a() {\n" +
                "        System.out.println(\"Test: excute train... \");\n" +
                "    }\n" +
                "}";
        File f = new File(fileName);
        FileWriter fw = null;
        try {
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            fw = new FileWriter(f);
            fw.write(calssStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.flush();
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("crateFile: " + fileName + " down!");
    }
}
