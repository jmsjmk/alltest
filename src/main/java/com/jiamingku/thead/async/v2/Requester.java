package com.jiamingku.thead.async.v2;//package com.jiamingku.thead.thread;

//
public class Requester {
    public void request() {
        final FutureTicket ft = new FutureTicket(new MyProcessData());

        //在新线程中调用耗时操作
        new Thread() {
            public void run() {
                ft.makeRealData();
            }
        }.start();
        ft.processData();
    }
}