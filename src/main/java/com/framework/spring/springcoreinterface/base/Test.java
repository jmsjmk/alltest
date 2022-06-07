package com.framework.spring.springcoreinterface.base;

import com.framework.spring.springcoreinterface.factorybean.FactoryBeanService;
import org.apache.http.io.SessionOutputBuffer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jiamingku on 2020/6/22.
 */
public class Test {
//    private static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
//        logger.info("dd");
        ClassPathXmlApplicationContext cac = new ClassPathXmlApplicationContext("classpath:base.xml");

        /**
         * 配置文件中,没有配置这个bean, 但是重容器中要就会调用
         * 当你像容器中要这个bean的时候时候不会调用getObject方法的
         */
        Object beanService = cac.getBean("department");

        System.out.println("beanService = " + beanService.getClass().getSimpleName());
    }
}
