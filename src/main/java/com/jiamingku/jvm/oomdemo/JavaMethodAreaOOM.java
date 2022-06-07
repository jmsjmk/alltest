package com.jiamingku.jvm.oomdemo;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 原来的永久代，在jdk8中已经去掉，换成了元数据区
 * -XX:MetaspaceSize=5M  -XX:MaxMetaspaceSize=5M
 * 其实概念上面没有啥的，只是显实现方式上面有点变化
 */
public class JavaMethodAreaOOM {
    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOM.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method arg1, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, args);
                }
            });
            OOM oom = (OOM) enhancer.create();
            System.out.println(oom.getClass());
            oom.sayHello("Kevin LUAN");
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class OOM {
        public String sayHello(String str) {
            return "HI " + str;
        }
    }
}  