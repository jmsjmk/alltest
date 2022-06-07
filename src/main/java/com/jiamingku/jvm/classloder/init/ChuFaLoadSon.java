package com.jiamingku.jvm.classloder.init;

/**
 * Created by jiamingku on 2018/11/7.
 */
public class ChuFaLoadSon extends ChuFaLoadParent {
    static {
        System.out.println(" ChuFaLoadSon    init!");
    }

    public static final String HELLO_WORLD = "hello world";

    public static String tqs() {
        System.out.println("tqs...");
        return "ddd";
    }

    public ChuFaLoadSon() {
        System.out.println("S = ");
    }

    public static void main(String[] args) {
        ChuFaLoadSon c = new ChuFaLoadSon();
        System.out.println("");
    }
}

class ChuFaLoadParent {
    public static String S = "S";

    static {
        System.out.println("触发了父类加载");
    }

    static void a() {
    }

    public ChuFaLoadParent() {
        System.out.println("S = " + S);
    }
}

