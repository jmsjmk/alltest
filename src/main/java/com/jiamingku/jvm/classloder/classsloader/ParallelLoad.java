package com.jiamingku.jvm.classloder.classsloader;

/**
 * 并行处理 <p/>
 * <p>
 * https://blog.csdn.net/yellowFARF/article/details/105448321 <p/>
 */
public class ParallelLoad {

    public static void main(String[] args) throws Exception {

        // 自定义类加载器
        ClassLoader customClassLoader = new CustomClassLoader();

        Class zclass = customClassLoader.loadClass("huangy.hyinterface.Generator");

        System.out.println(zclass);

        ParallelLoad.class.getClassLoader();
        ParallelLoad parallelLoad = new ParallelLoad();

    }


}

class CustomClassLoader extends ClassLoader {

    public CustomClassLoader() {
        super();
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        // 这里加载二进制字节流的方式可以自己决定
        return super.loadClass(name, resolve);
    }

    static {
        // 注册，让类加载器支持并行加载类
        ClassLoader.registerAsParallelCapable();
    }
}
