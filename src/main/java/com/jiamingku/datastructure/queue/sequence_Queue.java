package com.jiamingku.datastructure.queue;

public class sequence_Queue {
    private int first, last, size;
    private Object[] storage;

    public sequence_Queue() {
        this(100);
    }

    public sequence_Queue(int n) {
        size = n;
        storage = new Object[size];
        first = last = -1;
    }

    public boolean isFull() {
        return first == 0 && last == size - 1 || first == last + 1;
    }

    public boolean isEmpty() {
        return first == -1;
    }

    public void enqueue(Object e1) {
        if (last == size - 1 || last == -1) {
            storage[0] = e1;
            last = 0;
            if (first == -1)
                first = 0;
        } else {
            storage[++last] = e1;
        }
    }

    public Object dequeue() {
        Object tmp = storage[first];
        if (first == last)
            last = first = -1;
        else if (first == size - 1)
            first = 0;
        else first++;
        return tmp;
    }

    public void printAll() {
        for (int i = 0; i < size; i++)
            System.out.print(storage[i] + " ");
    }
}