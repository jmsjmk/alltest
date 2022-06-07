package com.jiamingku.thead.async.v1;//package com.jiamingku.thead.thread;

/**
 * 最简单的异步模型。
 * 票据锁两个方法
 * <p>
 * 1.获取阻塞
 * 2.通知阻塞
 * 3.传递给线程调用
 *
 * ----------------------------------------------------
 * 知识点: 一步模型就是 synchronized wait(), notify() 没了
 *
 */
public class FutureTicket {
    private Object data = null;
    private boolean completed = false;

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

    public synchronized Object getData() {
        while (!this.completed) {
            try {
                wait();
            } catch (Throwable t) {
            }
        }
        return this.data;

    }

    public boolean isCompleted() {
        return this.completed;
    }
}
