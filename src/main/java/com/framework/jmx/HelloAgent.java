package com.framework.jmx;


import java.lang.management.ManagementFactory;

import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * https://www.cnblogs.com/dongguacai/p/5900507.html
 * http://www.tianshouzhi.com/api/tutorials/jmx/32
 * <p>
 * MalformedObjectNameException: 格式错误
 * InstanceAlreadyExistsException: 已经存在的bean
 *
 * JMX 的名字非常重要：MXBean 这个非常重要.--bean的命令规则很重要
 */
@SuppressWarnings("all")
public class HelloAgent {
    public static void main(String[] args) throws JMException, Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName helloName1 = new ObjectName("com.framework.jmx:type=StudentMBean,name=hello");
        //create mbean and register mbean
        server.registerMBean(new Hello(), helloName1);
        Thread.sleep(60 * 60 * 1000 * 100);
    }

//    @Test
//    public void test1() throws  Exception {
//        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
//        ObjectName helloName = new ObjectName("jmxBean1:name=12hello;t=1");
//        ObjectName helloName1 = new ObjectName("jmxBean:type=Hello1,name=hello");
//        ObjectName helloName2 = new ObjectName("jmxBean:type=Hello2,name=hello");
//        //create mbean and register mbean
//        server.registerMBean(new Hello(), helloName);
////        server.registerMBean(new Hello(), helloName);
////        server.registerMBean(new Hello(), helloName1);
////        server.registerMBean(new Hello(), helloName2);
//        Thread.sleep(60 * 60 * 1000 * 100);
//    }
}