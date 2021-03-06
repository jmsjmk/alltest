package com.framework.model.observable.event.eventtest;

public class Door1 {
    private String state = "";
    private String name = "";

    // 监听器
    private DoorStateListener stateListener;
    private DoorNameListener nameListener;


    public void setState(String newValue) {
        if (state != newValue) {
            state = newValue;
            if (stateListener != null) {
                //注意参数传递
//                System.out.println("this.getClass().getName() = " + this.getClass().getName());
                Door1Event event = new Door1Event(this, "state", newValue);
                stateListener.doorEvent(event);
            }
        }
    }

    public void setName(String newValue) {
        if (name != newValue) {
            name = newValue;
            if (nameListener != null) {
                Door1Event event = new Door1Event(this, "name", newValue);
                nameListener.doorEvent(event);
            }
        }
    }

    public void setStateListener(DoorStateListener stateListener) {
        this.stateListener = stateListener;
    }

    public void setNameListener(DoorNameListener nameListener) {
        this.nameListener = nameListener;
    }

    public String getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        String s1 = "1";
        String s2 = "1";

        System.out.println(s1 == s2);
    }
}