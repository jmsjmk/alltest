package com.framework.log.log4j;

import org.apache.log4j.Logger;

/**
 * Created by jiamingku on 2018/10/31.
 */
public class Log4jFormateInfo {

    private static Logger logger = Logger.getLogger(Log4jFormateInfo.class);
    private static Logger logger1 = Logger.getLogger("t1");
    private static Logger loggerNdc = Logger.getLogger("ndctt");

    /**
     *  logback-与 log4j-success.properties 的格式有很大区别
     *  :-  =====================默认值符号
     *  %X{X-B3-TraceId:-} %X{X-B3-SpanId:-} %X{X-Span-Export:-}  %d{yyyy-MM-dd HH:mm:ss.SSS}
     *  %X{trace_ip} %X{service_name} [%thread] %-5level %logger:%line %msg%n
     */
    /**
     * #%c 输出所属类的全名
     * 　　 #%d 输出日志时间其格式为 可指定格式 如 %d{HH:mm:ss} 等
     * 　　 #%n 换行符
     * 　　 #%m 输出代码指定信息，如 info(“message”), 输出 message
     * 　　 #%p 输出日志的优先级，即 FATAL ,ERROR，INFO 等
     * <p>
     * ===
     * <p>
     * %c 输出日志信息所属的类的全名
     * %d 输出日志时间点的日期或时间，默认格式为ISO8601，也可以在其后指定格式，比如：%d{yyy-MM-dd HH:mm:ss }，输出类似：2002-10-18- 22：10：28
     * %f 输出日志信息所属的类的类名
     * %l 输出日志事件的发生位置，即输出日志信息的语句处于它所在的类的第几行
     * %m 输出代码中指定的信息，如log(message)中的message
     * %n 输出一个回车换行符，Windows平台为“rn”，Unix平台为“n”
     * %p 输出优先级，即DEBUG，INFO，WARN，ERROR，FATAL。如果是调用debug()输出的，则为DEBUG，依此类推
     * %r 输出自应用启动到输出该日志信息所耗费的毫秒数
     * %t 输出产生该日志事件的线程名
     *
     * @param args https://logging.apache.org/log4j/2.x/manual/layouts.html#PatternLayout
     *             <p>
     *             %-5p  ===申请宽度左对齐--
     *             %n    ===必须的要不不换行
     *             <p>
     *             <p>
     *             <p>
     *             有一种格式觉得很简练：
     *             log4j.appender.CONSOLE.layout=org.apache.log4j.PatternLayout
     *             log4j.appender.CONSOLE.layout.ConversionPattern=[Level]%-5p [Date]%d{yyyy-MM-dd HH:mm:ss}-[Class]%-5l-[Msg]:%m%n
     *             <p>
     *             https://logging.apache.org/log4j/2.x/manual/layouts.html#PatternLayout
     *             <p>
     *             https://logback.qos.ch/manual/index.html
     */
    public static void main(String[] args) {
//         logger.info("d");
        logger1.info("d");
        logger1.info("d");
    }
}
