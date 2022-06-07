package com.jiamingku.fastjson.test.learn.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class DateTestDemo {
    String name;

    private Date date;

    @JSONField(format="yyyyMMdd")
    private Date date1;


    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date date2;

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}