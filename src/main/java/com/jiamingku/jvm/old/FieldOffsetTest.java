package com.jiamingku.jvm.old;

import sun.misc.Unsafe;
  
import java.lang.reflect.Field;  
  
/** 
 * -Xmx1024m 
 * @author tianmai.fh 
 * @date 2014-03-18 19:10 
 */  
public class FieldOffsetTest {  
    static Unsafe unsafe;  
  
    static {  
        Field field = null;  
        try {  
            field = Unsafe.class.getDeclaredField("theUnsafe");  
            field.setAccessible(true);  
            unsafe = (Unsafe) field.get(null);  
        } catch (NoSuchFieldException e) {  
            e.printStackTrace();  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
        }  
    }  
  
    static class MyClass {  
        Object a = new Object();  
        Integer b = new Integer(3);  
        int c = 4;  
        long d = 5L;  
        Long[] e = new Long[2];  
        Object[] f = new String[0];  
    }  
    static class B2 {  
        int a;  
        Integer b;  
        int c;  
    }  
  
    static long objectFieldOffset(Field field) {

        /** 获取数据的起始坐标*/
        return unsafe.objectFieldOffset(field);  
    }  
  
    static String objectFieldOffset(Class<?> clazz) {  
        Field[] fields = clazz.getDeclaredFields();  
        StringBuilder sb = new StringBuilder(fields.length * 50);  
        sb.append(clazz.getName()).append(" Field offset:\n");  
        for (Field field : fields) {  
            sb.append("\t").append(field.getType().getSimpleName());  
            sb.append("\t").append(field.getName()).append(": ");  
            sb.append(objectFieldOffset(field)).append("\n");  
        }  
        return sb.toString();  
    }  
  
    public static void main(String[] args) throws InterruptedException, NoSuchFieldException {  
        MyClass mc = new MyClass();  
        int[] big = new int[30 * 1024 * 1024];  
        big = null;  
        System.gc();  
        System.out.println(objectFieldOffset((MyClass.class)));  
        System.out.println(objectFieldOffset((B2.class)));  
        Object a = new Long[1];  
        System.out.println(Long[].class.getName());  
        Thread.sleep(1000000);  
    }  
}  