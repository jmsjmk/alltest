package com.framework.log.spped4jtest;

import com.ecyrd.speed4j.StopWatch;

public class Test1 {
    public static void main(String[] args) {
        Test1 test1 = new Test1();
        test1.myBusyMethod();

        System.out.println("====");
        test1.myBusyMethod1();
    }


    public void myBusyMethod() {
        StopWatch sw = new StopWatch();

        // Do the busy thing

        sw.stop();
        System.out.println(sw);
    }

    public void myBusyMethod1() {
        StopWatch sw = new StopWatch();
        int iterations = 1000;
        for( int i = 0; i < iterations; i++ ) {
            // Do the busy thing
        }
        sw.stop();
        System.out.println(sw.toString(iterations));
    }

//    public void myBusyMethod2()
//    {
//        StopWatch sw = myStopWatchFactory.getStopWatch();
//
//        try
//        {
//            // Do the busy thing
//
//            // Notice that sw.stop() automatically logs if the Factory is configured so
//            sw.stop("busyThing:success");
//        }
//        finally
//        {
//            sw.stop("busything:failure");
//        }
//    }
}
