package com.jiamingku.thead.axman;

import com.jiamingku.thead.axman.request.MethodRequest;
@SuppressWarnings("all")
public class SmartQueue {

    private static final int MAX_METHOD_REQUEST = 100;
    private final MethodRequest[] requestQueue;
    private int tail;
    private int head;
    private int count;

    public SmartQueue() {
        this.requestQueue = new MethodRequest[MAX_METHOD_REQUEST];
        this.head = this.count = this.tail = 0;
    }

    public synchronized void putRequest(MethodRequest request) {
        while (this.count >= this.requestQueue.length) {
            try {
                this.wait();
            } catch (Throwable t) {
            }
        }
        this.requestQueue[this.tail] = request;
        tail = (tail + 1) % this.requestQueue.length; //如果到数组最后则从头开始  
        count++;
        this.notifyAll();
    }

    public synchronized MethodRequest takeRequest() {
        while (this.count <= 0) {
            try {
                this.wait();
            } catch (Throwable t) {
            }
        }
        MethodRequest request = this.requestQueue[this.head];

        // this.requestQueue[this.head] = null;  
        // 考虑注释掉的这一行代码，对于循环的队列还不是非常明显，如果是非循环的队列。这个元素  
        // 可能永远被持有，比如一个Stack中如果put了，再pop时，虽然对象被取走了，栈顶指针指象了index+1;  
        // 但底层的数组中那个index位置的对象还被数组本身引用着，如果没有put动作替换这个位置的句柄指向一个  
        // 新的对象，则已经pop出去的对象一直被数组本身引用着。所以对于处于<a href="http://lib.csdn
        // .net/base/datastructure" class='replace_word' title="算法与数据结构知识库" target='_blank' style='color:#df3434;
        // font-weight:bold;'>数据结构</a>中的对象如果要从中取出，
        // 数据结果本身要取消对它的引用。  

        this.head = (this.head + 1) % this.requestQueue.length;
        count--;
        this.notifyAll();
        return request;
    }
}