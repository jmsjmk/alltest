//package com.jiamingku.jvm.old;
//
//
//
//import com.sun.jdmk.comm.HtmlAdaptorServer;
//
//import java.lang.management.ManagementFactory;
//
//import javax.management.JMException;
//import javax.management.MBeanServer;
//import javax.management.ObjectName;
//
////import com.sun.jdmk.comm.HtmlAdaptorServer;
//
///**
// * Created by jiamingku on 2018/9/15.
// * http://localhost:8082，点
// */
//public class HelloWebAgent {
//    public static void main(String[] args) throws JMException, Exception {
//        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
//        ObjectName helloName = new ObjectName("jmxBean:name=hello");
//        //create mbean and register mbean
//        server.registerMBean(new Hello(), helloName);
//
//        /**
//         * 同样的如果你你不用jconsole的话你可以用别用浏览器等都可以
//         * 适配器
//         */
//        ObjectName adapterName = new ObjectName("HelloAgent:name=htmladapter,port=8082");
//        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
//        server.registerMBean(adapter, adapterName);
//        adapter.start();
//    }
//
//}
