package com.jiamingku.j2se.refelct.bo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParameterizedTypeBean<P1,P2> {
    // 下面的 field 的 Type 属于 ParameterizedType
    // ---如果在定义的时候指定的类型实际参数.--获取的时候会获取到
    Map<String, Person> map;
    Set<String> set1;
    Class<?> clz;
    Holder<String> holder;
    List<String> list;
    // Map<String,Person> map 这个 ParameterizedType 的 getOwnerType() 为 null，
    // 而 Map.Entry<String, String> entry 的 getOwnerType() 为 Map 所属于的 Type。
    Map.Entry<String, String> entry;
    // 下面的 field 的 Type 不属于ParameterizedType
    String str;
    Integer i;
    Set set;
    List aList;

    /** 这种获取类型时候是获取不到.*/
    Map<P1,P2> map1;

    static class Holder<V> {

    }
}

