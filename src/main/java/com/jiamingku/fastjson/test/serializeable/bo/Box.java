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
public class Box implements Serializable {
    private static final long serialVersionUID = -3450064362986273896L;
    
    private int width;
    private int height;

    private BoxIn boxIn;

    public BoxIn getBoxIn() {
        return boxIn;
    }

    public void setBoxIn(BoxIn boxIn) {
        this.boxIn = boxIn;
    }

    public static void main(String[] args) {
        Box myBox=new Box();
        myBox.setWidth(50);
        myBox.setHeight(30);
        BoxIn boxIn = new BoxIn();
        boxIn.setWidth(50);
        boxIn.setHeight(30);
        myBox.setBoxIn(boxIn);

        try {
            FileOutputStream fs=new FileOutputStream("/Users/jiamingku/Desktop/foo.ser");
            ObjectOutputStream os=new ObjectOutputStream(fs);
            os.writeObject(myBox);
            os.close();
            FileInputStream fi=new FileInputStream("/Users/jiamingku/Desktop/foo.ser");
            ObjectInputStream oi=new ObjectInputStream(fi);
            Box box=(Box)oi.readObject();
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
                .add("boxIn", boxIn)
                .toString();
    }
}