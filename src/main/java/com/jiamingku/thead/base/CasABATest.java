package com.jiamingku.thead.base;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 循环cas能达到所谓的互斥操作[伪互斥操作]---可以保证这个函数具有synchronized一样的功能
 */
public class CasABATest {
    public CasABATest() {
    }

    /**
     * cas是unsafe类中的方法
     */
    @Test
    public void test1() {
        AtomicInteger atomicInteger = new AtomicInteger(10);
        Integer i = atomicInteger.addAndGet(1);
        System.out.println("i = " + i);
    }

    /**
     * cas是unsafe类中的方法
     * java不建议我们自己使用,但是他自己的封装类中使用是安全的。
     */
    @Test
    public void test2() {
        AtomicBoolean locked = new AtomicBoolean(false);
        locked.compareAndSet(false, true);
    }


    @Test
    public void test3() {
        AtomicReference atomic1 = new AtomicReference();
        atomic1.set("aaa");
        atomic1.set(new StringBuffer("str"));
        Map map = new HashMap();
        map.put("name", "xiaoming");
        atomic1.set(map);
        atomic1.compareAndSet(null, null);
        System.out.println("1:" + atomic1.get());

        String[] s = {"aa", "bb", "cc"};
        AtomicReference atomic2 = new AtomicReference(s);
        System.out.println(atomic2.get());
        atomic2.set(atomic1);
        System.out.println(atomic2.get());
    }

    @Test
    public void test4() {
        String initialReference = "initial value referenced";
        AtomicReference<String> atomicStringReference = new AtomicReference<String>(initialReference);

        String newReference = "new value referenced";
        boolean exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
        System.out.println("exchanged: " + exchanged);

        exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
        System.out.println("exchanged: " + exchanged);
    }


    public static void main(String[] args) {

        String initialRef = "initial value referenced";
        int initialStamp = 0;

        AtomicStampedReference<String> atomicStringReference = new AtomicStampedReference<>(initialRef, initialStamp);
        String newRef = "new value referenced";
        int newStamp = initialStamp + 1;

        boolean exchanged = atomicStringReference.compareAndSet(initialRef, newRef, initialStamp, newStamp);
        System.out.println("exchanged: " + exchanged);  //true

        System.out.println(atomicStringReference.getReference());

        exchanged = atomicStringReference.compareAndSet(initialRef, "new string", newStamp, newStamp + 1);
        System.out.println("exchanged: " + exchanged);  //false

        exchanged = atomicStringReference.compareAndSet(newRef, "new string", initialStamp, newStamp + 1);
        System.out.println("exchanged: " + exchanged);  //false

        exchanged = atomicStringReference.compareAndSet(newRef, "new string", newStamp, newStamp + 1);
        System.out.println("exchanged: " + exchanged);  //true

        DaemonThreadCreateThreadTest.DaemonThread a = new DaemonThreadCreateThreadTest().new DaemonThread("dd");
    }
}
