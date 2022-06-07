package com.jiamingku.j2se.casttype;

import org.springframework.util.ClassUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by jiamingku on 2019/1/16.
 */
public class Super {

	public static int b = 10;

	static {
		b = 100;
	}
	public static int a;

	public void a() {
		System.out.println("super.a");
	}

	private void b() {

	}
	public static void main(String[] args) {


		Method[] declaredMethods = Super.class.getDeclaredMethods();

		for(Method m: declaredMethods) {
			System.out.println(" Modifier.isPrivate(m.getModifiers() = " + Modifier.isPrivate(m.getModifiers() ));

			String qualifiedMethodName = ClassUtils.getQualifiedMethodName(m);
			System.out.println(qualifiedMethodName);
			String name = m.getName();
			System.out.println(name);
			System.out.println(" ======================= " );



		}



	}
}
