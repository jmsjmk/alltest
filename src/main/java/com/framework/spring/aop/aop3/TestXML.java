package com.framework.spring.aop.aop3;

import com.framework.spring.aop.aop1.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jiamingku on 2018/11/22.
 * 基于xml配置, 多个切面===先后顺序的问题,切面配置方法的顺序有点问题。(around,before顺序颠倒执行顺序不一样)
 * <p>
 * 1.单独一个aspect的时候 https://blog.csdn.net/rainbow702/article/details/52185827
 * 主要是看顺序，这是一种基于xml配置的方式。
 * <p>
 * 2.基于xml的配置完全可以用类来进行替代aop9里面
 */
@SuppressWarnings("all")
public class TestXML {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aopXML.xml");
        /**
         * cglib生成的代理类:::
         */
        Hello o = (Hello) ctx.getBean("hello");
        System.out.println("o.getClass().getName() = " + o.getClass().getName());
        o.addUser("name", "pass");
        System.out.println();
    }
}
