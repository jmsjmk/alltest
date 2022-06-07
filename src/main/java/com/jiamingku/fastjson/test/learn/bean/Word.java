package com.jiamingku.fastjson.test.learn.bean;

import com.jiamingku.fastjson.test.learn.PropertyFilterTest;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Word {

    private String d;
    private String e;
    private String f;
    private String a;
    private int b;
    private Integer integerValue;

    public Integer getIntegerValue() {
        return integerValue;
    }

    public void setIntegerValue(Integer integerValue) {
        this.integerValue = integerValue;
    }

    private boolean c;
    private Date date;
    private Map<String , Object> map;
    private List<PropertyFilterTest> list;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public boolean isC() {
        return c;
    }

    public void setC(boolean c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public List<PropertyFilterTest> getList() {
        return list;
    }

    public void setList(List<PropertyFilterTest> list) {
        this.list = list;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}