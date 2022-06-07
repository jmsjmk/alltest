package com.jiamingku.fastjson.test.serializeable.base;

import com.google.common.base.Objects;

import java.io.*;


/**
 * http://www.cnblogs.com/vinozly/p/5171227.html — 这个帖子加上下面的链接就够使用了
 * <p>
 * 参见arraylist 里面也同样包含这个方法
 * 如果一个对象，方法名字中有 writeObject/readObject  对象在序列话的时候会进行调用
 * 可以自己实现序列化方法就是写writeObject/readObject两个方法=将对应的信息输出出去
 * 也就是transient修饰的字段也可以序列化--
 * <p>
 * 对于非transient属性来说，如果也像写出去的话，使用：s.defaultWriteObject();
 */
public class TestSerializationTransient implements Serializable {
    private String age;
    private transient int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    /**
     * 只要 写了这2个方法就可以回掉, 写什么序列化回来什么
     *
     * @param s
     * @throws java.io.IOException
     */
    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
        s.defaultWriteObject();
        s.writeObject(num);
        System.out.println("writeObject of " + this.getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
        s.defaultReadObject();
        num = (Integer) s.readObject();
        System.out.println("readObject of " + this.getClass().getName());
    }

    public static void main(String[] args) {
        TestSerializationTransient test = new TestSerializationTransient();
        test.setNum(10);
        test.age = 100 + "";
        System.out.println("序列化之前的值：" + test.getNum());
        System.out.println(test.toString());
        // 写入
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("/Users/jiamingku/IdeaProjects/alltest/temptest.txt"));
            outputStream.writeObject(test);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 读取
        try {
            ObjectInputStream oInputStream = new ObjectInputStream(new FileInputStream("/Users/jiamingku/IdeaProjects/alltest/temptest.txt"));
            try {
                TestSerializationTransient aTest1 = (TestSerializationTransient) oInputStream.readObject();
                System.out.println("读取序列化后的值：" + aTest1.getNum());
                System.out.println(aTest1.age);
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
                .add("num", num)
                .add("age", age)
                .toString();
    }
}