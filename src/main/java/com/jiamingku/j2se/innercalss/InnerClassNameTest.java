package com.jiamingku.j2se.innercalss;

/**
 * 关于内部类的名字:::::
 * InnerClassNameTest$1.class
 * InnerClassNameTest$2.class                    // 局部内部类
 * InnerClassNameTest$InnerClassNameTest1.class  // 成员内部类
 * InnerClassNameTest$InnerClassNameTest2.class  // 成员内部类
 * InnerClassNameTest.class
 */
public class InnerClassNameTest {

    public static class InnerClassNameTest1 {

    }

    public class InnerClassNameTest2 {

    }

    /**
     * 方法内的：按照顺序$1 向后面找.
     * @return
     */
    public InnerClassNameTest t1() {

        return new InnerClassNameTest() {

        };
    }

    public InnerClassNameTest t2() {

        return new InnerClassNameTest() {

        };
    }

    public static void main(String[] args) {
        InnerClassNameTest innerClassNameTest = new InnerClassNameTest();
        System.out.println("innerClassNameTest.getClass().getSimpleName() = " + innerClassNameTest.getClass().getSimpleName());

        InnerClassNameTest.InnerClassNameTest1 innerClassNameTest1 = new InnerClassNameTest1();
        System.out.println("innerClassNameTest1.getClass().getSimpleName() = " + innerClassNameTest1.getClass().getSimpleName());

        InnerClassNameTest.InnerClassNameTest2 classNameTest2 = new InnerClassNameTest().new InnerClassNameTest2();
        System.out.println("classNameTest2.getClass().getSimpleName() = " + classNameTest2.getClass().getSimpleName());


        InnerClassNameTest innerClassNameTest2 = innerClassNameTest.t1();

        InnerClassNameTest innerClassNameTest3 = innerClassNameTest.t2();

        System.out.println("innerClassNameTest3 = " + innerClassNameTest3);

    }

}
