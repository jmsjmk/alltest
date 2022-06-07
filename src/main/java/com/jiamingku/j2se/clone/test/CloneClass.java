//package com.jiamingku.j2se.clone.test;
//
//import typeinfo.ClassCasts;
//
//public class CloneClass implements Cloneable {
//    public int aInt;
//
////    public Object clone() {
////        CloneClass o = null;
////        try {
////            o = (CloneClass) super.clone();
////        } catch (CloneNotSupportedException e) {
////            e.printStackTrace();
////        }
////        return o;
////    }
//
//    public static void main(String[] args) throws Exception {
//        CloneClass cloneClass = new CloneClass();
//
//        cloneClass.aInt = 1000;
//
//        Object o =  cloneClass.clone();
//
//        System.out.println("o.getClass().getSimpleName() = " + o.getClass().getSimpleName());
//        System.out.println("o = " + o);
//
//    }
//
//    @Override
//    public String toString() {
//        final StringBuffer sb = new StringBuffer("CloneClass{");
//        sb.append("aInt=").append(aInt);
//        sb.append('}');
//        return sb.toString();
//    }
//}