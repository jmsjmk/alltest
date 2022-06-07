package com.jiamingku.j2se.innercalss;

/**
 * Created by jiamingku on 2018/6/13.
 */
public class ThisTest {

    public static void main(String[] args) {

        ThisTest t1 = new ThisTest();
        ThisTest t2 = new ThisTest();

        A a1 = t1.new A();
        A a2 = t2.new A();

        boolean b = a1.isOwn(t2);
        System.out.println(b);
        b = a1.isOwn(t1);
        System.out.println(b);
    }

    private String bb = 1000 + "";

    public class A {
        private String name;
        private String age;

        private String bb= "dddd";
        boolean isOwn(ThisTest t) {
            return ThisTest.this == t;

        }


        public void a() {
            String b = ThisTest.this.bb;
            String b1= this.bb;
            String c = this.bb();
            System.out.println("this.getClass().getName() = " + this.getClass().getName());
            System.out.println("ThisTest.this.getClass().getName() = " + ThisTest.this.getClass().getName());


        }

        public String bb() {
            System.out.println(" 内部类 = ");
            return "dd";
        }

    }

    public static String bb() {
        System.out.println(" 外不累 ");
        return "a";

    }

//	public static void main(String[] args) {
//		ThisTest.A a = new ThisTest().new A();
//		// 包访问权限
//		// T1.defaultClass inner1 = new T1().new defaultClass();
//		a.a();
//		System.out.println(" ============================= ");
//
//
////
////		ThisTest thisTest = new ThisTest();
////		thisTest.test();
//
//		ThisTest thisTest = new ThisTest() {
//
//			@Override
//			public void aaaa() {
//				System.out.println("this.getClass().getName() = " + this.getClass().getName());
////				System.out.println("ThisTest.this.getClass().getName() = " + ThisTest.this.getClass().getName());
//			}
//		};
//
//
//	}

    public void test() {
        ThisTest thisTest = new ThisTest() {

            @Override
            public void aaaa() {
                System.out.println("this.getClass().getName() = " + this.getClass().getName());
                System.out.println("ThisTest.this.getClass().getName() = " + ThisTest.this.getClass().getName());
            }
        };

        thisTest.aaaa();
    }

    public void aaaa() {
        System.out.println("bb = " + bb);

        ThisTest.this.aaaac();

        this.aaaac();

    }

    public void aaaac() {
        System.out.println("bb = " + bb);


    }


}
