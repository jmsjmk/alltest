package com.framework.jmx;

import java.io.Serializable;

public class Student implements StudentMXBean, Serializable {

    @Override
    public String getLastBorrowTimeFormatted() {
        return "dddd";
    }

    @Override
    public String getLastBorrowTrace() {
        return "dddd";
    }
}
