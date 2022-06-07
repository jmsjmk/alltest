package com.jiamingku.j2se.oop.superthis.construct;

/**
 * Created by jiamingku on 2019/11/4.
 */
public class Ss extends S {
    public void t() {
        System.out.println("super.getClass().getName() = " + super.getClass().getName());
        super.t();
    }
    public static void main(String[] args) {
        Ss s = new Ss();
        System.out.println(" ================================ ");
        s.t();
    }
}
