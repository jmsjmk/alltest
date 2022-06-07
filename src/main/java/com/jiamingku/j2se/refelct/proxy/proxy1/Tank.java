package com.jiamingku.j2se.refelct.proxy.proxy1;

import java.util.Random;

/**
 * 面向接口编程
 */
public class Tank implements Moveable {

	@Override
	public void move() {
		System.out.println("Tank Moving...");
		try {
			Thread.sleep(new Random().nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
