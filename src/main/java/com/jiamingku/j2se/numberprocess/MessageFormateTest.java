package com.jiamingku.j2se.numberprocess;

import com.jiamingku.j2se.string.StringFormateTest;
import org.junit.Test;

import java.text.MessageFormat;

/**
 * {@link MessageFormateTest}
 * {@link NumberFormatTest}
 * {@link StringFormateTest}
 * Created by jiamingku on 2018/12/29.
 */
public class MessageFormateTest {
	// http://blog.csdn.net/zhiweianran/article/details/8666992 ---

	/**
	 * MessageFormat 提供了以与语言无关方式生成连接消息的方式。使用此方法构造向终端用户显示的消息。
	 * <p>
	 * MessageFormat 获取一组对象，格式化这些对象，然后将格式化后的字符串插入到模式中的适当位置
	 * <p>
	 * String类的format()方法用于创建格式化的字符串以及连接多个字符串对象。
	 * <p>
	 * ===================================================================
	 * 虽然都是格式化，但是string.formate主要是针对字符串进行格式化的
	 * Message操作的类型可以很多。就是同样的格式化操作,功能强大强悍一些
	 *
	 * @param args
	 */
	public static void main(String args[]) {
		String str = "今天是{0}年{1}月{2}号，天气{3}";
		String str1 = "今天是%s年%d月%d号，天气%s";
		Object[] a = {"2018", "11", "23", "晴"};
		System.out.println(MessageFormat.format(str, "2018", "11", "23", "晴"));
		System.out.println(MessageFormat.format(str, a));
		System.out.println(String.format("今天是%s年%d月%d号，天气%s", "2018", 11, 23, "晴"));
		System.out.println(String.format(str1, "2018", 11, 23, "晴"));


		// number 决定了类型
		String message = "oh, ''{0,number,#.#}'' is a pig";
		Object[] array = new Object[]{122.123123};
		String value = MessageFormat.format(message, array);
		System.out.println(value);

		// messageformate 其实相当于两种动作
		// String str = "I'm not a {0}, age is {1,number,short}", height is {2,number,#.#};
		// {0} ---用一个东西替换，并插入到 这个位置
		// {1} ---用数字替换并插入
		// {3} ---用数字并且格式化之后替换

		// {0}和{1,number,short}和{2,number,#.#};都属于FormatElement，0,1,2是ArgumentIndex。
		// {1,number,short}里面的number属于FormatType，short则属于FormatStyle。
		// {1,number,#.#}里面的#.#就属于子格式模式。
		String s = "https://booking.01zhuanche.com/car.html#/RechargeState?type=mzk";
		if (s.contains("booking")) {
			System.out.println("s = " + true);
		}

	}

	@Test
	public static void a() {

	}

	/***
	 * 下标是从0 开始的
	 */
	@Test
	public void test1() {
		String str = "5300_2_0_[{1}]_10_0";
		String s = MessageFormat.format(str, "fuck", "ddd");
		System.out.println("s = " + s);

	}

	/**
	 * 单引号问题, 双单引号替代 单引号
	 */
	@Test
	public void test2() {
		String str = "5300_2_0_[''{1}'']_10_0";
		String s = MessageFormat.format(str, "fuck", "ddd");
		System.out.println("s = " + s);
	}
}
