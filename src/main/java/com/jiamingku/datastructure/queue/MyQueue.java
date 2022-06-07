package com.jiamingku.datastructure.queue;

/**
 * Created by jiamingku on 2017/5/3.
 */
public class MyQueue {
    private Object[] objects;
    private int head;
    private int tail;
    private int count;

    public MyQueue(int size) {
        this.count = 0;
        this.objects = new Object[size];
    }

    public MyQueue() {
        this(1000);
    }


    public void in(Object o) {
        if (tail >= objects.length) {
            System.out.println("no in");
        } else {
            objects[tail++] = o;

        }
    }

    public Object out() {
        Object o = null;
        if (head > tail || head == objects.length) {
            System.out.println("not out");
            o = new String("999999999999999999999999999");
        } else {
            o = objects[head++];
        }
        return o;
    }


    public static void main(String[] args) {

        MyQueue my = new MyQueue();

        my.in(1);
        my.in(2);
        my.in(3);
        my.in(4);
        my.in(5);
        my.in(6);
        my.in(7);
        my.in(8);
        my.in(9);
        my.in(10);
        my.in(10);
        my.in(10);
        my.in(6);
        my.in(7);
        my.in(8);
        my.in(9);
        my.in(10);
        my.in(10);
        my.in(10);


        System.out.println(my.out());
        System.out.println(my.out());
        System.out.println(my.out());
        System.out.println(my.out());
        System.out.println(my.out());
        System.out.println(my.out());
        System.out.println(my.out());
        System.out.println(my.out());
        System.out.println(my.out());
        System.out.println(my.out());
        System.out.println(my.out());
        System.out.println(my.out());




    }


}
