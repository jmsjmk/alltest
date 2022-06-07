package com.jiamingku.thead.axman.base;

import java.util.*;
/**
 * 实战篇的开篇内容,--生产者消费者模式.
 */

/**
 * 食物对象
 */
class Food {
}

/**
 * 共享资源,真正的保护对象
 */
class Table extends LinkedList {
    int maxSize;

    public Table(int maxSize) {
        this.maxSize = maxSize;
    }


    public synchronized void putFood(Food f) {
        while (this.size() >= this.maxSize) {
            try {
                this.wait();
            } catch (Exception e) {
            }
        }
        this.add(f);
        notifyAll();
    }

    public synchronized Food getFood() {
        while (this.size() <= 0) {
            try {
                this.wait();
            } catch (Exception e) {
            }
        }
        Food f = (Food) this.removeFirst();
        notifyAll();
        return f;
    }
}


class Chef extends Thread {
    Table t;
    String name;
    Random r = new Random(12345);

    public Chef(String name, Table t) {
        this.t = t;
        this.name = name;
    }

    public void run() {
        while (true) {
            Food f = make();
            System.out.println(name + " put a Food:" + f);
            t.putFood(f);
        }
    }

    private Food make() {
        try {
            Thread.sleep(200 + r.nextInt(200));
        } catch (Exception e) {
        }
        return new Food();
    }
}

class Eater extends Thread {
    Table t;
    String name;
    Random r = new Random(54321);

    public Eater(String name, Table t) {
        this.t = t;
        this.name = name;
    }

    public void run() {
        while (true) {
            Food f = t.getFood();
            System.out.println(name + " get a Food:" + f);
            eat(f);

        }
    }

    private void eat(Food f) {

        try {
            Thread.sleep(400 + r.nextInt(200));
        } catch (Exception e) {
        }
    }
}

public class Test {
    public static void main(String[] args) throws Exception {
        Table t = new Table(10);
        new Chef("Chef1", t).start();
        new Chef("Chef2", t).start();
        new Chef("Chef3", t).start();
        new Chef("Chef4", t).start();
        new Eater("Eater1", t).start();
        new Eater("Eater2", t).start();
        new Eater("Eater3", t).start();
        new Eater("Eater4", t).start();
        new Eater("Eater5", t).start();
        new Eater("Eater6", t).start();
    }
}