package com.jiamingku.thead.blokqueue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * element与peek方法的区别?
 * 都是返回头元素,不删除，在队列元素为空的情况下，element() 方法会抛出NoSuchElementException异常，peek() 方法只会返回 null。
 * <p>
 * add与offer方法的区别?
 * 都是将元素插入到队列中,区别就是add条件不满足时候抛出异常,offer不抛出异常返回false(null的时候直接抛出异常)
 * <p>
 * remove与poll区别?
 * 都是返回头元素并且移除头元素，空的时候poll返回null，remove发生异常
 */
@SuppressWarnings("all")
public class BlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        String s = "%s_%03d";
        String s1 = String.format(s, "what", 1);
        System.out.println("s1 = " + s1);
    }

    public static String getSaveUrl(String s) {
        String newUrl = "";
        if (StringUtils.isNotBlank(s) && s.contains("Expires=")) {
            int b = s.indexOf("Expires=");
            newUrl = s.substring(0, b - 1);
        }
        return newUrl;
    }
    // ---------------------------队列满了抛出异常s--------------------------

    /**
     * 队列满了抛出异常,如果元素是null的话,抛出异常
     * add本身也是获取锁但是发现满了就释放了
     */
    @Test
    public void addTest() {
        LinkedBlockingQueue<String> queue1 = new LinkedBlockingQueue<String>(4);
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(4);
        queue.add("11");
        queue.add("11");
        queue.add("11");
        queue.add("11");
        queue.add("11");
        System.out.println("queue = " + queue.size());
    }

    /**
     * 当队列的size满得时候返回false
     */
    @Test
    public void offerTest() {
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(4);
        boolean b = queue.offer("11");
        System.out.println("b = " + b);
        b = queue.offer("11");
        System.out.println("b = " + b);
        b = queue.offer("11");
        System.out.println("b = " + b);
        b = queue.offer("11");
        System.out.println("b = " + b);
        b = queue.offer("11");
        System.out.println("b = " + b);
        System.out.println("queue = " + queue.size());
    }


    /**
     * 就是队列空了remove(参数的)不返回异常
     * <p>
     * remove() 这两个方法一定要注意,remove直接发生异常
     */
    @Test
    public void removeTest() {
        LinkedBlockingQueue<String> queue1 = new LinkedBlockingQueue<String>(4);
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(4);
        System.out.println("queue = " + queue.size());
        queue.add("11");
        boolean a = queue.remove("11");
        a = queue.remove("11");
        a = queue.remove("11");
        a = queue.remove("11");
        a = queue.remove("11");
        a = queue.remove("11");
        a = queue.remove("11");
        System.out.println("a = " + a);
        queue.remove();

        queue1.remove();
        System.out.println("queue = " + queue.size());
    }

    /**
     * 当队列的size满得时候返回false
     */
    @Test
    public void poolTest() throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(4);
        boolean b = queue.offer("11");
        System.out.println("b = " + b);
        b = queue.offer("11");
        System.out.println("b = " + b);
        b = queue.offer("11");
        System.out.println("b = " + b);
        b = queue.offer("11");
        System.out.println("b = " + b);
        System.out.println("queue = " + queue.size());
        String s = queue.poll(1000, TimeUnit.SECONDS);
        System.out.println("s = " + s);
        s = queue.poll();
        System.out.println("s = " + s);
        s = queue.poll();
        System.out.println("s = " + s);
        s = queue.poll();
        System.out.println("s = " + s);
        System.out.println("queue = " + queue.size());
        /**
         * 当size=0的时候poll返回的null
         */
        String s1 = queue.poll();
        System.out.println(s1);
        String result = queue.poll(10, TimeUnit.SECONDS);
        System.out.println("result = " + result);
    }

    /**
     * 返回队列的头，但是不删除原属。跟peek的区别
     * 但是在队列是size=0的时候会出现 NoSuchElementException
     */
    @Test
    public void elementest() {
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(5);
//        queue.add("009");
        /** 不会出现异常*/
        String s1 = queue.peek();
        System.out.println("s1 = " + s1);
        /** size=0的时候发生异常*/
        String s = queue.element();
        System.out.println("s = " + s);
        System.out.println("queue = " + queue.size());
    }

    /**
     * 返回队列的头，但是不删除原属。跟peek的区别
     * 但是在队列是size=0的时候会出现 NoSuchElementException
     */
    @Test
    public void elementestT2() {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        queue.add("112");
        queue.add("1111");
        queue.add("009");
        /** 不会出现异常*/
        String s = queue.element();
        System.out.println("s = " + s);
        s = queue.element();
        System.out.println("s = " + s);
        s = queue.element();
        System.out.println("queue = " + queue.size());
    }


    /**
     * 当队列的size满得时候返回false
     */
    @Test
    public void peekTest() throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(3);
        String s = queue.peek();
        System.out.println("s = " + s);

    }

    /**
     * ----------------------一只阻塞---------------
     */
    @Test
    public void putTest() throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(4);
        Thread t = new Thread("YYYYYYYYYYYY") {
            @Override
            public void run() {
                try {
                    queue.put("11");
                    queue.put("11");
                    queue.put("11");
                    queue.put("11");
                    queue.put("11");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        Thread.sleep(500L);
        Thread t1 = new Thread("SSSSSSSSSS") {
            @Override
            public void run() {
                try {
                    queue.put("11");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
        Thread.sleep(3000L);
        t1.interrupt();
        System.out.println("queue = " + queue.size());
        Thread.sleep(10000000L);
    }


    @Test
    public void takeTest() throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(4);
        String s = queue.take();
        System.out.println("s = " + s);
        System.out.println("queue = " + queue.size());
    }

    /**
     * -------------------------------------
     */


    @Test
    public void offerTimeTest() throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(4);

        queue.offer("11", 10000, TimeUnit.SECONDS);
        queue.offer("11", 10000, TimeUnit.SECONDS);
        queue.offer("11", 10000, TimeUnit.SECONDS);
        queue.offer("11", 10000, TimeUnit.SECONDS);
        queue.offer("11", 10, TimeUnit.SECONDS);

        System.out.println("queue = " + queue.size());
    }

    @Test
    public void testAddNullElement() {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(100);
        arrayBlockingQueue.add(null);
        arrayBlockingQueue.peek();
    }

    @Test
    public void pollTimeTest() throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(4);
        String s = queue.poll(20, TimeUnit.SECONDS);
        System.out.println("s = " + s);
        System.out.println("queue = " + queue.size());
    }


    @Test
    public void testInterupt() throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(4);

        Thread t = new Thread("poll-method") {
            @Override
            public void run() {
                try {
                    String s = queue.poll(200000, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();

        Thread thread = new Thread("take-method") {
            @Override
            public void run() {
                try {
                    String s = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        Thread.sleep(1000L);
        thread.interrupt();
        t.interrupt();
        Thread.sleep(100000L);
    }
}