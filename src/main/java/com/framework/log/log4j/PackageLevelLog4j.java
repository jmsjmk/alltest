package com.framework.log.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by jiamingku on 2018/10/31.
 */
public class PackageLevelLog4j {

    private static Logger Log = Logger.getLogger(PackageLevelLog4j.class);
    private static Logger packageLog = Logger.getLogger("com.framework.log.log4j");

    @Before
    public void testBefort() {
        PropertyConfigurator.configure("/Users/jiamingku/IdeaProjects/alltest/src/main/java/com/framework/log/log4j/log4jPackage.properties");
    }


    @Test
    public void extendLevel() {
        Log.info("info");
        Log.debug("debug");
        Log.error("error");
    }


    public static void main(String[] args) {

        Log.info("info");
        // 父记录器是 包级别的
        String name = Log.getParent().getName();
        String logName = Log.getName();
        System.out.println("logName = " + logName);
        System.out.println("name = " + name);

        // root logger父记录器
        String name1 = packageLog.getParent().getName();
        String logName1= packageLog.getName();
        System.out.println("logName = " + logName1);
        System.out.println("name = " + name1);
    }
}
