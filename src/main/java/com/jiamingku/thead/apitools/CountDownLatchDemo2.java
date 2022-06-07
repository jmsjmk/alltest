package com.jiamingku.thead.apitools;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 1.线程孩子上车。
 * 2.阻塞时间限制。
 * 3.开车。
 */
public class CountDownLatchDemo2 {
    public static int numberOfPeople = 10;//等车的学生数
    public static boolean isGone = false;//车开的标志
    public static int carWaitTime = 100;//车等的时间

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch waitStudentsGetOn = new CountDownLatch(numberOfPeople);
        new Thread(new GetOn(waitStudentsGetOn)).start();
        waitStudentGetOn(waitStudentsGetOn);//等所有的学生上车
    }

    private static void waitStudentGetOn(CountDownLatch waitStudentsGetOn) throws InterruptedException {
        System.out.println("赶紧的,抓紧时间上车..");
        boolean b = waitStudentsGetOn.await(carWaitTime, TimeUnit.SECONDS);//等5秒，还没上车，就开走。。
        System.out.println("阻塞等待result:" + b +", 开车.............回家");
    }

}

class GetOn implements Runnable {

    private CountDownLatch waitStudentsGetOn;

    GetOn(CountDownLatch waitStudentsGetOn) {
        this.waitStudentsGetOn = waitStudentsGetOn;
    }

    @Override
    public void run() {
        for (int i = 0; i < CountDownLatchDemo2.numberOfPeople; i++) {
            try {
                if (CountDownLatchDemo2.isGone) {
                    System.out.println("妈的，还差：" + waitStudentsGetOn.getCount() + " 个没娃上车呢.怎么车走了");
                    break;
                }
                boolean goonSuccess = new Student(i + 1).getOn();//顺序上车
                if (goonSuccess)
                    waitStudentsGetOn.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (waitStudentsGetOn.getCount() != 0l) {
                System.out.println("还差：" + (waitStudentsGetOn.getCount()) + " 个没上车");
            } else {
                System.out.println("都上车了");
            }
        }
    }

    /**
     * 模拟孩子上车时间<br/>
     */
    class Student {
        private int myNum;//学生编号

        public Student(int num) {
            this.myNum = num;
        }

        //上车
        public boolean getOn() throws InterruptedException {
            long a = new Random().nextInt(2) * 1000;
            //System.out.println(a);
            Thread.currentThread().sleep(a);//上车使用的时间，随机
            if (CountDownLatchDemo2.isGone) {
                return false;//不能上了，上车失败
            }
            System.out.print("编号为:" + myNum + "的同学上车了..");
            return true;
        }
    }
}