package com.jiamingku.j2se.genneric;

/**
 * https://blog.csdn.net/a327369238/article/details/52608805
 * <p>
 * https://blog.csdn.net/a327369238/article/details/52608805
 * <p>
 * jvm生成的一个虚拟的标记类吧.
 */
public class Main {

    private static class Inner {
    }

    static void checkSynthetic(String name) {
        try {
            System.out.println(name + " : " + Class.forName(name).isSynthetic());
        } catch (ClassNotFoundException exc) {
            exc.printStackTrace(System.out);
        }
    }

    public static void main(String[] args) throws Exception {
        new Inner();
        checkSynthetic("com.jiamingku.j2se.genneric.Main");
        checkSynthetic("com.jiamingku.j2se.genneric.Main$Inner");
        checkSynthetic("com.jiamingku.j2se.genneric.Main$1");
    }
}

////打印结果：
//com.fcc.test.Main : false
//        com.fcc.test.Main$Inner : false
//        com.fcc.test.Main$1 : true
////编译结果，生成三个class文件: Main.class/Main$inner/Main$1.class
//// $FF: synthetic class
//class Main$1 {
//}