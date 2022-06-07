package com.jiamingku.fastjson.test.serializeable.bo;

import java.io.*;

/**
 * 学习好java 必须的会序列化机制，现在的5花8门的技术都是依赖于序列化
 * <p>
 * http://www.cnblogs.com/vinozly/p/5171227.html  ----帖子位置
 * <p>
 * 没有实现序列化接口用java的序列化机制是不行的
 */
public class BoxNoImplSerializable {

    private int width;
    private int height;

    public static void main(String[] args) {
        BoxNoImplSerializable boxNoImplSerializable = new BoxNoImplSerializable();
        boxNoImplSerializable.setWidth(50);
        boxNoImplSerializable.setHeight(30);
        try {
            FileOutputStream fs = new FileOutputStream("/Users/jiamingku/Desktop/foo.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(boxNoImplSerializable);
            os.close();
            FileInputStream fi = new FileInputStream("/Users/jiamingku/Desktop/foo.ser");
            ObjectInputStream oi = new ObjectInputStream(fi);
            BoxNoImplSerializable box = (BoxNoImplSerializable) oi.readObject();
            oi.close();
            System.out.println(box.height + "," + box.width);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}