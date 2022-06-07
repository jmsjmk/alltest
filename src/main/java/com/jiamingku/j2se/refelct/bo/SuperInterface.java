package com.jiamingku.j2se.refelct.bo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public interface SuperInterface<T> {

    @SuppressWarnings("unchecked")
    default Class<T> getEntityClass() {
        Type[] types = getClass().getGenericInterfaces();
        for (Type type : types) {
            if (type.getTypeName().startsWith(SuperInterface.class.getName())) {
                ParameterizedType pt = (ParameterizedType) type;
                return (Class<T>)pt.getActualTypeArguments()[0];
            }
        }
        return null;
    }
}


