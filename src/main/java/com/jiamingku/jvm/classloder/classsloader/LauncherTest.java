package com.jiamingku.jvm.classloder.classsloader;

/**
 *
 */
public class LauncherTest {

    /**
     * 1.有的"类加载器"也是java编写,同样也需要加载
     * 2.jvm在启动的时候会启动bootStrap 加载器.他是c++编写的
     * 3.sun.misc.Launcher-包含扩展加载器，系统加载器, 所以这个的初始化是由
     * 4.bootStrap加载器进行的加载,rt.jar这里面的都是bootStrap加载的。
     *
     * @param args
     */
    public static void main(String[] args) {

        sun.misc.Launcher launcher = sun.misc.Launcher.getLauncher();
        System.out.println("code :" + System.identityHashCode(launcher));
        System.out.println("launcher is boot load:" + launcher.getClass().getClassLoader());
        // 正常
        System.out.println("launcher = " + launcher.getClassLoader());
        System.out.println(launcher.getClass().getClassLoader());
        System.out.println("ClassLoader.getSystemClassLoader() = " + ClassLoader.getSystemClassLoader());


        String s = "12";
        ClassLoader cl = s.getClass().getClassLoader();

        if (cl == null) {
            System.out.println(" ======null ");
        } else {
            String sname = s.getClass().getClassLoader().toString();
            System.out.println("sname = " + sname);
        }

        cl = LauncherTest.class.getClassLoader();
        if (cl == null) {
            System.out.println(" ==11111111111111111111111====null ");
        } else {
            String sname = cl.toString();
            System.out.println("sname11111111111111 = " + sname);
        }
    }
}
