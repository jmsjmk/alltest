package com.jiamingku.j2se.remove;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Objects;

/**
 * Created by jiamingku on 2019/5/10.
 */
public class operationTest {

	/**
	 * 求模，跟除法
	 *
	 */
	@Test
	public void test1() {
		int a = 10;
		int b = 20;
		int c = 25;
		int d = 25;
		System.out.println("a + b = " + (a + b));
		System.out.println("a - b = " + (a - b));
		System.out.println("a * b = " + (a * b));
		System.out.println("b / a = " + (b / a));
		System.out.println("b % a = " + (b % a));
		System.out.println("c % a = " + (c % a));
		System.out.println("a++ = " + (a++));
		System.out.println("a-- = " + (a--));
	}

	@Test
	public void test3() {
		int a = 3;
		int b = 4;
		double d = a / b;
		System.out.println("d = " + d);

		int ddd = a /b;
		System.out.println("ddd = " + ddd);

		double dddd =  b/a;
		System.out.println("dddd = " + dddd);

		int ddddd = b/a;
		System.out.println("ddddd = " + ddddd);

		int c = 2 /2  - 1;
		int f =  2 /2 ;
		System.out.println("f = " + f);
		System.out.println("c = " + c);
		String a1= "{\"code\":402,\"data\":[],\"message\":\"签名验证未通过\",\"timestamp\":1608545165,\"version\":1,\"sign\":\"01F376D8589B6E9C4D909AA9C32807B8\"}";

		JSONObject responseBodyJson = JSON.parseObject(a1);
		String data = responseBodyJson.getString("data");
		System.out.println("data = " + data);
		String  code = responseBodyJson.getString("code");
		System.out.println("code = " + code);

		if (code.equals("402")) {
			System.out.println(" = 402.." );
		}

		JSONArray jsonArray = responseBodyJson.getJSONArray("data");

		if (jsonArray.isEmpty()) {
			System.out.println(
					"yes"
			);
		}

		if (Objects.isNull(jsonArray)) {
			System.out.println("jsonArray =33333333333333333333 " + jsonArray);
		}
		System.out.println("jsonArray = " + jsonArray);

	}

	@Test
	public void test2() {
		int a = 10;
		int b = 20;
		System.out.println("a == b = " + (a == b));
		System.out.println("a != b = " + (a != b));
		System.out.println("a > b = " + (a > b));
		System.out.println("a < b = " + (a < b));
		System.out.println("b >= a = " + (b >= a));
		System.out.println("b <= a = " + (b <= a));
	}

}
