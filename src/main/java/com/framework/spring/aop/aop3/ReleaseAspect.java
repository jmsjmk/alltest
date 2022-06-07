package com.framework.spring.aop.aop3;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

/**
 * @Before 执行前置增强
 * <p>
 * Created by jiamingku on 2018/11/22.
 */
@Aspect
@Order(2)
public class ReleaseAspect {
    // throwing 的话 如果返回值得类型不符合的话，不会进行后置增强
    @After("execution(* com.framework.spring.aop.aop1.*.*(..))")
    public void authority() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(".......................释放资源。。。。。。");
    }
}
