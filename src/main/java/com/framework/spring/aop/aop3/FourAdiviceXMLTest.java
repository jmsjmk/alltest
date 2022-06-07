package com.framework.spring.aop.aop3;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;

/**
 * Created by jiamingku on 2018/11/23.
 */
@SuppressWarnings("all")
public class FourAdiviceXMLTest {

    public Object processTx(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around增强: 执行目标方法前，模拟事物开始... ");
        Object[] objects = joinPoint.getArgs();
        if (objects != null && objects.length > 0 && objects[0].getClass() == String.class) {
            objects[0] = "增强-添加前缀" + objects[0];
        }
        Object rvt = joinPoint.proceed(objects);
        if (rvt != null && rvt instanceof Integer) {
            rvt = 100000;
        }
        return rvt;
    }

    public void authority(JoinPoint jp) {
        System.out.println("Before增强: 执行目标方法前，模拟权限进行检查======此方法只是拦截---获取一些参数---不会获取方法的返回值 ");

        String methodName = jp.getSignature().getName();
        System.out.println("Before增强：获取methodName = " + methodName);

        System.out.println("Before增强：目标方法参数为 = " + Arrays.toString(jp.getArgs()));

        System.out.println("Before增强：被织入增强的目标对象=" + jp.getTarget());
        System.out.println("Before增强：被织入增强的目标对象=" + jp.getThis());

        System.out.println("Before增强：获取被被被被被被被被被被被代理对象=" + jp.getTarget().getClass().getName());
        System.out.println("Before增强：获取代理对象=" + jp.getThis().getClass().getName());
    }

    public void log(JoinPoint jp, Object rvt) {
        String msg = "AfterReturning增强:";
        System.out.println(msg + "获取目标方法返回值" + rvt);

        String methodName = jp.getSignature().getName();
        System.out.println(msg + "被织入增强的目标方法为 " + methodName);

        System.out.println(msg + "目标方法参数为 = " + Arrays.toString(jp.getArgs()));
        System.out.println(msg + "目标对象为 = " + jp.getTarget());

    }

    public void release(JoinPoint jp) {
        String msg = "After增强:";
        System.out.println(msg + "模拟方法结束后释放资源.");
        String methodName = jp.getSignature().getName();
        System.out.println(msg + "被织入增强的目标方法为 " + methodName);
        System.out.println(msg + "目标方法参数为 = " + Arrays.toString(jp.getArgs()));
        System.out.println(msg + "目标对象为 = " + jp.getTarget());
        System.out.println(msg + "目标对象为 = " + jp.getThis());
        System.out.println("jp = " + jp);
    }
}
