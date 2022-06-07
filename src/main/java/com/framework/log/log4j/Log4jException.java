package com.framework.log.log4j;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import java.util.stream.Stream;

/**
 * ** :import org.apache.log4j.Logger;
 * ** :
 * import org.slf4j.Logger;
 * import org.slf4j.LoggerFactory;
 * =====log4j 跟slf4j的区别
 *
 * Created by jiamingku on 2018/10/31.
 */
public class Log4jException {
    private static final String BASE = "/Users/jiamingku/IdeaProjects/alltest/src/main/java/com/framework/log/log4j/configdemo/";
    private static Logger logger = Logger.getLogger(Log4jException.class);

    public static void main(String[] args) {
        PropertyConfigurator.configure(BASE + "log4jException.properties");
        if (true) {
            NullPointerException nullPointerException = new NullPointerException("DD");
            logger.info("ddd", nullPointerException);
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            logger.info(JSON.toJSONString(nullPointerException));

            nullPointerException.printStackTrace();
        }
    }

    /**
     * 下面这一对方法其实是一样的获取---
     * java.lang.Throwable#getOurStackTrace
     * Thread.currentThread().getStackTrace
     * <p>
     * 打印异常信息都是通过本地方法-获取的一个数组
     */
    @Test
    public void b() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        Stream.of(stackTraceElements).forEach(stackTraceElement ->
                System.out.println("stackTraceElement = " + stackTraceElement));
    }
}
