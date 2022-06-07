package com.jiamingku.fastjson.test.learn.bean;

import com.google.common.base.Objects;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 序列化出现--Exception in thread "main" java.lang.StackOverflowError
 */
public class Brother2 implements Serializable {
    public int brotherId;
    public String name;
    private int size;
    public List<Brother2> brothers;

    public Brother2() {
        brothers = new ArrayList<>();
    }

    public int getBrotherId() {
        return brotherId;
    }

    public void setBrotherId(int brotherId) {
        this.brotherId = brotherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Brother2> getBrothers() {
        return brothers;
    }

    public void setBrothers(List<Brother2> brothers) {
        this.brothers = brothers;
    }


    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException {
        s.defaultWriteObject();
        size = brothers.size();
        for (int i = 0; i < brothers.size(); i++) {
            s.writeObject(brothers.get(i));
        }
    }

    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        s.defaultReadObject();
        System.out.println("size = " + size);
        List<Object> a = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Object aa = s.readObject();
            a.add(aa);
        }
        brothers = (List) a;
    }

    public static void main(String[] args) {

        Brother2 b1 = new Brother2();
        b1.setBrotherId(1);
        b1.setName("w1");

        Brother2 b2 = new Brother2();
        b2.setBrotherId(2);
        b2.setName("w2");

        List<Brother2> list1 = new ArrayList<>();
        list1.add(b2);

        List<Brother2> list2 = new ArrayList<>();
        list2.add(b1);

        b1.setBrothers(list1);
        b2.setBrothers(list2);

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
        System.out.println("b1 = " + b1);
        System.out.println("---------------------------序列化完成..开始返序列话----------------------------------------");
        // 读取
        try {
            ObjectInputStream oInputStream = new ObjectInputStream(new FileInputStream("/Users/jiamingku/IdeaProjects/alltest/temptest.txt"));
            try {
                Brother2 aTest = (Brother2) oInputStream.readObject();
                String sssss =aTest.getBrothers().get(0).toString();
                System.out.println("sssss = " + sssss);
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

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("brotherId", brotherId)
                .add("name", name)
                .add("size", size)
                .add("brothers", brothers.size())
                .toString();
    }
}