package com.jiamingku.fastjson.test.serializeable.bo;

import java.io.Serializable;

/**
 * Created by jiamingku on 2020/6/6.
 */
@SuppressWarnings("all")
public class UserContainStatic implements Serializable {

    private static String name="dfal";
    private int intValue;
    private transient String password;

    public UserContainStatic(int intValue, String name, String password) {
        this.intValue = intValue;
        this.name = name;
        this.password = password;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
