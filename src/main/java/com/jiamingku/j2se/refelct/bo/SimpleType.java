package com.jiamingku.j2se.refelct.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * 8种类型
 * Created by jiamingku on 2018/5/26.
 */
public class SimpleType {

    static {
        System.out.println("dddd");
    }

    public static void main(String[] args) {
        Class c = SimpleType.class;

    }

    private byte byte1;
    private char char1;
    private short short1;
    private int int1;
    private long long1;
    private float float1;
    private double double1;
    private boolean boolean1;

    private List list;

    private List list1 = new ArrayList<>();

    private List list2 = new ArrayList<>();

    public List getList2() {
        return list2;
    }

    public void setList2(List list2) {
        System.out.println(" list2 is excute.. ");
        this.list2 = list2;
    }

//    public List getList1() {
//        return list1;
//    }
//
//    public void setList1(List list1) {
//        this.list1 = list1;
//    }

    public SimpleType(boolean boolean1, byte byte1, char char1, double double1, float float1, int int1, long long1, short
            short1) {
        this.boolean1 = boolean1;
        this.byte1 = byte1;
        this.char1 = char1;
        this.double1 = double1;
        this.float1 = float1;
        this.int1 = int1;
        this.long1 = long1;
        this.short1 = short1;
    }


//    public SimpleType() {
//    }

    public boolean isBoolean1() {
        return boolean1;
    }

    public void setBoolean1(boolean boolean1) {
        System.out.println(" = is invoken ");
        this.boolean1 = boolean1;
    }


    public List getList() {
        System.out.println("序列化时候调用这个....");
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public byte getByte1() {
        return byte1;
    }

    public void setByte1(byte byte1) {
        System.out.println(" setByte1= is invoke ");
        this.byte1 = byte1;
    }

    public char getChar1() {
        return char1;
    }

    public void setChar1(char char1) {
        this.char1 = char1;
    }

    public double getDouble1() {
        return double1;
    }

    public void setDouble1(double double1) {
        this.double1 = double1;
    }

    public float getFloat1() {
        return float1;
    }

    public void setFloat1(float float1) {
        this.float1 = float1;
    }

    public int getInt1() {
        return int1;
    }

    public void setInt1(int int1) {
        this.int1 = int1;
    }

    public long getLong1() {
        return long1;
    }

    public void setLong1(long long1) {
        this.long1 = long1;
    }

    public short getShort1() {
        return short1;
    }

    public void setShort1(short short1) {
        this.short1 = short1;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SimpleType{");
        sb.append("boolean1=").append(boolean1);
        sb.append(", byte1=").append(byte1);
        sb.append(", char1=").append(char1);
        sb.append(", short1=").append(short1);
        sb.append(", int1=").append(int1);
        sb.append(", long1=").append(long1);
        sb.append(", float1=").append(float1);
        sb.append(", double1=").append(double1);
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}
