package com.jiamingku.jvm;


import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class RuntimeMXBeanUtil {

//    private static final Logger logger = MyLoggerUtil.setLoggerHanlder(Logger.getLogger("RuntimeMXBeanUtil"));

    private static final RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

    public static void main(String[] args) {

        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println("runtimeMXBean = " + runtimeMXBean.getName());

        System.out.println("BootClassPart-->\n" + runtimeMXBean.getBootClassPath());
        //System.out.println(("ManagerSpecVersion is "+runtimeMXBean.getManagementSpecVersion());
        //System.out.println(("ClassPath is "+runtimeMXBean.getClassPath());
        //System.out.println(("Library path is "+runtimeMXBean.getLibraryPath());

        /**
         * Populate the process ID 
         */
        System.out.println("The PID is :" + populateProcessID());

        System.out.println("Spec Name is : " + runtimeMXBean.getSpecName());
        System.out.println("Spec Vendor is :" + runtimeMXBean.getSpecVendor());
        //System.out.println(("Spec Version is "+runtimeMXBean.getSpecVersion());
        //System.out.println(("Start Time is "+runtimeMXBean.getStartTime());
        //System.out.println(("Start Time is "+runtimeMXBean.getStartTime());
        //System.out.println(("Update Time is "+runtimeMXBean.getUptime());

        System.out.println("VM Name is " + runtimeMXBean.getVmName());

        System.out.println("VM Vendor is " + runtimeMXBean.getVmVendor());
        System.out.println("VM Version is " + runtimeMXBean.getVmVersion());

        /**
         * Get System Properties 
         */
        System.out.println("System Properties--> " + runtimeMXBean.getSystemProperties());

        //System.out.println(("Is boot class path supported? "+runtimeMXBean.isBootClassPathSupported());
        //System.getProperties();
        //what is the difference between ManagementFactory.getRuntimeMXBean().getSystemProperties() and System.getProperties() ?  
        //The difference is you can use RuntimeMXBean from a remote JVM to obtain its system properties.  

        //System.out.println(runtimeMXBean.getSystemProperties().size() == System.getProperties().size());

        javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null,
                "The PID is " + populateProcessID(), null, javax.swing.JOptionPane.DEFAULT_OPTION);
    }

    private static String populateProcessID() {
        /* 
         * runtimeMXBean.getName()取得的值包括两个部分：PID和hostname，两者用@连接。 
         */
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

        return runtimeMXBean.getName().split("@")[0];
    }
}  