package com.jiamingku.datastructure.queue;

public class LinkList_Queue {
    private java.util.LinkedList list = new java.util.LinkedList();

    public void clear() {
        list.clear();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public Object firstEl() {
        return list.getFirst();
    }

    public Object dequeue() {
        return list.removeFirst();
    }

    public void enqueue(Object e1) {
        list.addLast(e1);
    }

    public String toString() {
        return list.toString();
    }
}