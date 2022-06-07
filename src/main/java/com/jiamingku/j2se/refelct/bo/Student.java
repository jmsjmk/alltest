package com.jiamingku.j2se.refelct.bo;

import java.util.List;

/**
 * Created by jiamingku on 2018/10/11.
 */
public class Student {
    private Byte byte1;
    private Character char1;
    private Short short1;
    private Integer int1;
    private Long long1;
    private Float float1;
    private Double double1;
    private Boolean boolean1;

    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }


    public Student() {
    }

    public Boolean getBoolean1() {
        return boolean1;
    }

    public void setBoolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
    }

    public Byte getByte1() {
        return byte1;
    }

    public void setByte1(Byte byte1) {
        this.byte1 = byte1;
    }

    public Character getChar1() {
        return char1;
    }

    public void setChar1(Character char1) {
        this.char1 = char1;
    }

    public Double getDouble1() {
        return double1;
    }

    public void setDouble1(Double double1) {
        this.double1 = double1;
    }

    public Float getFloat1() {
        return float1;
    }

    public void setFloat1(Float float1) {
        this.float1 = float1;
    }

    public Integer getInt1() {
        return int1;
    }

    public void setInt1(Integer int1) {
        this.int1 = int1;
    }

    public Long getLong1() {
        return long1;
    }

    public void setLong1(Long long1) {
        this.long1 = long1;
    }

    public Short getShort1() {
        return short1;
    }

    public void setShort1(Short short1) {
        this.short1 = short1;
    }
}
