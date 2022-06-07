package com.framework.spring.springcoreinterface.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.Resource;

//@Component

/**
 * 字符串肯定会给你转换过来.resource属性.
 */
public class SpringToResourceBean {

    public SpringToResourceBean() {
        System.out.println("spring 调用了 无参数 构造器.");
    }

    private Resource s;

    public Resource getS() {

        return s;
    }

    public void setS(Resource s) {
        System.out.println(" Spring 调用了 set方法.");
        this.s = s;
    }
}
//接口

