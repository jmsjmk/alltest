

package com.jiamingku.j2se.refelct.proxy.assemble;


public class MyProxyThree implements MoveAble {
    private MoveAble targer;

    public MyProxyThree(MoveAble targer) {
        super();
        this.targer = targer;
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        System.out.println("Three start...");
        targer.move();
        System.out.println("Three end...");
    }
}
