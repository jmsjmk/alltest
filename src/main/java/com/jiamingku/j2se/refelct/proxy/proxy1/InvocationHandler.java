package com.jiamingku.j2se.refelct.proxy.proxy1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 调用某个对象的某个方法
 *
 * 针对于本例子中 ，Object 就相当于当前对象this,代理对象
 *
 * 设计模型，将被代理对象放入到了一个handler里面
 *
 *  h.invoke(this, md);" + rt +
 *
 */
public interface InvocationHandler {
	void invoke(Object o, Method m) throws InvocationTargetException, IllegalAccessException;
}
