package com.framework.spring.springcoreinterface.factorybean;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("dddd")
public class BeanNameServiceImpl implements BeanNameService {
    @Override
    public void testFactoryBean() {
        System.out.println("也注入了....");
    }

    @Resource
    FactoryBeanService factoryBeanService;

    public void test() {
        System.out.println("factoryBeanService.hashCode() = " + factoryBeanService.hashCode());
        factoryBeanService.testFactoryBean();
    }
}
