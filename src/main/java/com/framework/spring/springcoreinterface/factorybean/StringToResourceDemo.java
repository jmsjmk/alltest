package com.framework.spring.springcoreinterface.factorybean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jiamingku on 2020/6/22.
 */
public class StringToResourceDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cac = new ClassPathXmlApplicationContext("classpath:Factorybeans1.xml");
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
