package com.framework.model.observable.event.eventtest;

import java.util.EventListener;

public class DoorStateListener implements EventListener {
    public void doorEvent(Door1Event event) {
        if (event.getValue() != null && event.getValue().equals("open")) {
            System.out.println("门1打开");
        } else {
            System.out.println("门1关闭");
        }
    }
}

