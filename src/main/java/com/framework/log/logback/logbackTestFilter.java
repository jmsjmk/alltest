package com.framework.log.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jiamingku on 2018/6/27.
 */
public class logbackTestFilter {
    private static Logger LOG = LoggerFactory.getLogger(logbackTestFilter.class);

    public static void main(String[] args) {
        LOG.info("333");

    }
}

