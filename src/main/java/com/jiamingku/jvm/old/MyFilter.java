//package com.jiamingku.jvm.old;
//
//import sun.jvm.hotspot.tools.jcore.ClassFilter;
//import sun.jvm.hotspot.oops.InstanceKlass;
//
//public class MyFilter implements ClassFilter {
//    @Override
//    public boolean canInclude(InstanceKlass kls) {
//        String klassName = kls.getName().asString();
//        return klassName.startsWith("sun/reflect/GeneratedMethodAccessor");
//    }
//}