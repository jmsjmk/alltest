package com.framework.log.logback.com.test.whyasuam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Created by jiamingku on 2018/6/27.
 */
@SuppressWarnings("all")
public class logbackTestFormate {
    //  这个日志的格式 c.f.l.l.c.t.w 是looger配置的 %logger{35}
    // 2019-11-28 09:44:01.002 [main] INFO  c.f.l.l.c.t.w.logbackTestFormate:18 - dddd333333333333333333333333333333333333333333
//    private static Logger LOG = LoggerFactory.getLogger(logbackTestFormate.class);

    public static void main(String[] args) {
        try {
//            LOG.info("dddd333333333333333333333333333333333333333333");

            BigDecimal b1 = new BigDecimal("12");
            BigDecimal b2 = null;

            BigDecimal b3 = b1.subtract(b2);
            System.out.println("b3 = " + b3);
        } catch (Exception e) {
//            LOG.error("{}", e);
//            LOG.info("{}", e.getMessage(), e);
//            System.out.println("e ======================== ");
        }
    }
}
