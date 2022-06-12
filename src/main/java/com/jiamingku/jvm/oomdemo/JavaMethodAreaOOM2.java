//package com.jiamingku.jvm.oomdemo;
//
//import jdk.internal.org.objectweb.asm.ClassWriter;
//import jdk.internal.org.objectweb.asm.Opcodes;
//
///**
// * 不断的创建类-然后加载-是元空间
// */
//public class JavaMethodAreaOOM2 extends ClassLoader {
//    public static void main(String[] args) {
//        int j = 0;
//        try {
//            JavaMethodAreaOOM2 javaMethodAreaOOM2 = new JavaMethodAreaOOM2();
//            for (int i = 0; i < 10000; i++) {
//                ClassWriter classWriter = new ClassWriter(0);
//                classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
//                byte[] code = classWriter.toByteArray();
//                Class c = javaMethodAreaOOM2.defineClass("Class" + i, code, 0, code.length);
//                System.out.println(c.getName());
//                j++;
//            }
//        } finally {
//            System.out.println(j);
//        }
//    }
//
//    static class OOM {
//        public String sayHello(String str) {
//            return "HI " + str;
//        }
//    }
//}