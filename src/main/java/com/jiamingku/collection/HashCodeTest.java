package com.jiamingku.collection;

import org.junit.Test;

/**
 * Created by jiamingku on 2019/7/20.
 * <p>
 * 并不是对象的内存地址，而是利用hash算法，对对象实例的一种描述符（或者说对象存储位置的hash算法映射）——对象实例的哈希码。
 * object 的hashcode就是一个本地方法。返回内存对象的地址映射
 */
public class HashCodeTest {

    /**
     * jdk 生成hashcode有几种策略
     * 1。有一种是根据内存地址来进行的, 但是java对象每次运行的hashcode相同，说明他不是根绝内存地址来进行生成的
     * 2。系统默认的生成hashcode的代码==System.identityHashCode(o);
     */
    @Test
    public void testHashCodeAndToString() {
        Object o = new Object();
        System.out.println("o.hashCode() = " + o.hashCode());
        System.out.println("o = " + o.toString());
        /**
         * o.hashCode() = 787604730
         o = java.lang.Object@2ef1e4fa
         */
        String s = Integer.toHexString(110718392);
        System.out.println("s = " + s);

        int l = System.identityHashCode(o);
        System.out.println(l);
    }

    @Test
    public void test1() {

    }
}
