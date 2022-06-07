package com.framework.spring.aop.aop0;

import com.framework.spring.aop.aop0.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jiamingku on 2018/11/22.
 */
@SuppressWarnings("all")
public class Test {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop0.xml");

        System.out.println("设置保存代理类文件(true)");
        Hello o = (Hello) ctx.getBean("hello");
        System.out.println("spring容器中的bean:" + o);
        System.out.println("spring容器中的代理" + o.getClass().getName());
        System.out.println();
        System.out.println();
        System.out.println();
        o.addUser("name", "pass");
        System.out.println();

    }
}
