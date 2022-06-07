package com.jiamingku.j2se.basetype.box;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiamingku on 2019/6/10.
 */
public class BoxTest {

    @Test
    public void testBox() {
        // 装箱,valueOf
        Integer i = 100;
        // 拆箱 intValue
        int b = i;
        System.out.println("Integer.MIN_VALUE = " + Integer.MIN_VALUE);
    }

    /**
     * -128 -127 之间的包装类型== 都是true
     * <p>
     * 自动装箱：就是valueof
     * 自动拆箱：就是intValue
     * {@link Integer#valueOf(int)}
     */
    @Test
    public void testInteger() {
        Integer i = 123;
        Integer b = 123;
        System.out.println("i ==  = " + (i == b));
        i = 1233;
        b = 1233;
        System.out.println("i ==  = " + (i == b));
    }

    /**
     * 创建对象的话 就明显不是同一个对象了。
     * 如果明显的构造器就不行了
     */
    @Test
    public void testNew() {
        Integer a = new Integer(100);
        Integer b = new Integer(100);
        System.out.println("(a ==b ) = " + (a == b));
    }

    @Test
    public void testLong() {
        Long a = 12L;
        Long b = 12L;
        System.out.println("(a==b) = " + (a == b));

        a = 1212121L;
        b = 1212121L;
        System.out.println("(a==b) = " + (a == b));
    }

    /**
     * HashMap的get方法的理解
     */
    @Test
    public void testKeng() {
        Long l1 = new Long(230);
        Long l2 = new Long(230);
        System.out.println(l1 == l2);// false
        Map<Long, String> map = new HashMap<>();
        map.put(l1, "nihao");
        String val = map.get(l2);
        /**
         * // long 的hashcod 与equals有关系，确保命中记录
         * // 输出结果nihao 虽然key 不相等，但是还可以获取到
         */
        System.out.println("val:" + val);

    }

    @Test
    public void testKey() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "1");
        map.put(2, "2");
        String v = map.get(Integer.valueOf(2));
        System.out.println("v = " + v);

        map.put(10000, "10000");
        v = map.get(Integer.valueOf(10000));
        System.out.println("v = " + v);
    }

    /**
     * 拆箱时候进行intvalue，这时候出现null出现空指针异常
     */
    @Test
    public void testException() {
        Integer i = null;
        Test1 t = new Test1(i);
    }

    class Test1 {
        int a;

        public Test1(int a) {
            this.a = a;
        }
    }

    /**
     * integer 是不可以变的类。
     * 这个的解释就是 传递到change方法，i=2000；相当于进行了装箱，也就是 valueof ，
     * <p>
     * 这时候i 指向的是另外一个对象，所以呢，没啥关系
     */
    @Test
    public void noChange() {
        Integer i = 100;
        change(i);
        System.out.println("i = " + i);
    }

    public void change(Integer i) {
        i = 2000;
        System.out.println("i = " + i);
    }

    @Test
    public void main1() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        BoxTest boxTest = new BoxTest();
        boxTest.testChange(list);
        System.out.println("boxTest = " + list);

        boxTest.testChange1(list);
        System.out.println(list);
    }

    /**
     * 这个跟自动装箱 是一个道理。
     *
     * @param list
     */
    public void testChange(List<String> list) {
        list = new ArrayList<>();
        list.add("11");
        System.out.println("list = " + list);
    }

    public void testChange1(List<String> list) {
        list.add("11");
        System.out.println("list = " + list);

        Integer i = new Integer(110);

    }
}
