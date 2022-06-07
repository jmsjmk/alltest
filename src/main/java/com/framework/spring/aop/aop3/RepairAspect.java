package com.framework.spring.aop.aop3;

import org.aspectj.lang.annotation.AfterThrowing;

/**
 * @Before 执行前置增强
 *
 * Created by jiamingku on 2018/11/22.
 */
public class RepairAspect {
    // 1.throwing 的话 如果返回值得类型不符合的话，不会进行后置增强
    // 2.就是对异常进行后续处理，也不会阻止程序的停止，他不能完全处理这个异常。
    // 3.观察这些注解,其实都是定义连接点。符合条件的 连接点就编程了切入点
    @AfterThrowing(pointcut = "execution(* com.framework.spring.aop.aop1.*.*(..))", throwing = "rvt")
    public void authority(Throwable rvt) {
        System.out.println("模拟异常. ");
        try {
            throw rvt;
        }catch (Throwable e) {
            System.out.println(" =================== " );
        }
    }
}
