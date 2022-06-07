package com.jiamingku.jvm.old;

import java.lang.management.ManagementFactory;

import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;

public class HelloAgent {
    public static void main(String[] args) throws JMException, Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        /**
         * jconsole 的Mbean中显示的信息就是jmxBean
         * name 就是hello
         *
         * 其实就是你将一个bean注入到平台，然后用jconsole可以动态的改变他
         *
         */
        ObjectName helloName = new ObjectName("jmxBean:name=hello");
        //create mbean and register mbean
        server.registerMBean(new Hello(), helloName);
        Thread.sleep(60 * 60 * 1000);
    }
}