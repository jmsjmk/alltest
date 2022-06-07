package com.framework.spring.scope;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 就是获取这个bean的时候，每次都给你新的.
 */
public class ScopeTestDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cac = new ClassPathXmlApplicationContext("classpath:Factorybeans11.xml");

        /**
         * 获取-- 对应的真实bean的时候---
         * 要某一种类型的bean的时候，会找对应的工厂，来进行创建 返回.
         */
        System.out.println(cac.getBean("factory").hashCode());
        System.out.println(cac.getBean("factory").hashCode());
        System.out.println(cac.getBean("factory").hashCode());
        System.out.println(cac.getBean("factory").hashCode());
    }

    @SuppressWarnings("ALL")
    public void test() {
        ClassPathXmlApplicationContext cac = new ClassPathXmlApplicationContext("classpath:Factorybeans.xml");
        String[] names = cac.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("name:" + name);
        }
        System.out.println("容器中又多少bean::::" + cac.getBeanDefinitionCount());
        /**
         * 配置文件中,没有配置这个bean, 但是重容器中要就会调用
         * 当你像容器中要这个bean的时候时候不会调用getObject方法的
         */
        FactoryBeanService beanService = cac.getBean(FactoryBeanService.class);
        beanService.testFactoryBean();
        System.out.println("--------------");
        System.out.println("--------------");

        for (String name : names) {
            System.out.println("name:" + name);
        }
        System.out.println("容器中又多少bean::::" + cac.getBeanDefinitionCount());
    }
}
