package com.framework.spring.datasource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jiamingku on 2018/11/22.
 https://blog.csdn.net/u013034378/article/details/81455513

 https://blog.csdn.net/u013034378/article/details/81661706


 */
@SuppressWarnings("all")
public class Test {
    public static void main(String[] args) {
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("cyp-datasource-context.xml");
//
//
//        SqlSessionFactory o = (DefaultSqlSessionFactory) ctx.getBean("sqlSessionFactory");
//        System.out.println("o = " + o);
//        System.out.println("hw1.getClass().getSimpleName() = " + o.getClass().getName());
//
//
//        MultiDataSource o1 = (MultiDataSource) ctx.getBean("multiDataSource");
//
//        o1.setDataSourceKey("slave");
//
//
//        SqlSession sqlSession = o.openSession();
//
//        sqlSession.getConnection();
//
//        User u = new User();
//        u.setName("name");
//        u.setAge(1);
//        int result = sqlSession.update("com.framework.spring.transaction.t1.UserMapper.insert", u);
//
//        sqlSession.commit();
//        System.out.println("result = " + result);
        System.out.println("ddd");

    }
}
