package com.jiamingku.fastjson.test.serializeable.bo;

import java.io.Serializable;

/**
 * Created by jiamingku on 2020/6/6.
 */
@SuppressWarnings("all")
//public class User1 implements Serializable {
    public class User1  {

    private String name;
    private int intValue;
    private String password;

    public User1(int intValue, String name, String password) {
        this.intValue = intValue;
        this.name = name;
        this.password = password;
    }

    public int getIntValue() {
        System.out.println(" getIntValue method is invoke ");
        return intValue;
    }

    public void setIntValue(int intValue) {
        System.out.println(" setIntValue method is invoke ");
        this.intValue = intValue;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User1{");
        sb.append("intValue=").append(intValue);
        sb.append(", name='").append(name).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
