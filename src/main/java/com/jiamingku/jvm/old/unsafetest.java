package com.jiamingku.jvm.old;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by jiamingku on 2018/9/16.
 */
public class unsafetest {
    public static void main(String[] args) throws Exception {

        Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);
        // return (Unsafe) theUnsafeInstance.get(null);是等价的
        Unsafe unsafe = (Unsafe) theUnsafeInstance.get(Unsafe.class);


        System.out.println(unsafe.objectFieldOffset(VO.class.getDeclaredField("a")));
        System.out.println(unsafe.objectFieldOffset(VO.class.getDeclaredField("b")));
        System.out.println(unsafe.objectFieldOffset(VO.class.getDeclaredField("dd")));

// fieldOffset与objectFieldOffset功能一样,fieldOffset是过时方法,最好不要再使用
        System.out.println(unsafe.fieldOffset(VO.class.getDeclaredField("b")));
    }
}
