package com.jiamingku.fastjson.test.serializeable.base;

import com.jiamingku.fastjson.test.learn.bean.Brother;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * http://www.cnblogs.com/vinozly/p/5171227.html — 这个帖子加上下面的链接就够使用了
 * <p>
 * 参见arraylist 里面也同样包含这个方法
 * 如果一个对象，方法名字中有 writeObject/readObject  对象在序列话的时候会进行调用
 * 可以自己实现序列化方法就是写writeObject/readObject两个方法=将对应的信息输出出去
 * 也就是transient修饰的字段也可以序列化--
 */
public class TestSerializationTransient2 implements Serializable {
    private transient int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public static void main(String[] args) {
        /**
         * 构建一种交叉的依赖关系----
         */
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


        // 写入
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("/Users/jiamingku/IdeaProjects/alltest/temptest.txt"));
            ObjectOutputStream oos = null;
            ByteArrayOutputStream baos = null;
            try {
                // 序列化
                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(b1);
                byte[] bytes = baos.toByteArray();
                System.out.println("bytes.length = " + bytes.length + ",,,..b1.getBrothers().size() = " + b1.getBrothers().size());
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }
            outputStream.writeObject(b1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------------序列化完成..开始返序列话----------------------------------------");
        // 读取
        try {
            ObjectInputStream oInputStream = new ObjectInputStream(new FileInputStream("/Users/jiamingku/IdeaProjects/alltest/temptest.txt"));
            try {
                Brother aTest = (Brother) oInputStream.readObject();
                System.out.println("读取序列化后的值：" + aTest);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}