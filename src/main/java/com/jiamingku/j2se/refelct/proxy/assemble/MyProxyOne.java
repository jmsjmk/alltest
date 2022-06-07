

package com.jiamingku.j2se.refelct.proxy.assemble;


public class MyProxyOne implements MoveAble {

    private MoveAble targer;

    public MyProxyOne(MoveAble targer) {
        super();
        this.targer = targer;
    }

    @Override
    public void move() {
        System.out.println("one start...");
        targer.move();
        System.out.println("one end...");
    }
}
