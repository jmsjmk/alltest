package com.jiamingku.md5;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * Created by jiamingku on 2019/6/12.
 */
public class Md5BaseUse {

	/**
	 * 基本的使用md5
	 * =https://blog.csdn.net/jiamingku/article/details/91564575
	 * 第一步：创建对象(指定算法md5,sha)-- MessageDigest md = MessageDigest.getInstance("MD5");
	 * 第二步：获得进行加密串的字节数组， 这个地方要注意就是别获取少了尽量用大的字符集去获取字节数组
	 * 第三步：将加密之后的数据返回在转换成 16进制的，返回一个字符串-因为md5之后返回的是字节数组
	 * 第四步： 一般用messageDigest返回的字节数组，会将每一个字节转换成为对应16进制数字(重点**************************)
	 * <p>
	 * // ---------------------------
	 * 总结：算法是jdk提供的，我们只是在上面进行了一些边缘操作，
	 * 1.获取byte[]数组
	 * 2.加密的结果返回16进制的数
	 * <p>
	 * <p>
	 * {@link MessageDigest#digest()}
	 * {@link MessageDigest#update(byte[])}
	 * {@link MessageDigest#reset()}
	 */
	@Test
	public void testMd5() {
		String source = "123";
		byte[] result;
		try {
			// 1.
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");

			// 2.
			messageDigest.update(source.getBytes("UTF-8"));

			// 3.
			result = messageDigest.digest();

			System.out.println("result.length = " + result.length);
			System.out.println("new String(result) = " + new String(result));
			// 2. 3.步骤其实可以变化成为一个步骤
			result = messageDigest.digest(source.getBytes("UTF-8"));

			System.out.println("result.length = " + result.length);
			System.out.println("new String(result) = " + new String(result));
			String s = byteToHexString(result);
			System.out.println("s = " + s + "\t" + s.toCharArray().length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testLength() {
		try {
			String a = "123";

			byte[] bytes = a.getBytes("utf-8");

			System.out.println("bytes = " + bytes.length);

			byte b1 = 12;
			byte b2 = 23;
//			byte b3 = b1 & b2;

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 关于salt的测试, salt就是防止暴力破解弄的这么一个东西+点佐料，方便快速的弄
	 * 1.其实本质上 messageDigest.update(s1.getByte), messageDigest.update(s2.getByte);
	 * 等同于 messageDigest.update((s1+s2).getByte);
	 * <p>
	 * 2.所以盐在前面+，还是合并起来一起+都是一样的效果
	 */
	@Test
	public void testSalt() {
		try {
			String source = "1234";
			String salt = "nihao";
			byte[] result;
			// 1.
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");

			// 2.
			messageDigest.update(source.getBytes("UTF-8"));
			messageDigest.update(salt.getBytes());
			// 3.
			result = messageDigest.digest();

			System.out.println("result.length = " + result.length);

			// 2. 3.步骤其实可以变化成为一个步骤
			result = messageDigest.digest(source.getBytes("UTF-8"));
			String s = byteToHexString(result);
			System.out.println("s = " + s + "\t" + s.toCharArray().length);

			System.out.println(" ====================================================== ");
			/**
			 * 通过update()方法处理数据,任何时候都可以通过reset()方法重置摘要,
			 * 一旦所有更新数据都更新完了,应该调用digest()方法完成哈希计算,
			 * 对于定量的数据计算,digest()方法只能被调用一次,MessageDigest对象恢复到初始状态
			 *
			 * 在digest 之后就 整型reset其实没啥效果。在执行之前有效果。
			 */
			messageDigest.reset();
			messageDigest.update((source + salt).getBytes("UTF-8"));
			result = messageDigest.digest();
			System.out.println("result.length = " + result.length);
			// 2. 3.步骤其实可以变化成为一个步骤
			result = messageDigest.digest(source.getBytes("UTF-8"));
			s = byteToHexString(result);
			System.out.println("s = " + s + "\t" + s.toCharArray().length);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("all")
	public static String byteToHexString(byte[] b) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			hexString.append(hex.toUpperCase());
		}
		return hexString.toString();
	}

	public static void main(String[] args) {
		byte[] b = {1};
		int a = b[0] & 0xFF;
		System.out.println("a = " + a);
		String hex = Integer.toHexString(b[0] & 0xFF);
		System.out.println("hex = " + hex);


		System.out.println("-1 & 0xFF = " + (-1 & 0xFF));
	}
}
