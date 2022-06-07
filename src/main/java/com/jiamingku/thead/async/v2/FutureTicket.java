package com.jiamingku.thead.async.v2;

/**
 * Created by jiamingku on 2020/3/4.
 */
@SuppressWarnings("all")
public class FutureTicket {
    private Object data = null;
    private boolean completed = false;
    private ProcessData pd;

    public FutureTicket(ProcessData pd) {
        this.pd = pd;
    }

    public synchronized void makeRealData() {
        if (this.completed) return;
        //获取数据的耗时操作.这里用Sleep代替
        try {
            Thread.sleep(10000);
        } catch (Throwable t) {
        }
        this.data = "返回的数据内容";
        this.completed = true;
        notifyAll();
    }

    public synchronized void processData() {
        while (!this.completed){
            try {
                wait();
            } catch (Throwable t) {
            }
        }
        //return this.data;
        //不用返回了,直接处理
        this.pd.process(this.data);
        // alert(?);

    }


    //这个方法也可以不要了.
    public boolean isCompleted() {
        return this.completed;
    }
}

