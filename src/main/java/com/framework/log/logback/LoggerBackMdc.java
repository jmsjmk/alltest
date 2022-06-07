package com.framework.log.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Created by jiamingku on 2019/11/28.
 */
public class LoggerBackMdc {
    private static Logger LOG = LoggerFactory.getLogger("com.jiamingku.log");

    public static void main(String[] args)
    {
        // 与log4j还是差了一些。
//        MDC.put("so", "1");
//        MDC.put("X-B3-TraceId", "WAT111111111111111112121AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//        MDC.put("X-B3-SpanId", "WAT");
//        MDC.put("X-Span-Export:-", "WAT");
        /**
         * <conversionRule conversionWord="ip" converterClass="com.zhuanche.util.IPLogConfig" />
         * <property name="appName" value="mp-manage"/>
         *
         * %ip
         * ${appName:-} 获取配置的名字
         * 还有一种就是 MDC :- 后面的是默认值
         */

        LOG.info("3333");
    }
}
