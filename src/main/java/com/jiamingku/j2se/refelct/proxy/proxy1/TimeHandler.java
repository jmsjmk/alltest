package com.jiamingku.j2se.refelct.proxy.proxy1;

import java.lang.reflect.Method;

/**
 * TimeHandler组合了被代理对象.
 * <p>
 * 但是实际上来还是有好处的.
 * 1.如果用组合，首先的创建类，并且必须实现接口里面的方法，如果接口方法很多的话
 * 你就的全部实现，非常每个都需要写。
 * <p>
 * 2.动态代理解决了上面的所有的麻烦上--原理讲解完毕，官方同样也提供了解决办法
 */
public class TimeHandler implements InvocationHandler {

    private Object target;

    public TimeHandler(Object target) {
        super();
        this.target = target;
    }

    /**
     * @param o 代理对象
     * @param m
     */
    @Override
    public void invoke(Object o, Method m) {
        long start = System.currentTimeMillis();
        System.out.println("starttime::::::::::::::::::::::" + start);
        System.out.println("代理对象==" + o.getClass().getName());
        try {
            // 被代理对象的方法执行
            m.invoke(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("time::::::::::::::::::::::::::" + (end - start));
    }
}
