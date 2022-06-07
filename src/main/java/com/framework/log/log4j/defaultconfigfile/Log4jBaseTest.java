package com.framework.log.log4j.defaultconfigfile;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiamingku on 2020/5/8.
 * +jvm参数：log4j.debug
 * <p>
 * log4j.java 你使用的时候最开始,需要LogManager 启动类的静态方法
 * -- log4j: Using URL [file:/Users/jiamingku/IdeaProjects/alltest/target/classes/log4j.properties] for automatic log4j configuration.
 * 会可以查看出来，log4j最中使用的是那个配置文件
 */
public class Log4jBaseTest {
    private static final Logger logger = Logger.getLogger(Log4jBaseTest.class);

    public static void main(String[] args) {
        System.out.println("Log4jBaseTest.class = " + Log4jBaseTest.class);
        logger.info("ttttttttttttttttttttttttttttttt");
        System.out.println("logger.getName() = " + logger.getName());
        System.out.println("logger.getEffectiveLevel() = " + logger.getEffectiveLevel());
        System.out.println("logger.getResourceBundle() = " + logger.getResourceBundle());
        System.out.println("logger.getLoggerRepository().toString() = " + logger.getLoggerRepository().toString());
        System.out.println("logger.getAllAppenders() = " + logger.getAllAppenders());
        Map<String, String> map = new HashMap<>();
        map.put("key1", "valu1");
        System.out.println("map = " + map);
        logger.info(JSON.toJSONString(map));
    }

    @Test
    public void mt() {
        PropertyConfigurator.configure("/Users/jiamingku/IdeaProjects/alltest/src/main/java/com/framework/log/log4j/log4jTest.properties");
        System.out.println(Log4jBaseTest.class);
        System.out.println(Log4jBaseTest.class.getClasses());

        Logger log = Logger.getLogger(Log4jBaseTest.class.getName() + "1is3sdfsdfs");
        String s = log.getName();
        System.out.println("s = " + s);
        // ----及时logger不存在也能打印出来
        log.info("-----------------------------");
        for (int i = 0; i < 1; i++) {
            log.info("ddddd");
        }

        if (log.isDebugEnabled()) {
            System.out.println("is debug enabled");
        }

        if (true) {
            log.info("dd", new NullPointerException("d"));
        }
    }

    @Before
    public void testBefort() {
        PropertyConfigurator.configure("/Users/jiamingku/IdeaProjects/alltest/src/main/java/com/framework/log/log4j/log4jTest.properties");
    }

    /**
     * 日志记录器不存在就获取父, 其实就是获取不到就用父的
     */
    @Test
    public void testFormate() {
        Logger log = Logger.getLogger("testFormate");
        log.info("ddd");
        System.out.println(" ============= ");
        log.info("dddddd");
    }

    @Test
    public void testPrintLevel() {
        Logger log = Logger.getLogger("t11");
        log.debug("debug");
        log.info("info");
        log.error("error");
    }

    /**
     * * 打印父记录器
     * <p>
     * 打印直接的的父logger，没有定义包级别的直接打印root，如果有定义包级别的，父记录器就是包
     */
    @Test
    public void getParent() {
        System.out.println("Log4jParentTest.class.getClassLoader().getResource(\"/\").getPath() = " + Log4jBaseTest.class.getClassLoader().getResource("").getPath());
        System.out.println(" ============= ");

        Logger log = Logger.getLogger(Log4jBaseTest.class);
        Logger log1 = Logger.getLogger("tt");


        String name = log.getParent().getName();
        String logName = log.getName();
        System.out.println("logName = " + logName);
        System.out.println("getParent = " + name);

        String name1 = log1.getParent().getName();
        String logName1 = log1.getName();
        System.out.println("logName1 = " + logName1);
        System.out.println("getParent = " + name1);

        log1.info("dddd");
    }
}
