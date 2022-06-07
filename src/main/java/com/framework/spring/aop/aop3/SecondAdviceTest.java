package com.framework.spring.aop.aop3;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by jiamingku on 2018/11/23.
 */
public class SecondAdviceTest {
    public void auth(JoinPoint joinpoint) {
//        System.out.println("目标参数方法 aa = " + aa);
        System.out.println("auth  1号,Before增强.. =模拟权限检查 ");


        // 在拦截的时候可以直接调用目标方法
        // ProceedingJoinPoint is only supported for around advice
//        try {
//            joinpoint.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
    }


    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("around    1号,Before增强.. =模拟权限检查 ");
        Object o = null;

        // 在拦截的时候可以直接调用目标方法
        // ProceedingJoinPoint is only supported for around advice
        try {
            o = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return o;
    }
}
