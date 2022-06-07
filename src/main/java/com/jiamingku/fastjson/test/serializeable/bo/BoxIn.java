package com.jiamingku.fastjson.test.serializeable.bo;

import com.google.common.base.Objects;

import java.io.*;

/**
 * 学习好java 必须的会序列化机制，现在的5花8门的技术都是依赖于序列化
 *
 * http://www.cnblogs.com/vinozly/p/5171227.html  ----帖子位置
 *
 * 1.初级使用 <br/>
 *
 * 2.依赖于OjbectOutputStream
 *
 */
public class BoxIn  {
    private static final long serialVersionUID = -3450064362986273896L;
    
    private int width;
    private int height;
    
    public static void main(String[] args) {
        BoxIn myBox=new BoxIn();
        myBox.setWidth(50);
        myBox.setHeight(30);
        try {
            FileOutputStream fs=new FileOutputStream("/Users/jiamingku/Desktop/foo.ser");
            ObjectOutputStream os=new ObjectOutputStream(fs);
            os.writeObject(myBox);
            os.close();
            FileInputStream fi=new FileInputStream("/Users/jiamingku/Desktop/foo.ser");
            ObjectInputStream oi=new ObjectInputStream(fi);
            BoxIn box=(BoxIn)oi.readObject();
            oi.close();
            System.out.println(box.height+","+box.width);
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

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("width", width)
                .add("height", height)
                .toString();
    }
}