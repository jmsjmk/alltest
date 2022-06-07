package com.jiamingku.fastjson.test.learn.bean;

import java.util.Date;

/**
 * Created by jiamingku on 2020/6/7.
 */
public class AllTypeBean {
    private String stringValue;
    private Integer integerReferenceValue;
    private int intValue;

    private Double doubleReferenceValue;
    private double douValue;

    private Float floatReferenceValue;
    private float floatValue;

    private Byte byteReferenceValue;
    private byte byteValue;

    private Date dateValue;

    public Byte getByteReferenceValue() {
        return byteReferenceValue;
    }

    public void setByteReferenceValue(Byte byteReferenceValue) {
        this.byteReferenceValue = byteReferenceValue;
    }

    public byte getByteValue() {
        return byteValue;
    }

    public void setByteValue(byte byteValue) {
        this.byteValue = byteValue;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public Double getDoubleReferenceValue() {
        return doubleReferenceValue;
    }

    public void setDoubleReferenceValue(Double doubleReferenceValue) {
        this.doubleReferenceValue = doubleReferenceValue;
    }

    public double getDouValue() {
        return douValue;
    }

    public void setDouValue(double douValue) {
        this.douValue = douValue;
    }

    public Float getFloatReferenceValue() {
        return floatReferenceValue;
    }

    public void setFloatReferenceValue(Float floatReferenceValue) {
        this.floatReferenceValue = floatReferenceValue;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
    }

    public Integer getIntegerReferenceValue() {
        return integerReferenceValue;
    }

    public void setIntegerReferenceValue(Integer integerReferenceValue) {
        this.integerReferenceValue = integerReferenceValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AllTypeBean{");
        sb.append("byteReferenceValue=").append(byteReferenceValue);
        sb.append(", stringValue='").append(stringValue).append('\'');
        sb.append(", integerReferenceValue=").append(integerReferenceValue);
        sb.append(", intValue=").append(intValue);
        sb.append(", doubleReferenceValue=").append(doubleReferenceValue);
        sb.append(", douValue=").append(douValue);
        sb.append(", floatReferenceValue=").append(floatReferenceValue);
        sb.append(", floatValue=").append(floatValue);
        sb.append(", byteValue=").append(byteValue);
        sb.append(", dateValue=").append(dateValue);
        sb.append('}');
        return sb.toString();
    }
}
