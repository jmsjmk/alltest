package com.jiamingku.thead.axman;

import com.jiamingku.thead.axman.request.MethodRequest;

public class Scheduler extends Thread {
  
    private final SmartQueue queue; // 所有加载了请求方法的对象需要放在一个容器上以便统一调用处理。
    // 这里实现了一个队列
    public Scheduler(SmartQueue queue) {
        this.queue = queue;
    }

    public void invoke(MethodRequest request) {
        this.queue.putRequest(request);
    }

    public void run() {
        while (true) {
            // 如果队列中有请求线程,则开始执行请求
            MethodRequest request = this.queue.takeRequest();
            request.execute();
        }
    }
}  