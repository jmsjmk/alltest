package com.framework.model.observable.event.eventtest;

import java.util.EventListener;

public class DoorNameListener implements EventListener {
    public void doorEvent(Door1Event event) {
        Door1 door = (Door1) event.getSource();
        System.out.println("I got a new name,named \"" + door.getName() + "\"");
    }
}