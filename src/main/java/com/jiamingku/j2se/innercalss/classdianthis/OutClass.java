package com.jiamingku.j2se.innercalss.classdianthis;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

/**
 * Created by jiamingku on 2018/6/17.
 */
public class OutClass {

	public static void main(String[] args) {
		OutClass out = new OutClass();
		String d = "33";
		System.out.println("================");

//        obj.displayLocvar();

		out.method("d");

		c(new Inter() {
			@Override
			public void displayLocvar() {
				System.out.println(out);
				// 匿名类访问变量 变量必须是final的
//                d = "11122";
				System.out.println("d = " + d);
			}
		});

		Collections.emptyList();


	}


	public static void c(Inter i) {
		i.displayLocvar();
	}

	public void a(String f) {
		Button button = null;
		String c = "dd";
		button.addActionListener(new ActionListener() {

			private String iiii = 3+"";
			@Override
			public void actionPerformed(ActionEvent e) {
				iiii = "sdfsdf";
				System.out.println("this.iiii = " + this.iiii);

				System.out.println("d = " + d);
				System.out.println("c = " + c);
				System.out.println("f = " + f);
				//ui.dazzle(e.getModifiers());
			}
		});
	}

	private String d = "33";
	private int aaa = 30;

	void method(final String b) {
		d = "dsdsd";
		final String c = "33";
//		c = "332323";

		new Inter() {
			@Override
			public void displayLocvar() {
				d = "dfsfsdf";
				System.out.println("c = " + c);
				System.out.println("d = " + d);
				System.out.println("aaa ++  = " + aaa ++ );
				System.out.println(b);
				System.out.println();

			}
		}.displayLocvar();
	}
}
