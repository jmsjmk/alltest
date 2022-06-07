package com.framework.spring.transaction.t1;

import com.framework.spring.transaction.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jiamingku on 2018/11/22.
 */
@SuppressWarnings("all")
public class TestTranscationByXml {
    public static void main(String[] args) throws Exception {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("t2.xml");
        UserService userService = (UserService) ctx.getBean("userServiceImpl");
        User u = new User();
        u.setName("u1");
        u.setAge(1);
        int a = userService.insertUser(u);
        System.out.println("a = " + a);
    }
}