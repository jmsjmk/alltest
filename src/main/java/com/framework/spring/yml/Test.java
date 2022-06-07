package com.framework.spring.yml;

/**
 * Created by jiamingku on 2020/4/6.
 */

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("all")
public class Test {
    public static void main(String[] args) throws Exception {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("yml.xml");

//        DataSource o = (DataSource) ctx.getBean("oasMysqlDataSource");
//
//        boolean b = o.getConnection().getAutoCommit();
//        System.out.println("b = " + b);

        Map<String, Object> userService = (Map<String, Object>) ctx.getBean("databasesMap");
        for (Map.Entry<String, Object> entry : userService.entrySet()) {
            System.out.println("key.class== " + entry.getKey().getClass().getSimpleName() + "    value.calss== " + entry.getValue().getClass().getSimpleName());
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());


            System.out.println();
            System.out.println();
            System.out.println();

            LinkedHashMap<Object, Object> l = (LinkedHashMap) entry.getValue();
            for (Map.Entry<Object, Object> ee : l.entrySet()) {
                System.out.println("ee.getKey() = " + ee.getKey());
                System.out.println("ee.getValue() = " + ee.getValue());
                System.out.println(ee.getValue().getClass().getSimpleName());

                LinkedHashMap<Object, Object> l1 = (LinkedHashMap) ee.getValue();

                for (Map.Entry<Object, Object> ee3 : l1.entrySet()) {
                    System.out.println("ee3.getKey() = " + ee3.getKey());
                    System.out.println("ee3.getValue() = " + ee3.getValue());
                    System.out.println(ee3.getValue().getClass().getSimpleName());
                }
            }
            System.out.println(" ====== ");
            System.out.println(" ====== ");
            System.out.println(" ====== ");
            System.out.println(" ====== ");
            System.out.println(" ====== ");
            System.out.println(" ====== ");




        }
    }
}