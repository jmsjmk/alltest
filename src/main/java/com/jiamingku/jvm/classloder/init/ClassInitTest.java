package com.jiamingku.jvm.classloder.init;

import org.junit.Test;

public class ClassInitTest {

    public static void main(String[] args) throws Exception {
//         1.创建类的实例-导致类加载-并调用初始化
        // ChuFaLoad chuFaLoad = new ChuFaLoad();

        // 2.引用类的静态属性--导致类的初始化(父类属性, 但是子类引用进行引用-导致父类初始化-子类不进行初始化)
        System.out.println(ChuFaLoad.S);
//        System.out.println(ChuFaLoadClass.D); ===final static 不会导致类初始化.
        // 3.调用静态方法
//         ChuFaLoadClass.a();

        // 4.反射 -- 加载--也会进行初始化
//        Class c = Class.forName("com.jiamingku.jvm.classloder.init.ChuFaLoadParentClass");
//        System.out.println("c = " + c);
//        Class c1 = Class.forName("com.jiamingku.jvm.classloder.init.ChuFaLoadParentClass");
//        System.out.println("c1 = " + c1);

//        Class c = Class.forName("com.jiamingku.jvm.classloder.init.ChuFaLoadParentClass", false, ClassInitTest.class.getClassLoader());
//        System.out.println(c);
//        Object o = c.getConstructor().newInstance();
//        System.out.println("o = " + o);

        // 5.子类初始化
//        System.out.println(Constances.tqs());

        // 6.java在启动的时候指定了主类,会进行初始化

        // 5.加载但是不进行初始化 *********************************加载但是不会初始化
        Class a = ChuFaLoadParent.class;
    }

    // -------------------------------------------------------------------------------------------------------
    @Test
    public void test() {

        // 4.不初始化. 4)XXX.class时候不会进行初始化
        Class<ChuFaLoadSon> chuFaLoadSonClass = ChuFaLoadSon.class;
        System.out.println("chuFaLoadSonClass = " + chuFaLoadSonClass);

        // 1)public static final 编译时候直接将这个值当成字符串编译到本类的常量池中了
        System.out.println(" = " + ChuFaLoadSon.HELLO_WORLD);

        // 2.当访问一个静态变量时候,如果这个静态变量时从父类继承过来的,子类不会进行初始化
        System.out.println(ChuFaLoadSon.S);

        try {
            // 3.ClassLoader.loadClass不会触发类初始化，newInstance会进行初始化
            Class c = Thread.currentThread().getContextClassLoader().loadClass(ChuFaLoadSon.class.getName());
            System.out.println(c.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test3() {
        try {
            //Initable.class类加载但是不进行初始化, initable.newInstance();进行类初始化
//            Class initable = Initable.class;
//            initable.newInstance();
//            System.out.println("After creating Initable ref");
            // 不触发类初始化
            System.out.println(Initable.staticFinal);
            //会触发类初始化--
            System.out.println(Initable.staticFinal2);
//            System.out.println("=================\" = =================");
//            //会触发类初始化
//            System.out.println(Initable2.staticNonFinal);
            //forName方法获取Class对象
//            Class initable3 = Class.forName("com.jiamingku.jvm.classloder.init.Initable3");
//            System.out.println("After creating Initable3 ref");
//            System.out.println(Initable3.staticNonFinal);

            Class cccc = ChuFaLoadSon.class;
            System.out.println(cccc.getSimpleName());
            System.out.println(cccc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

class Initable {
    //编译期静态常量
    static final int staticFinal = 47;
    //非编期静态常量
    static final int staticFinal2 = (int) Math.random() * 100;

    static {
        System.out.println("Initializing Initable");
    }
}

class Initable2 {
    //静态成员变量
    static int staticNonFinal = 147;

    static {
        System.out.println("Initializing Initable2");
    }
}

class Initable3 {
    //静态成员变量
    static int staticNonFinal = 74;

    static {
        System.out.println("Initializing Initable3");
    }
}
