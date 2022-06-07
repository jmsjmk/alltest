package com.jiamingku.fastjson.test.learn.bean;

import java.util.Date;

/**
 * Created by jiamingku on 2020/6/7.
 */
public class AllTypeBeanClone {
    private String stringValue;
    private Integer integerValueReference;
    private int intValue;

    private Double doubleValueReference;
    private double douValue;

    private Float floatValueReference;
    private float floValue;

    private Byte byteValueReference;
    private byte byValue;

    private Date dateValue;

    public Byte getByteValueReference() {
        return byteValueReference;
    }

    public void setByteValueReference(Byte byteValueReference) {
        this.byteValueReference = byteValueReference;
    }

    public byte getByValue() {
        return byValue;
    }

    public void setByValue(byte byValue) {
        this.byValue = byValue;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public Double getDoubleValueReference() {
        return doubleValueReference;
    }

    public void setDoubleValueReference(Double doubleValueReference) {
        this.doubleValueReference = doubleValueReference;
    }

    public double getDouValue() {
        return douValue;
    }

    public void setDouValue(double douValue) {
        this.douValue = douValue;
    }

    public Float getFloatValueReference() {
        return floatValueReference;
    }

    public void setFloatValueReference(Float floatValueReference) {
        this.floatValueReference = floatValueReference;
    }

    public float getFloValue() {
        return floValue;
    }

    public void setFloValue(float floValue) {
        this.floValue = floValue;
    }

    public Integer getIntegerValueReference() {
        return integerValueReference;
    }

    public void setIntegerValueReference(Integer integerValueReference) {
        this.integerValueReference = integerValueReference;
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
        final StringBuffer sb = new StringBuffer("AllTypeBeanClone{");
        sb.append("byteValueReference=").append(byteValueReference);
        sb.append(", stringValue='").append(stringValue).append('\'');
        sb.append(", integerValueReference=").append(integerValueReference);
        sb.append(", intValue=").append(intValue);
        sb.append(", doubleValueReference=").append(doubleValueReference);
        sb.append(", douValue=").append(douValue);
        sb.append(", floatValueReference=").append(floatValueReference);
        sb.append(", floValue=").append(floValue);
        sb.append(", byValue=").append(byValue);
        sb.append(", dateValue=").append(dateValue);
        sb.append('}');
        return sb.toString();
    }
}
