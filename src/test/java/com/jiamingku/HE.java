package com.jiamingku;

import com.google.common.base.Objects;
import sun.reflect.Reflection;

/**
 * Created by jiamingku on 2018/6/23.
 */
public class HE {

    private String age;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HE)) return false;
        HE he = (HE) o;
        return Objects.equal(age, he.age) && Objects.equal(name, he.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(age, name);
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Class<?> callerClass = Reflection.getCallerClass(0);
        System.out.println(callerClass);

        callerClass = Reflection.getCallerClass(1);
        System.out.println(callerClass);


        callerClass = Reflection.getCallerClass(2);
        System.out.println(callerClass);

    }
}
