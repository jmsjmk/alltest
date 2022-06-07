package com.jiamingku.j2se.refelct.bo;


import java.lang.reflect.ParameterizedType;

/**
 * 定义一个抽象的父类
 * 获取父类中的泛型类型 T
 */
public abstract class SuperClass<T> {

    T t;

    //    public <R> R GET() {
//        R r  = new R();
//
//    }
    // 泛型类型
    private Class<T> clazz;

    public SuperClass() {
        super();
        // 根据实现类反射获取包含泛型的父类，然后获取泛型的类型
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Class<T> getClazz() {
        return this.clazz;
    }

    public static void main(String[] args) {
        // 构造匿名子类
        SuperClass<String> superClassString = new SuperClass<String>() {
        };
        System.out.println(superClassString.getClazz()); // class java.lang.String

        // 构造匿名子类
        SuperClass<Entity> superClassEntity = new SuperClass<Entity>() {
        };
        System.out.println(superClassEntity.getClazz()); // class Entity
    }

}
