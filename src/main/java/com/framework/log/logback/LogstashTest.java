package com.framework.log.logback;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jiamingku on 2020/6/22.
 */
public class LogstashTest {

    private Logger logger = LoggerFactory.getLogger(LogstashTest.class);

    @Test
    public void test() {

        logger.info("333 {}", " haojiahuo");

    }


    @Test
    public void test1() {

        try {

            throw new NullPointerException("CESHI");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("333 ", e);
        }


    }

}