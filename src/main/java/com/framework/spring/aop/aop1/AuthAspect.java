package com.framework.spring.aop.aop1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

/**
 * @Before 执行前置增强
 * <p>
 * Created by jiamingku on 2018/11/22.
 */
@Aspect
@Order(2)
public class AuthAspect {
    public static final String prefix = "AuthAspect, Order[1]";

    /**
     * 前置通知
     */
    @Before("execution(* com.framework.spring.aop.aop1.*.*(..))")
    public void before() {
        System.out.println(prefix + "(@Before)前置通知....");
    }

    /**
     * 后置通知
     * returnVal,切点方法执行后的返回值
     */
    @AfterReturning(value = "execution(* com.framework.spring.aop.aop1.*.*(..))", returning = "returnVal")
    public void AfterReturning(Object returnVal) {
        System.out.println(prefix + "(@AfterReturning)后置通知...." + returnVal);
    }


    /**
     * 环绕通知
     *
     * @param joinPoint 可用于执行切点的类
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.framework.spring.aop.aop1.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println(prefix + "(@Around)环绕通知前....");
        Object obj = (Object) joinPoint.proceed();
        System.out.println(prefix + "(@Around)环绕通知后....");
        return obj;
    }

    /**
     * 抛出通知
     *
     * @param e
     */
    @AfterThrowing(value = "execution(* com.framework.spring.aop.aop1.*.*(..))", throwing = "e")
    public void afterThrowable(Throwable e) {
        System.out.println(prefix + "(@AfterThrowing)出现异常:msg=" + e.getMessage());

    }

    /**
     * 无论什么情况下都会执行的方法
     */
    @After(value = "execution(* com.framework.spring.aop.aop1.*.*(..))")
    public void after() {
        System.out.println(prefix + "(@After)最终通知....");
    }
}
