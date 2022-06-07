package com.jiamingku.j2se.exception.codeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUitilsTest {
    private static Logger log = LoggerFactory.getLogger(LogUitilsTest.class);

    public static void main(String[] args) {
        try {
            throw new NullPointerException("空大是大非");
        } catch (Exception e) {
            LogUtils.info(log, "v3 goods get {}", e.getMessage(), e);
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            LogUtils.info(log, "v3 goods get ", e);
            System.out.println("");
            System.out.println("");
            log.error("<sync orders_logis>: insert orders_logis error!! Exception info : {}", e);
        }
    }
}