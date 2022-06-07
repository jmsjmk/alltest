package com.jiamingku.jvm.classloder.classsloader;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 保证代码的安全性.
 */
public class EnDeClassLoader extends ClassLoader {

    private String mLibPath;
    private static String className = "/Users/jiamingku/IdeaProjects/alltest/target/classes/com/jiamingku/jvm/classloder/Test.classen";

    public EnDeClassLoader(String path) {
        this.mLibPath = path;
    }

    public static void main(String[] args) {
        EnDeClassLoader diskLoader = new EnDeClassLoader("");
        try {
            //加载class文件
            Class c = diskLoader.findClass(className);
            if (c != null) {
                try {
                    Object obj = c.newInstance();
                    Method method = c.getDeclaredMethod("fun", null);
                    //通过反射调用Test类的say方法
                    method.invoke(obj, null);
                } catch (InstantiationException | IllegalAccessException
                        | NoSuchMethodException
                        | SecurityException |
                        IllegalArgumentException |
                        InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println(" 不直接掉用find 就走双亲委派原则了= ");
        File file = new File(name);
        try {
            FileInputStream is = new FileInputStream(file);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len = 0;
            byte b = 0;
            try {
                while ((len = is.read()) != -1) {
                    //将数据异或一个数字2进行解密
                    b = (byte) (len ^ 2);
                    bos.write(b);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] data = bos.toByteArray();
            is.close();
            bos.close();
            return defineClass("com.jiamingku.jvm.classloder.Test", data, 0, data.length);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return super.findClass(name);
    }

}


class FileUtils {
    public static void test(String path) {
        File file = new File(path);
        try {
            FileInputStream fis = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(path + "en");
            int b = 0;
            int b1 = 0;
            try {
                while ((b = fis.read()) != -1) {
                    //每一个byte异或一个数字2
                    fos.write(b ^ 2);
                }
                fos.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws  Exception{
        // test("/Users/jiamingku/IdeaProjects/alltest/target/classes/com/jiamingku/jvm/classloder/Test.class");
        System.out.println(" = ");


        File f = new File("/Users/jiamingku");

        String[] strings = f.list();
        for(String s : strings) {
            
            File ff = new File(f, s);

            System.out.println("ff.getAbsolutePath() = " + ff.getAbsolutePath());
            System.out.println("ff.getCanonicalPath() = " + ff.getCanonicalPath());
        }
    }

}