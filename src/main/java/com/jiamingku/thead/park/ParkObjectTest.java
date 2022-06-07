package com.jiamingku.thead.park;

import java.util.concurrent.locks.LockSupport;

/**
 * park(object),            ---就是为了调试问题时候方便
 * park();两个方法的区别
 */
public class ParkObjectTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread("test-park-noObject") {
            @Override
            public void run() {
                ParkObjectTest parkObjectMethod = new ParkObjectTest();
                System.out.println("parkTest = " + parkObjectMethod);
                try {
                    parkObjectMethod.a();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        Thread.sleep(1000L);
        Object o = LockSupport.getBlocker(t);
        System.out.println("o = " + o);


        Thread t1 = new Thread("test-park-Object") {
            @Override
            public void run() {
                ParkObjectTest parkObjectMethod = new ParkObjectTest();
                System.out.println("parkTest = " + parkObjectMethod);
                try {
                    parkObjectMethod.b();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
        Thread.sleep(1000L);
        Object o1 = LockSupport.getBlocker(t1);
        System.out.println("o = " + o1);
    }

    /**
     * 对比代参数的 少了一个waiting on
     * <p>
     * java.lang.Thread.State: WAITING (parking)
     * at sun.misc.Unsafe.park(Native Method)
     * at java.util.concurrent.locks.LockSupport.park(LockSupport.java:304)
     * at com.jiamingku.thead.park.ParkNoObjectMethod.a(ParkNoObjectMethod.java:45)
     * at com.jiamingku.thead.park.ParkNoObjectMethod$1.run(ParkNoObjectMethod.java:19)
     *
     * @throws InterruptedException
     */
    public void a() throws InterruptedException {
        LockSupport.park();
        Thread.sleep(1000009L);
    }


    /**
     * jstack 查询显示的信息如下----------------------
     * "test-park1111" #11 prio=5 os_prio=31 tid=0x00007fe299303000 nid=0xa803 waiting on condition [0x0000700003877000]
     * java.lang.Thread.State: WAITING (parking)
     * at sun.misc.Unsafe.park(Native Method)
     * - parking to wait for  <0x000000076b01f548> (a java.lang.Long)
     * at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)-------代表在那个对象上面
     * at com.jiamingku.thead.park.parkTest1.a(parkTest1.java:34)
     * - locked <0x000000076b01ebc0> (a com.jiamingku.thead.park.parkTest1)
     * at com.jiamingku.thead.park.parkTest1$1.run(parkTest1.java:19)
     *
     * @throws InterruptedException
     */
    public void b() throws InterruptedException {
        Object o = new Object();
        System.out.println("o = " + o);
        LockSupport.park(o);
        Thread.sleep(1000009L);
    }
}
