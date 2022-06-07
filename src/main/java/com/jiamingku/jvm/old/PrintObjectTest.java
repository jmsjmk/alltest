//package com.jiamingku.jvm.old;
//
//import sun.jvm.hotspot.oops.*;
//import sun.jvm.hotspot.runtime.VM;
//import sun.jvm.hotspot.tools.Tool;
//import sun.jvm.hotspot.utilities.SystemDictionaryHelper;
//
///**
// * 打印对象的内存布局
// */
//public class PrintObjectTest extends Tool {
//    public static void main(String[] args) throws InterruptedException {
//        PrintObjectTest test = new PrintObjectTest();
//        test.start(args);
//        test.stop();
//    }
//
//    @Override
//    public void run() {
//        VM vm = VM.getVM();
//        ObjectHeap objHeap = vm.getObjectHeap();
//        HeapVisitor heapVisitor = new HeapPrinter(System.out);
//        //观察特定对象
//        Klass klass = SystemDictionaryHelper.findInstanceKlass("FieldOffsetTest$MyClass");
//        objHeap.iterateObjectsOfKlass(heapVisitor, klass, false);
//
//        //观察数组对象
//        objHeap.iterate(heapVisitor,new ObjectHeap.ObjectFilter() {
//            @Override
//            public boolean canInclude(Oop oop) {
//                return oop.isObjArray();
//            }
//        });
//        objHeap.iterate(heapVisitor);
//    }
//}