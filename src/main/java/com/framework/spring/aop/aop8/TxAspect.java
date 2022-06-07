package com.framework.spring.aop.aop8;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * <p>
 * Created by jiamingku on 2018/11/22.
 * <p>
 * around增强是非常强大的,即使是目标方法发生异常-你也可以抓去到，并帮他执行处理
 * 异常出现修改固定的返回值-等
 * <p>
 * 重要：访问目标方法的参数：,可以各种修改。。预留的口子很多.
 */
@Aspect
public class TxAspect {
    @Around("execution(* com.framework.spring.aop.aop1.*.*(..))")
    public Object authority(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("[环绕方法中---]执行目标方法前，模拟事物开始... ");
        Object rvt = null;
        Object[] objects = proceedingJoinPoint.getArgs();
        // 获取连接点的参数长度-如果你把参数长度更改size不一样会出现异常，同样类型不一样也出现异常
        // java.lang.IllegalArgumentException: Expecting 2 arguments to proceed, but was passed 3 arguments
        System.out.println("[环绕方法中---]objects.length = " + objects.length);
        if (objects != null && objects.length > 1) {
            objects[0] = "[环绕方法中---]增强-添加前缀" + objects[0];
        }
        // objects = new Object[3];
        try {
            rvt = proceedingJoinPoint.proceed(objects);
        } catch (Exception e) {

            e.printStackTrace();
        }
        System.out.println("[环绕方法中---]执行方法模拟事物执行关闭。..");
        if (rvt != null && rvt instanceof Integer) {
            rvt = 100000;
        } else {
            rvt = 3;
        }
        return rvt;
    }
}
