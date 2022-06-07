package com.framework.spring.aop.aop9;

import com.framework.spring.aop.aop1.HelloImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * Created by jiamingku on 2018/11/23.
 * <p>
 * around > before > after> aferReturning ,测试代码
 * <p>
 * 在执行这个方法:时候会获取很多信息-甚至可以修改参数的参数....
 */
@SuppressWarnings("all")
@Aspect
public class FourAdiviceTest {

    @Around(("execution(* com.framework.spring.aop.aop1.*.*(..))"))
    public Object processTx(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("----------------Around增强: 执行目标方法前，模拟事物开始...--------------- ");
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

    @Before("execution(* com.framework.spring.aop.aop1.*.*(..))")
    public void authority(JoinPoint jp) {
        System.out.println("----------------Before增强: 执行目标方法前，模拟权限进行检查---------------- ");

        String methodName = jp.getSignature().getName();
        System.out.println("Before增强：获取methodName = " + methodName);

        System.out.println("Before增强：目标方法参数为 = " + Arrays.toString(jp.getArgs()));

        System.out.println("Before增强：被织入增强的目标对象=jp.getTarget()" + jp.getTarget());
        System.out.println("Before增强：被织入增强的目标对象=jp.getThis()" + jp.getThis());

        /**
         * 获取-被代理对象--.
         */
        Object o = jp.getTarget();
        Object o1 = jp.getThis();
        System.out.println("jp.getTarget().getClass().getSimpleName():" + jp.getTarget().getClass().getSimpleName());
        System.out.println("jp.getThis().getClass().getSimpleName():" + jp.getThis().getClass().getSimpleName());

        Class cc = o1.getClass().getSuperclass();
        System.out.println("o1.getClass().getSuperclass():" + o1.getClass().getSuperclass());

        if (o instanceof HelloImpl) {
            HelloImpl hello = (HelloImpl) o;
            ((HelloImpl) o).addUser("dd", "d");
            System.out.println(" ==&&&&&&&&&&&&&&&&&&&&= ");
        }

        if (o1 instanceof HelloImpl) {
            System.out.println("hhhhhhhhhhhhhhhhhhhhhhhh" + o1);
        } else {
            // 根据你生成动态代理的方式不同，cglib 就能显示一样.
            System.out.println("虽然toString看着一样但是确实不是一个对象...");
        }

        System.out.println(" o1 = o" + (o1 == o));

        System.out.println("Before增强：获取代理对象=" + jp.getTarget().getClass().getName());
        System.out.println("Before增强：获取代理对象=" + jp.getThis().getClass().getName());

        if (jp.getThis() == jp.getTarget()) {
            System.out.println(" 他两尽然相等！！！！！ ");
        } else {
            System.out.println(" 但是他们是不相同的东西 ");
        }


    }

    @AfterReturning(value = ("execution(* com.framework.spring.aop.aop1.*.*(..))"), returning = "rvt")
    public void log(JoinPoint jp, Object rvt) {
        String msg = "----------------AfterReturning增强:----------------";
        System.out.println(msg + "获取目标方法返回值" + rvt);

        String methodName = jp.getSignature().getName();
        System.out.println(msg + "被织入增强的目标方法为 " + methodName);


        System.out.println(msg + "目标方法参数为 = " + Arrays.toString(jp.getArgs()));
        System.out.println(msg + "目标对象为 = " + jp.getTarget());

    }

    @After(value = ("execution(* com.framework.spring.aop.aop1.*.*(..))"))
    public void release(JoinPoint jp) {
        String msg = "----------------After增强:----------------";
        System.out.println(msg + "模拟方法结束后释放资源.");
        String methodName = jp.getSignature().getName();
        System.out.println(msg + "被织入增强的目标方法为 " + methodName);
        System.out.println(msg + "目标方法参数为 = " + Arrays.toString(jp.getArgs()));
        System.out.println(msg + "目标对象为 = " + jp.getTarget());
        System.out.println(msg + "目标对象为 = " + jp.getThis());
        System.out.println("jp = " + jp);
    }

}
