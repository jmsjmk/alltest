package com.jiamingku.j2se.casttype;

import java.lang.reflect.Constructor;

/**
 * Created by jiamingku on 2019/3/25.
 *
 * 这就是多重绑定的概念。
 *
 */
public class ResultTest<T extends Super & A> {

	public static void main(String[] args) throws  Exception{
		ResultTest<SonCastTypeTest> resultTest = new ResultTest<>();
		// 下面的这条语句错误，这个类型必须实现 super 与A 才可以使用
		//  ResultTest<Son1> resultTest1 = new ResultTest<>();

		/**
		 * 一下看类的导航图
		 */
		Class<Object> objectClass = Object.class;


		Constructor<Object> constructor = objectClass.getConstructor();


	}
}
