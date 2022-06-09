package com.jiamingku.j2se.refelct;

import com.jiamingku.j2se.casttype.SonCastTypeTest;
import com.jiamingku.j2se.refelct.bo.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 5.对象与类型,类型与对象
 * 5.1 Class.isInstance(对象) == instanceOf
 * <p>
 * 5.2 对象 instanceof Class （null instanceof 不会发生异常,会返回false）
 * <p>
 * 5.3 Class isAssignableFrom Class
 * <p>
 * 5.4 强制类型转换 == Son.class.cast(p);
 * {@link com.jiamingku.j2se.casttype.SonCastTypeTest}
 */
public class InstanceofAndisInstanceTest {

    @Test
    public void testCast() {
        Parent p = new Son();
        Son s = Son.class.cast(p);
        System.out.println(s.getClass());
        System.out.println("p.getClass() = " + p.getClass());
        System.out.println(" ------= ");

        Class o = null;
        boolean assignableFrom = o.isAssignableFrom(Map.class);
        System.out.println("assignableFrom = " + assignableFrom);
    }

    /**
     * null instanceof 不会发生异常,会返回false
     * <p>
     * {@link SonCastTypeTest}
     */
    @Test
    public void testInstanceofAndIsInstance() {
        InstanceofAndisInstanceTest t = new InstanceofAndisInstanceTest();
        SubTest subTest = new SubTest();

        if (t instanceof Object) {
            System.out.println(" t instanceof Object is true ");
        }

        // ------------------ isInstance----只能验证是不是这个类的对象-----------
        if (t.getClass().isInstance(Object.class)) {
            System.out.println("class isInstance is true");
        }
        if (t.getClass().isInstance(InstanceofAndisInstanceTest.class)) {
            System.out.println("class .......");
        }
        if (t.getClass().isInstance(t)) {
            System.out.println("class .......");
        }

        if (t.getClass().isInstance(new Object())) {
            System.out.println("t'=......");
        }

        if (t.getClass().isInstance(t)) {
            System.out.println(" =-===== gooddssdsd ");
        }

        if (t.getClass().isInstance(new SubTest())) {
            System.out.println(" =-===== sub Test");
        }

        if (t.getClass().isInstance(subTest)) {
            System.out.println(" =-===== sub Test");
        }
    }

    /**
     * 这个方法名，map是否可以分配来源于hashMap的类型
     */
    @Test
    public void TestIsAssignableFrom() {
        if (Map.class.isAssignableFrom(HashMap.class)) {
            System.out.println("true = " + true);
        } else {
            System.out.println("false = " + false);
        }

        if (Set.class.isAssignableFrom(HashMap.class)) {
            System.out.println("true = " + true);
        } else {
            System.out.println("false = " + false);
        }

        if (Object.class.isAssignableFrom(HashMap.class)) {
            System.out.println("true = " + true);
        } else {
            System.out.println("false = " + false);
        }

        System.out.println("===============================");

        if (DDDDD.class.isAssignableFrom(ABSTttt.class)) {
            System.out.println("yes");
        } else {
            System.out.println("false");
        }

        if (DDDDD.class.isAssignableFrom(DDDDD.class)) {
            System.out.println("yes");
        } else {
            System.out.println("false");
        }

        if (ABSTttt.class.isAssignableFrom(DDDDD.class)) {
            System.out.println("yes.....");
        } else {
            System.out.println("false");
        }


        if (TTTT.class.isAssignableFrom(DDDDD.class)) {
            System.out.println("yes.....SFSDFSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
        } else {
            System.out.println("false");
        }
    }

    // ----------------------------------------------------------------------------------------------------------
    static void test(Object x) {
        print("Testing x of type " + x.getClass());
        print("x instanceof Base " + (x instanceof Base));
        print("x instanceof Derived " + (x instanceof Derived));
        print("Base.isInstance(x) " + Base.class.isInstance(x));
        print("Derived.isInstance(x) " + Derived.class.isInstance(x));
        print("x.getClass() == Base.class " + (x.getClass() == Base.class));
        print("x.getClass() == Derived.class " + (x.getClass() == Derived.class));
        print("x.getClass().equals(Base.class)) " + (x.getClass().equals(Base.class)));
        print("x.getClass().equals(Derived.class)) " + (x.getClass().equals(Derived.class)));
    }

    public static void main(String[] args) {
        test(new Base());
        test(new Derived());
    }

    public static void print(Object o) {
        System.out.println("o = " + o);
    }

    /**
     * 会发生异常.
     */
    @Test
    public void testNull() {
        boolean assignableFrom = Set.class.isAssignableFrom(null);
        System.out.println(assignableFrom);
    }
}
