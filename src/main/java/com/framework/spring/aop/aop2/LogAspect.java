package com.framework.spring.aop.aop2;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

/**
 * @Before 执行前置增强
 * <p>
 * Created by jiamingku on 2018/11/22.
 */
@Aspect
@Order(1)
public class LogAspect {
    // 1.设置了returning 的话 如果返回值得类型不符合的话，不会进行后置增强,类型不相等也不会进行后置增强

    // 2.如果被代理对象方法抛出异常，后置增强会停止。他指会等待正确执行之后才可以执行

    // 3.执行方法不能更改返回值
    @AfterReturning(pointcut = "execution(* com.framework.spring.aop.aop1.*.*(..))", returning = "rvt")
    public void authority(Object rvt) {
        System.out.println("-----------------------AOP-模拟日志--------------------返回结果[" + rvt + "]---- ");
    }
}
