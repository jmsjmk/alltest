package com.jiamingku.j2se.refelct.proxy.jdkproxy;

import org.junit.Test;

/**
 * Created by jiamingku on 16/11/8.
 */
public class JdkProxyTest {

    /**
     * 使用jdk的动态代理很简单
     * <p>
     * 1.必须是实现接口的类, UserService接口(可以生成多个接口)
     * <p>
     * 2.必须实现InvocationHandler，里面有被代理对象
     * <p>
     * 3.InvocationHandler的实现 获取代理对象，执行
     *
     * @param args
     */
    public static void main(String[] args) {

        String value = System.getProperty("sun.misc.ProxyGenerator.saveGeneratedFile");
        System.out.println("动态代理默认生成文件的值: value = " + value + "    输出文件的路径::::::::" + System.getProperty("user.dir"));

        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        // 实例化InvocationHandler,包含被代理对象
        UserService userService = new UserServiceImpl();
        MyInvocationHandler invocationHandler = new MyInvocationHandler(userService);

        UserService proxy = (UserService) invocationHandler.getProxy();
        proxy.equals(null);
    }


    @Test
    public void testProxyAndProxy() {
        String value = System.getProperty("sun.misc.ProxyGenerator.saveGeneratedFile");
        System.out.println("动态代理默认生成文件的值: value = " + value);
        System.out.println("输出文件的路径::::::::" + System.getProperty("user.dir"));

        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        // 实例化InvocationHandler,包含被代理对象
        UserService userService = new UserServiceImpl();
        MyInvocationHandler invocationHandler = new MyInvocationHandler(userService);

        // 根据目标对象生成代理对象
        UserService proxy = (UserService) invocationHandler.getProxy();

        // 代理又创建代理
        MyInvocationHandler invocationHandler1 = new MyInvocationHandler(proxy);
        UserService proxy1 = (UserService) invocationHandler1.getProxy();
        // 调用代理对象的方法
//        proxy1.add();
        proxy1.delete();
    }

    @Test
    public void testProxyAndProxy1() {
        String value = System.getProperty("sun.misc.ProxyGenerator.saveGeneratedFile");
        System.out.println("动态代理默认生成文件的值: value = " + value);
        System.out.println("输出文件的路径::::::::" + System.getProperty("user.dir"));

        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        // 实例化InvocationHandler,包含被代理对象
        UserService userService = new UserServiceImpl();
        MyInvocationHandler invocationHandler = new MyInvocationHandler(userService);

        // 根据目标对象生成代理对象
        UserService proxy = (UserService) invocationHandler.getProxy();

        // 代理又创建代理
        MyInvocationHandler invocationHandler1 = new MyInvocationHandler(proxy);
        UserService proxy1 = (UserService) invocationHandler1.getProxy();
        // 调用代理对象的方法
//        proxy1.add();
        proxy1.delete();
    }
}
