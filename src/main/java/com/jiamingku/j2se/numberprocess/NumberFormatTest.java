package com.jiamingku.j2se.numberprocess;

import com.jiamingku.j2se.string.StringFormateTest;
import org.junit.Test;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * {@link MessageFormateTest}
 * {@link NumberFormatTest}
 * {@link StringFormateTest}
 * Created by jiamingku on 2018/12/29.
 */
public class NumberFormatTest {

	public static void main(String[] args) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setRoundingMode(RoundingMode.HALF_UP);//采用舍入模式
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		String nf1 = nf.format(218.516F);//格式化的数字
		System.out.println("value is:" + nf1);//218.52
		System.out.println("==============================");
		NumberFormat nf222222 = NumberFormat.getInstance();//默认的舍入模式(四舍五入的方式)
		nf222222.setMaximumFractionDigits(4);
		nf222222.setMinimumFractionDigits(2);
		String val11111 = nf222222.format(218.53336316F);//格式化的数字
		System.out.println("value is:" + val11111);//218.52
	}

	/**
	 * 格式化消暑设置千分符号的问题
	 * 格式化包含千分符号的问题
	 */
	@Test
	public void testThousand() {
		try {
			NumberFormat numberFormat1 = NumberFormat.getNumberInstance();
			System.out.println(numberFormat1.format(11122.33)); //结果是11,122.33

			NumberFormat numberFormat2 = NumberFormat.getNumberInstance();
			numberFormat2.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
			System.out.println(numberFormat2.format(11122.33)); //结果是11122.33

			System.out.println(" ================================== ");
			String amount1 = "13,000.00";
			double d1 = new DecimalFormat().parse(amount1).doubleValue(); //这里使用的是parse，不是format
			System.out.println(String.valueOf(d1)); //结果是13000.00
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 格式化各种数字出现异常的情况
	 */
	@Test
	public void testException() {
//        NumberFormat nf = NumberFormat.getInstance();
		Integer nf = new Integer(200);
		int a = 0;
		a = Integer.parseInt("0", 10); //返回 0
		System.out.println("a = " + a);
		a = Integer.parseInt("473", 10); // 返回 473
		System.out.println("a = " + a);
		a = Integer.parseInt("-0", 10); // 返回 0
		System.out.println("a = " + a);
		a = Integer.parseInt("-FF", 16); // 返回 -255
		System.out.println("a = " + a);
		a = Integer.parseInt("1100110", 2); // 返回 102
		System.out.println("a = " + a);
		a = Integer.parseInt("2147483647", 10); // 返回 2147483647
		System.out.println("a = " + a);
		a = Integer.parseInt("-2147483648", 10); //返回 -2147483648
		System.out.println("a = " + a);
		a = Integer.parseInt("2147483648", 10); //抛出 NumberFormatException
		System.out.println("a = " + a);
		a = Integer.parseInt("99", 8); // 抛出 NumberFormatException
		System.out.println("a = " + a);
		a = Integer.parseInt("Kona", 10); // 抛出 NumberFormatException
		System.out.println("a = " + a);
		a = Integer.parseInt("Kona", 27); //返回 411787
		System.out.println("a = " + a);
	}
}
