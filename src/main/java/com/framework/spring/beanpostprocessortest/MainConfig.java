package com.framework.spring.beanpostprocessortest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangqh
 * @date 2018年4月30日
 */
@Configuration
public class MainConfig {

    /**
     * 方法的名字决定bean 的名字在spring 容器中
     *
     * @return
     */
    @Bean(initMethod = "initUser", destroyMethod = "destroyUser")
    public User2 getUser22222222222221() {
        return new User2();
    }

    @Bean(initMethod = "initUser", destroyMethod = "destroyUser")
    public User1 getUser222222222222212() {
        return new User1();
    }

//    @Bean
//    public MyBeanPostProcessor getMyBeanPostProcessor() {
//        return new MyBeanPostProcessor();
//    }
}

