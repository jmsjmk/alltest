package com.framework.spring.springcoreinterface.factorybean;

import org.springframework.beans.factory.FactoryBean;

public class FactoryBeanLearn implements FactoryBean {

    public FactoryBeanLearn() {
        System.out.println("spring 调用了 无参数 构造器.");
    }

    private String s;

    public String getS() {

        return s;
    }

    public void setS(String s) {
        System.out.println(" Spring 调用了 set方法.");
        this.s = s;
    }

    @Override
    public Object getObject() throws Exception {
        System.out.println(" ********************************************Spring 调用了 getObject. 方法......");
        //这个Bean是我们自己new的，这里我们就可以控制Bean的创建过程了
        FactoryBeanServiceImpl factoryBeanService =  new FactoryBeanServiceImpl();
        System.out.println(" ******************************************** factoryBeanService.hashCode() = " + factoryBeanService.hashCode());
        return factoryBeanService;
    }

    @Override
    public Class<?> getObjectType() {
        return FactoryBeanService.class;
    }

    /**
     * 是否单例
     * @return
     */
    @Override
    public boolean isSingleton() {
        return false;
    }
}
//接口

