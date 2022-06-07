package com.jiamingku.thead.base;

import org.junit.Test;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiamingku on 2020/3/2.
 * 线程组可以方便的进行管理,使用比较少
 * <p>
 * --- http://blog.csdn.net/a352193394/article/details/39323427
 */
public class ThreadGroupTest {

    public static void main(String[] args) {
        ThreadGroupTools.getGroupNames();
        System.out.println(" ===== ");
        ThreadGroup t = new ThreadGroup("what");

        // ----- 自定义的
        FatherThread fathreadThread = new FatherThread(t, "fatherThread");
        fathreadThread.start();
    }

    /**
     * 线程A,线程B这两个线程组,system线程组的父线程组==null 容易发生空指针异常
     * 尝试中断一组线程
     */
    @Test
    public void testInterrupt() {
        ThreadGroup rootThreadGroup = new ThreadGroup("root线程组");
        Thread thread0 = new Thread(rootThreadGroup, new MRunnableInterrupt(), "线程A");
        Thread thread1 = new Thread(rootThreadGroup, new MRunnableInterrupt(), "线程B");
        thread0.start();
        thread1.start();
        System.out.println("===========================");
        /**
         * 可以创建线程组的时候指定---父线程组
         */
        ThreadGroup threadGroup1 = new ThreadGroup(rootThreadGroup, "子线程组");
        Thread thread2 = new Thread(threadGroup1, new MRunnableInterrupt(), "线程C");
        Thread thread3 = new Thread(threadGroup1, new MRunnableInterrupt(), "线程D");
        thread2.start();
        thread3.start();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rootThreadGroup.interrupt();
        System.out.println("批量中断组内线程");

        // 没有被中断 ----
        Thread thread4 = new Thread(new MRunnableInterrupt(), "线程E");
        thread4.start();
        try {
            Thread.sleep(100000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// ------------------------------------------------------------------------------------

/**
 * 线程组构建的时候可以指定, 不指定就是当前的线程组对象
 */
class FatherThread extends Thread {

    public FatherThread(ThreadGroup group, String name) {
        super(group, name);
    }

    @Override
    public void run() {
        ThreadGroupTools.getGroupNames();
        SonThread sonThread = new SonThread();
        sonThread.start();
    }
}

class SonThread extends Thread {
    @Override
    public void run() {
        while (true) {
            ThreadGroupTools.getGroupNames();
            try {
                Thread.sleep(10300L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ThreadGroupTools {

    public static void getGroupNames() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
            int i = 0;
            boolean b = false;
            String name = Thread.currentThread().getName();
            StringBuffer sb = new StringBuffer();
            sb.append(name);
            while (threadGroup != null) {
                sb.append(" --> ");
                String name1 = threadGroup.getName();
                String m = "[" + i++ + "]" + name1;
                sb.append(m);
                //System.out.println("[" + i++ + "] " + name1);
                threadGroup = threadGroup.getParent();
            }
            System.out.println(sb.toString());
            b = true;
            if (b) break;
        }
    }
}


// ------------------------------------------------------------------------------------
class MRunnableInterrupt implements Runnable {
    @Override
    public void run() {
        /**
         * 打印出来线程组
         */
        ThreadGroupTools.getGroupNames();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[" + Thread.currentThread().getName() + "]---执行结束");
    }
}

// -----------------------------------------------------------------------
class Result {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/**
 * 直接永兴这个方法，观察线程的组的管理
 * threadGroup.activeCount()
 */
class SearchTask implements Runnable {

    public SearchTask(Result result) {
        this.result = result;
    }

    private Result result;

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("Thread Start " + name);
        try {
            doTask();
            result.setName(name);
        } catch (InterruptedException e) {
            System.out.printf("Thread %s: Interrupted\n", name);
            return;
        }
        System.out.println("Thread end " + name);
    }

    private void doTask() throws InterruptedException {
        Random random = new Random((new Date()).getTime());
        int value = (int) (random.nextDouble() * 100);
        System.out.printf("Thread %s: %d\n", Thread.currentThread().getName(), value);
        TimeUnit.SECONDS.sleep(value);
    }

    public static void main(String[] args) {
        //创建5个线程，并入group里面进行管理
        ThreadGroup threadGroup = new ThreadGroup("Searcher");
        Result result = new Result();
        SearchTask searchTask = new SearchTask(result);
        for (int i = 0; i < 5; i++) {
            Thread thred = new Thread(threadGroup, searchTask);
            thred.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //通过这种方法可以看group里面的所有信息
        System.out.printf("Number of Threads: %d\n", threadGroup.activeCount());
        System.out.printf("Information about the Thread Group\n");
        threadGroup.list();

        //这样可以复制group里面的thread信息
        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        for (int i = 0; i < threadGroup.activeCount(); i++) {
            System.out.printf("Thread %s: %s\n", threads[i].getName(),
                    threads[i].getState());
        }

        waitFinish(threadGroup);
        //将group里面的所有线程都给interpet
        threadGroup.interrupt();
    }

    private static void waitFinish(ThreadGroup threadGroup) {
        while (threadGroup.activeCount() > 9) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

