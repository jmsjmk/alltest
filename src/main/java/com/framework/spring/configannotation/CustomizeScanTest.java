package com.framework.spring.configannotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomizeScanTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(CustomizeScanTest.class);
        annotationConfigApplicationContext.refresh();

        /**
         * spring 容器启动之后-
         * 1.进行后处理器的处理。--可以为你编辑注解的类进行代理了什么的生成，
         *
         * 2.自定义注解还有一种用法就是定义在方法上面 aop 在执行的时候获取注解信息进行拦截操作。
         */

        ScanClass1 injectClass = annotationConfigApplicationContext.getBean(ScanClass1.class);
        injectClass.print();
    }


    @Bean
    public BeanScannerConfigurer getMyBeanPostProcessor() {
        return new BeanScannerConfigurer();
    }


}