package com.framework.model.observable.event.eventtest;

public class Door1Test {
    public static void main(String[] args) {

        // 对比awt 其实是一样的
        Door1 door = new Door1();
        door.setStateListener(new DoorStateListener());
        door.setNameListener(new DoorNameListener());
        // 开门
        door.setState("open");
        System.out.println("我已经进来了");
        // 关门
        door.setState("close");
        // 改名
        door.setName("dengzy");
    }
}