package com.framework.spring.aop.aop0;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.util.ClassUtils;

/**
 * Created by jiamingku on 2018/11/28.
 * <p>
 * 采用了advisor的配置方式
 */
public class MyIntercepter implements MethodInterceptor, InitializingBean, ApplicationEventPublisherAware {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("目标方法拦截..................以下可以获取的参数:");
        Class<?> clazz = invocation.getThis().getClass();
        System.out.println("被代理对象的类型信息clazz = " + clazz);

        System.out.println("MethodInvocation真实实现类 = " + invocation.getClass().getSimpleName());
        //
        String statementId = invocation.getMethod().getName();
        System.out.println("被拦截的方法名: = " + statementId);
        String simpleName = invocation.getThis().getClass().getSimpleName();
        System.out.println("被拦截的实现类: = " + simpleName);

        ReflectiveMethodInvocation reflectiveMethodInvocation = (ReflectiveMethodInvocation) invocation;
        System.out.println("代理--的实现类: = " + reflectiveMethodInvocation.getProxy().getClass().getName());

        String mapperNamespace = null;
        if (true) {
            //获取实际调用mapper erp
            Class<?>[] targetInterfaces = ClassUtils.getAllInterfacesForClass(clazz, clazz.getClassLoader());
            //dao完整名称，同mapping.xml中的namespace
            mapperNamespace = targetInterfaces[0].getName();
            System.out.println("mapperNamespace = " + mapperNamespace);
        } else {
            mapperNamespace = invocation.getMethod().getDeclaringClass().getName();

            System.out.println("mapperNamespace = " + mapperNamespace);
        }

        // 决定调用方法---
        return invocation.proceed();
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {

    }
}
