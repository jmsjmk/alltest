package com.jiamingku.network.io.test;

/**
 * 调度者:
 *
 */
public class MainThread {

    public static void main(String[] args) {
        //这里不做关于IO 和  业务的事情

        //1,创建 IO Thread  （一个或者多个）
        SelectorThreadGroup boss = new SelectorThreadGroup(3);  //混杂模式
        //boss有自己的线程组

        SelectorThreadGroup worker = new SelectorThreadGroup(3);  //混杂模式
        //worker有自己的线程组

        //混杂模式，只有一个线程负责accept，每个都会被分配client，进行R/W
//        SelectorThreadGroup stg = new SelectorThreadGroup(3);

        //2，我应该把 监听（9999）的  server  注册到某一个 selector上

        /**
         * 这只自己管理的员工组.
         */
        boss.setWorker(worker);
        //但是，boss得多持有worker的引用：
        /**
         * boss里选一个线程注册listen ， 触发bind，从而，这个不选中的线程得持有 workerGroup的引用
         * 因为未来 listen 一旦accept得到client后得去worker中 next出一个线程分配
         */

        boss.bind(9999);
        boss.bind(8888);
        boss.bind(6666);
        boss.bind(7777);
    }
}
