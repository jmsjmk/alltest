package com.jiamingku.j2se.innercalss.instance;

/**
 * Created by jiamingku on 2017/7/8.
 * <p>
 * 访问内部类，外不类提供访方法。但是内部类的访问权限必须可见
 */
public class T1 {

    static String a = "dd";
    private String dd = "ddd";

    public static class SS {
        private String kk;
        public void bb() {
//            System.out.println("dd = " + dd);
            System.out.println("kk = " + kk);
            System.out.println("a = " + a);
        }

        public static void b2b() {
//            System.out.println("dd = " + dd);
//            System.out.println("kk = " + kk);
            System.out.println("a = " + a);
        }
    }
    /**
     * 定义一个内部类
     */
    public class Content {
        String a = "3333";

        public void t() {
            System.out.println("a = " + T1.this.a);
        }
    }

    private class privateClass {
    }

    /**
     * 保持不变同包可见
     */
    class defaultClass {
        // static int a = 100;
    }

    protected class protectedClass {
    }


    // -------------------------------

    public static class publicStaticClass {

    }

    private static class privateStaticClass {

    }

    protected static class protectedStaticClass {
    }

    static class defaultStaticClass {
    }


    public Content getContent() {
        return new Content();
    }

    public static void main(String[] args) {
        T1 t1 = new T1() {
        };
        T1 t2 = new T1();

//		Parcel7 p = new Parcel7();
//		p.a = 10000;
//		Wrapping w = p.wrap(10);
//		System.out.println(w.value());
    }
}

