package com.framework.log.log4j.split;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by jiamingku on 2018/11/1.
 */
public class Log4jSplitFileSuccess {

    public static void main(String[] args) {
        PropertyConfigurator.configure("/Users/jiamingku/IdeaProjects/alltest/src/main/java/com/jiamingku/log/log4j/split/log4j-success.properties");
        Log4jSplitFileSuccess log4jSplitFileSuccess = new  Log4jSplitFileSuccess();
        log4jSplitFileSuccess.printLog();
    }
    /**
     log4j.appender.info.filter.a=org.apache.log4j.varia.LevelMatchFilter
     log4j.appender.into.filter.a.LevelToMatch=INFO
     log4j.appender.into.filter.a.AcceptOnMatch=true

     log4j.appender.info.filter.b=org.apache.log4j.varia.LevelMatchFilter
     log4j.appender.info.filter.b.LevelToMatch=ERROR
     log4j.appender.info.filter.b.AcceptOnMatch=false

     ====================

     #log4j.appender.info.filter.a=org.apache.log4j.varia.LevelRangeFilter
     #log4j.appender.into.filter.a.LevelMin=INFO
     #log4j.appender.info.filter.a.LevelMax=INFO

     上下只是两个写法问题

    ========================重点就是

     #log4j.appender.info.BufferSize=2000
     #log4j.appender.info.BufferedIO=true
     ImmediateFlush=true：表示所有消息都会被立即输出，设为false则不输出，默认值是true。


     这连个设置上了。但是打印不打印-- https://blog.csdn.net/aitangyong/article/details/50394857
     */


    public void printLog() {
        Logger log = Logger.getLogger(Log4jSplitFileSuccess.class.getClass());
        log.info("测试info11111111");
        log.debug("测试debug");
        log.error("测试error");
    }
}
