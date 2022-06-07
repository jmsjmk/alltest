package com.framework.model.observable.event.observable;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        long a = 0B11;
        System.out.println("a = " + a);

        System.out.println(" ======================= ");
        Publish publish = new Publish();
        Subscribe subscribe = new Subscribe(publish);
        
        publish.setData("开始");
    }

}