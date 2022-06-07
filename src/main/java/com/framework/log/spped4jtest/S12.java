package com.framework.log.spped4jtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LocationAwareLogger;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class S12 {

    public static void main(String[] args) {
        Logger l = LoggerFactory.getLogger(S12.class);
        if (l instanceof LocationAwareLogger) {
            System.out.println("yes");
        }
        String name = l.getName();
        Class c = l.getClass();
        System.out.println(c.getSimpleName());
        System.out.println(name);
    }

    public void test() {
        System.out.println(S12.this.hashCode());
    }

    public static synchronized double variance(List<Double> m_values) {
        long n = 0;
        double mean = 0;
        double s = 0.0;

        for (double x : m_values) {
            n++;
            double delta = x - mean;
            mean += delta / n;
            s += delta * (x - mean);
        }

        return (s / n);
    }


}
