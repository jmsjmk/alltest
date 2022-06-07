package com.jiamingku.thead.axman;

import com.jiamingku.thead.axman.impl.Proxy;
import com.jiamingku.thead.axman.impl.SmartAxmanServiceImp;

public class SmartAxmanServiceFactory {
    public static synchronized ISmartAxmanService createService() {
        /**
         * 真正的实现
         */
        SmartAxmanServiceImp imp = new SmartAxmanServiceImp();
        /**
         *  队列
         */
        SmartQueue queue = new SmartQueue();
        /**
         * 调度器，里面必须的有个队列
         */
        Scheduler st = new Scheduler(queue);
        /**
         *
         */
        Proxy p = new Proxy(st, imp);
        st.start();
        return p;
    }
}