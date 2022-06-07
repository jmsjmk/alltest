package com.jiamingku.datastructure.queue;

/**
 * Created by jiamingku on 2017/5/3.
 */
public class ForQueue {

    private Object[] objects;
    private int head;
    private int tail;
    private int count;

    public ForQueue(int size) {
        this.count = 0;
        this.objects = new Object[size];
    }

    public ForQueue() {
        this(10);
    }

    /**
     * count 可以防止覆盖
     * @param o
     */
    public void in(Object o) {
        if(count >= objects.length ) {
            System.out.println("is full");
        } else {
            objects[tail] = o;
            tail = (tail + 1) % objects.length;
            count ++;
        }
    }


    public Object out() {
        Object o = null;
        if(count <= 0) {
            System.out.println("empty le");
            o = "999999";
        } else {
            o =  objects[head] ;
            head = (head + 1) % objects.length;
            count --;
        }

        return o;
    }



    public static void main(String[] args) {

        ForQueue my = new ForQueue();

        my.in(1);
        my.in(2);
        my.in(3);
        my.in(4);
        my.in(5);
        my.in(6);
        my.in(7);



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

        my.in(7);
        my.in(7);
        my.in(7);
        my.in(7);
        my.in(7);
        my.in(7);
        my.in(7);
        my.in(7);
        my.in(7);
        my.in(7);
        my.in(7);
        my.in(7);
        my.in(7);
        my.in(7);
        my.in(7);
        my.in(7);
        my.in(7);

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
        System.out.println(my.out());






    }


}
