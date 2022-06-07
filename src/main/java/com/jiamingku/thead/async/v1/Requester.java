package com.jiamingku.thead.async.v1;//package com.jiamingku.thead.thread;

//
public class Requester {
    public FutureTicket request() {
        final FutureTicket ft = new FutureTicket();

        //在新线程中调用耗时操作
        new Thread() {
            public void run() {
                ft.makeRealData();
            }
        }.start();
        return ft;
    }
}