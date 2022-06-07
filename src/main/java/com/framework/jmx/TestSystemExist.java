package com.framework.jmx;

public class TestSystemExist {

    public static void main(String[] args) {
        boolean b = true;
        if (true)  {
            System.exit(1);

        }
        try {
            Thread.sleep(1000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
