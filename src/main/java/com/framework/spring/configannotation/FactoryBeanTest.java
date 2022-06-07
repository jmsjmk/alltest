package com.framework.spring.configannotation;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;

public  class FactoryBeanTest<T> implements InitializingBean, FactoryBean<T> {
      private String innerClassNameTest1ss;


    public String getInnerClassNameTest1ss() {
        return innerClassNameTest1ss;
    }

    public void setInnerClassNameTest1ss(String innerClassNameTest1ss) {
        this.innerClassNameTest1ss = innerClassNameTest1ss;
    }

    public T getObject() throws Exception {
          Class innerClass = Class.forName(innerClassNameTest1ss);
          System.out.println("innerClass = " + innerClassNameTest1ss);
          System.out.println("innerClass = " + innerClassNameTest1ss);
          System.out.println("innerClass = " + innerClassNameTest1ss);
          System.out.println("innerClass = " + innerClassNameTest1ss);
          System.out.println("innerClass = " + innerClassNameTest1ss);
          if (innerClass.isInterface()) {

              System.out.println(" ====接口 " );
              return (T) InterfaceProxy.newInstance(innerClass);

          } else {
              System.out.println(" ====非。。。接口 " );
              Enhancer enhancer = new Enhancer();
              enhancer.setSuperclass(innerClass);
              enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
              enhancer.setCallback(new MethodInterceptorImpl());
              return (T) enhancer.create();
          }
      }
      public Class<?> getObjectType() {
          try {
                return Class.forName(innerClassNameTest1ss);
          } catch (ClassNotFoundException e) {
                e.printStackTrace();
          }
          return null;
      }
      public boolean isSingleton() {
          return true;
      }
      public void afterPropertiesSet() throws Exception {
      }
}
