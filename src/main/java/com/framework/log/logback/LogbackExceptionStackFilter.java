package com.framework.log.logback;


import java.util.Map;
import java.util.concurrent.atomic.LongAdder;

import org.slf4j.MDC;

import com.google.common.base.Strings;


import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxy;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * 异常栈聚合报警
 * @author yanpengfang
 * create 2019-08-23 10:44 AM
 */
public class LogbackExceptionStackFilter extends Filter<ILoggingEvent>  {

//    private BufferTrigger<ExceptionTag> bufferTrigger;

    private static final String DEFAULT = "default";

    private static final String ERROR_MSG_FMT = "[PERF-错误日志报警][环境:%s][系统:%s][ip:%s]一分钟内异常log次数[%d]大于[%d]" +
            "[第一次异常的traceId:%s][打印异常栈深度:%d][异常:%s]";

//    public LogbackExceptionStackFilter() {
//        bufferTrigger = initialize();
//    }
    private String TRACE_ID;

    @Override
    public FilterReply decide(ILoggingEvent event) {
        try {
            if (!isStarted() || !event.getLevel().equals(Level.ERROR)) {
                return FilterReply.NEUTRAL;
            }
//            QuotaMonitor monitor = ConfigManager.get(QuotaMonitor.class);
//            if (!monitor.isStartup() || monitor.getErrorLog() == null) {
//                return FilterReply.NEUTRAL;
//            }
            final IThrowableProxy throwableProxy = event.getThrowableProxy();
            if (throwableProxy == null) {
                return FilterReply.NEUTRAL;
            }
            if (!(throwableProxy instanceof ThrowableProxy)) {
                return FilterReply.NEUTRAL;
            }
            final ThrowableProxy throwableProxyImpl = (ThrowableProxy) throwableProxy;
            final Throwable throwable = throwableProxyImpl.getThrowable();
            if (throwable == null) {
                return FilterReply.NEUTRAL;
            }
            String exceptionStacks = null;
            if (Strings.isNullOrEmpty(exceptionStacks)) {
                return FilterReply.NEUTRAL;
            }
            Map<String, String> mdcMap = event.getMDCPropertyMap();
            String traceId = mdcMap.get(TRACE_ID);
            if (Strings.isNullOrEmpty(traceId)) {
                //traceId = mdcMap.get(X_REQUEST_ID);
            }
            ExceptionTag tag = new ExceptionTag(traceId, throwable.getMessage(), exceptionStacks);
            // log(ta//g);
        } catch (Exception ignored) {

        }
        return FilterReply.NEUTRAL;
    }


}
