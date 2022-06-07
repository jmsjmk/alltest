package com.middleserver.zk;

import org.apache.zookeeper.ZooKeeper;

public class ZkConstance {

    public static final String ip = "test-zk01-a.zk.01zhuanche.com";
    public static final String port = 2181 + "";
    public static final String CONNECTION_STR = ip + ":" + port;

    public static void main(String[] args) {
        System.out.println("CONNECTION_STR = " + CONNECTION_STR);
    }

    public static void getZkConnectionStatus(ZooKeeper zookeeper) {
        long sessionId = zookeeper.getSessionId();
        byte[] passwd = zookeeper.getSessionPasswd();
        System.out.println("sessionId = " + sessionId + ", passwd = " + passwd + ", Stat=" + zookeeper.getState().toString());

    }
}
