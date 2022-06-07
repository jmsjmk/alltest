package com.jiamingku.j2se.refelct.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 真正使用的时候，就这个么一个东西，动态代理就是固定的写法
 * jdk的记住就行了
 */
public class MyInvocationHandler implements InvocationHandler {

    // 目标对象(被代理对象)
    private Object target;

    /**
     * 构造方法
     *
     * @param target 目标对象
     */
    public MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }


    /**
     * 执行目标对象的方法
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("(生成的代理类::)MyInvocationHandler::proxy.getClass().getSimpleName() = " + proxy.getClass().getSimpleName());
        // 在目标对象的方法执行之前简单的打印一下
        System.out.println("------------------before------------------");

        // 执行目标对象的方法  
        Object result = method.invoke(target, args);

        // 在目标对象的方法执行之后简单的打印一下
        System.out.println("-------------------after------------------");

        return result;
    }

    /**
     * 获取目标对象的代理对象
     *
     * @return 代理对象
     */
    public Object getProxy() {

        /**
         * 生成多个接口的类:::::
         */
        Class<?>[] a = target.getClass().getInterfaces();
        List<Class<?>> classes = new ArrayList<>();
        classes.addAll(Arrays.asList(a));
//        boolean add = classes.add(UserService1.class);
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), classes.toArray(new Class[0]), this);
    }
}  