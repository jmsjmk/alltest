package com.framework.spring.aop.shiwushibai;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ServiceConfigAspect {

    /**
     * 定义切入点函数
     */
    @Pointcut("execution(* com.framework.spring.aop.shiwushibai.*.test*(..))")
    void pointcut() {
    }

    /**
     * 使用@Before声明前置通知
     *
     * @param thisJoinPoint
     */
    @Before("pointcut()")
    public void beforeExecute(JoinPoint thisJoinPoint) {
        System.out.println("before ...");
    }

    /**
     * 使用@After 注解声明后置通知
     *
     * @param thisJoinPoint
     */
    @After("pointcut()")
    public void afterExecute(JoinPoint thisJoinPoint) {
        System.out.println("end......");
    }
}

