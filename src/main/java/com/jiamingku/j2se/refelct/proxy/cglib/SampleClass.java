package com.jiamingku.j2se.refelct.proxy.cglib;

import net.sf.cglib.proxy.*;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

public class SampleClass {
    public void test() {
        System.out.println("hello world");
    }

    public String test(String input) {
        System.out.println(" .....");
        System.out.println(" .....");
        System.out.println(" .....");
        return "hello world";
    }

    /**
     * 方法拦截.
     *
     * @param args
     */
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before method run...");
                Object result = proxy.invokeSuper(obj, args);
                System.out.println("after method run...");
                return result;
            }
        });
        SampleClass sample = (SampleClass) enhancer.create();
        String simpleName = sample.getClass().getSimpleName();
        System.out.println("simpleName = " + simpleName);
        System.out.println("getSuperclass = " + simpleName.getClass().getSuperclass());
        sample.test();
    }

    /**
     * 固定的返回值
     */
    @Test
    public void testFixedValue() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return 1;
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        System.out.println(proxy.test(null)); //拦截test，输出Hello cglib
        System.out.println(proxy.toString());
        System.out.println(proxy.getClass());
//        System.out.println( (proxy.hashCode() ));
    }

    /**
     * 回掉的拦截--- 父类的方法都可以拦截.
     *
     * @throws Exception
     */
    @Test
    public void testInvocationHandler() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method.getName() + "   method.getDeclaringClass() :" + method.getDeclaringClass());
                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return "hello cglib";
                } else {
                    throw new RuntimeException("Do not know what to do");
                }
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        Assert.assertEquals("hello cglib", proxy.test(null));
        Assert.assertNotEquals("Hello cglib", proxy.toString());
    }


    @Test
    public void testCallbackFilter() throws Exception {
        Enhancer enhancer = new Enhancer();
        CallbackHelper callbackHelper = new CallbackHelper(SampleClass.class, new Class[0]) {
            @Override
            protected Object getCallback(Method method) {
                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return new FixedValue() {
                        @Override
                        public Object loadObject() throws Exception {
                            return "Hello cglib";
                        }
                    };
                } else {
                    return NoOp.INSTANCE;
                }
            }
        };
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallbackFilter(callbackHelper);
        enhancer.setCallbacks(callbackHelper.getCallbacks());
        SampleClass proxy = (SampleClass) enhancer.create();
        Assert.assertEquals("Hello cglib", proxy.test(null));
        Assert.assertNotEquals("Hello cglib", proxy.toString());
        System.out.println(proxy.hashCode());
    }

}
