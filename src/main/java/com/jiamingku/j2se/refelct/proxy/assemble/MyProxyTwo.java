package com.jiamingku.j2se.refelct.proxy.assemble;


public class MyProxyTwo implements MoveAble {

    private MoveAble targer;

    public MyProxyTwo(MoveAble targer) {
        super();
        this.targer = targer;
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        System.out.println("two start...");
        targer.move();
        System.out.println("two end...");
    }
}
