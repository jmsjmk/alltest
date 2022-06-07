package com.jiamingku.jvm.oomdemo;

/**
 * AQS中也出现过这个问题，但是那个是静态的属性
 */
public class JavaVMStackSOF1 {
    public static void main(String[] args) {
        JavaVMStackSOF1 a1 = new JavaVMStackSOF1();
        System.out.println("a1 = ");
    }

    public JavaVMStackSOF1() {
    }

    /**
     * 递归创建了----...
     */
    JavaVMStackSOF1 A1 = new JavaVMStackSOF1();
}

