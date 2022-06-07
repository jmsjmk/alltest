package com.framework.log.log4j.split;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by jiamingku on 2018/11/1.
 */
public class Log4jSplitFileError {
    private static final String BASE = "/Users/jiamingku/IdeaProjects/alltest/src/main/java/com/framework/log/log4j/configdemo/";

    /**
     * @param args
     */
    public static void main(String[] args) {
        PropertyConfigurator.configure(BASE + "log4j.properties");
        Log4jSplitFileError app = new Log4jSplitFileError();
        app.printLog();
    }

    public void printLog() {
        Logger log = Logger.getLogger(Log4jSplitFileSuccess.class.getClass());
        log.info("测试in1111fo");
        log.trace("trace");
        log.debug("测试debug");
        log.error("测试error");

        String s  = "332323 ";
        boolean b = s.contains(" ");
        System.out.println("b = " + b);
    }
}
