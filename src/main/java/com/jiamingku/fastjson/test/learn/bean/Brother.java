package com.jiamingku.fastjson.test.learn.bean;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 序列化出现--Exception in thread "main" java.lang.StackOverflowError
 */
public class Brother implements Serializable {
    public int brotherId;
    public String name;
    public List<Brother> brothers;
    public Brother() {
        brothers = new ArrayList<>();
    }

    public int getBrotherId() {
        return brotherId;
    }

    public void setBrotherId(int brotherId) {
        this.brotherId = brotherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Brother> getBrothers() {
        return brothers;
    }

    public void setBrothers(List<Brother> brothers) {
        this.brothers = brothers;
    }

    public static void main(String[] args) {

        Brother b1 = new Brother();
        b1.setBrotherId(1);
        b1.setName("w1");

        Brother b2 = new Brother();
        b2.setBrotherId(2);
        b2.setName("w2");


        Brother b3 = new Brother();
        b3.setBrotherId(3);
        b3.setName("w3");


        List<Brother> list1 = new ArrayList<>();
        list1.add(b2);
        list1.add(b3);


        List<Brother> list2 = new ArrayList<>();
        list2.add(b1);
        list2.add(b3);

        List<Brother> list3 = new ArrayList<>();
        list3.add(b1);
        list3.add(b2);


        b1.setBrothers(list1);
        b2.setBrothers(list2);
        b3.setBrothers(list3);


        String s = JSONObject.toJSONString(b1, true);
        System.out.println("s = " + s);


    }

    @Override
    public String toString() {
        return "Brother{" +
                "brotherId=" + brotherId +
                ", name='" + name + '\'' +
                ", brothers=" + brothers +
                '}';
    }
}