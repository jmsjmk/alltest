package com.jiamingku.fastjson.test.serializeable.base;

import com.jiamingku.fastjson.test.serializeable.bo.*;
import org.junit.Test;

import java.io.*;

/**
 * Created by jiamingku on 2020/6/6.
 * 1.序列化,----> 构造器,set,get方法没关系
 * 2.静态, transient 不会被序列化
 * 3.序列化如果里面包含的引用对象,被包含的对象也必须是序列化的
 * 4.灵活的方法
 * 4.1 private void writeObject(ObjectOutputStream s)
 * 4.2 private void readObject(ObjectInputStream s)
 *
 * ===
 * 5.序列化的时候,包含子类的关系时候子类也必须实现序列化接口(BoxIn)
 * 6.序列化时候会出现 Exception in thread "main" java.lang.StackOverflowError （TestSerializationTransient2）
 * 默认属性:s.defaultWriteObject();
 */
@SuppressWarnings("all")
public class BaseSerializableTest {

    // -----------------------java序列化比较严格,属性调整顺序没问题------------------
    // -----------------------增加方法不行-get/set方法也不行,增加属性不行 ------------------
    @Test
    public void testSerializableWrite2122() throws Exception {
        // 1.指定输出文件
        FileOutputStream fileOutputStream = new FileOutputStream("./user1244431.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        UserCloneNoGetSetConstractorOther user = new UserCloneNoGetSetConstractorOther(1, "name");
        System.out.println(user);
        // 2.发送出去
        objectOutputStream.writeObject(user);
    }

    @Test
    public void testSerializableRead211() throws Exception {
        FileInputStream fileOutputStream = new FileInputStream("./user1244431.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileOutputStream);
        UserCloneNoGetSetConstractorOther user = (UserCloneNoGetSetConstractorOther) objectInputStream.readObject();
        System.out.println("user = " + user);
    }


    // -----------------------java序列化与构造器-get/set方法没关系------------------
    // -----------------------java序列化与构造器-get/set方法没关系------------------
    // -----------------------java序列化与构造器-get/set方法没关系------------------
    // -----------------------java序列化与构造器-get/set方法没关系------------------
    // -----------------------java序列化与构造器-get/set方法没关系------------------
    @Test
    public void testSerializableRead2() throws Exception {

        // 序列化
        FileOutputStream fileOutputStream = new FileOutputStream("./user1244431.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        UserCloneNoGetSetConstractor user = new UserCloneNoGetSetConstractor(1, "name");
        System.out.println("序列化:::" + user);
        objectOutputStream.writeObject(user);


        // 反序列化
        FileInputStream fileOutputStream1 = new FileInputStream("./user1244431.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileOutputStream1);
        UserCloneNoGetSetConstractor user1 = (UserCloneNoGetSetConstractor) objectInputStream.readObject();
        System.out.println("反序列化:user1 = " + user1);
    }

    @Test
    public void testSerializableRead21() throws Exception {
        UserCloneNoGetSetNoConstractor userCloneNoGetSetNoConstractor = new UserCloneNoGetSetNoConstractor();
        userCloneNoGetSetNoConstractor.age = 1;
        userCloneNoGetSetNoConstractor.name = "name";

        // 序列化
        FileOutputStream fileOutputStream = new FileOutputStream("./user12444311.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        System.out.println("序列化:::" + userCloneNoGetSetNoConstractor);
        objectOutputStream.writeObject(userCloneNoGetSetNoConstractor);

        // 反序列化
        FileInputStream fileOutputStream1 = new FileInputStream("./user12444311.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileOutputStream1);
        UserCloneNoGetSetNoConstractor user1 = (UserCloneNoGetSetNoConstractor) objectInputStream.readObject();
        System.out.println("反序列化:user1 = " + user1);
    }

    /**
     * @see User1
     * 1)没有无参数构造器.
     * 2)反序列化时候--get/set方法也没有被调用
     * ***点就是包名-必须等于全限定名字
     */
    @Test
    public void test1() {
        User1 user1 = new User1(1, "name", "pass");
        String filePath = "./1.obj";
        ObjectOutputStream outStream;
        ObjectInputStream inStream;
        try {
            outStream = new ObjectOutputStream(new FileOutputStream(filePath));
            outStream.writeObject(user1);
            inStream = new ObjectInputStream(new FileInputStream(filePath));
            com.jiamingku.fastjson.test.serializeable.bo.p.User1 readObject = (com.jiamingku.fastjson.test.serializeable.bo.p.User1) inStream.readObject();
            System.out.println("反序列化回来==：" + readObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 1.transient 关键字-不会被序列化,可以通过其他方式重写方法进行序列化
     */
    @Test
    public void test2Transient() {
        UserContainTransient user1 = new UserContainTransient(1, "name", "pass");
        String filePath = "./2.obj";
        ObjectOutputStream outStream;
        ObjectInputStream inStream;
        try {
            System.out.println("序列化前：" + user1.toString());
            outStream = new ObjectOutputStream(new FileOutputStream(filePath));
            outStream.writeObject(user1);

            inStream = new ObjectInputStream(new FileInputStream(filePath));
            UserContainTransient readObject = (UserContainTransient) inStream.readObject();
            System.out.println("反序列化后：" + readObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * transient 关键字-不会被序列化-=---静态的也不被序列化的
     */
    @Test
    public void testStatic() {
        UserContainStatic user1 = new UserContainStatic(1, "name", "pass");
        String filePath = "./3.obj";
        ObjectOutputStream outStream;
        ObjectInputStream inStream;
        try {
            System.out.println("序列化前：" + user1.toString());
            outStream = new ObjectOutputStream(new FileOutputStream(filePath));
            outStream.writeObject(user1);
            /**
             * jvm 中已经包含这个属性了,--所以静态是不会被序列化的，在返序列化的时候这个值的就变化成为当前值
             */
            user1.setName("10000");
            inStream = new ObjectInputStream(new FileInputStream(filePath));
            UserContainStatic readObject = (UserContainStatic) inStream.readObject();
            System.out.println("序列化后：" + readObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 静态属性-是不会被序列化回来的---但是类已经jvm装载之后-在反序列化是可以显示出来的-
     */
    @Test
    public void testFromStatic() {
        String filePath = "./3.obj";
        ObjectOutputStream outStream;
        ObjectInputStream inStream;
        try {
            inStream = new ObjectInputStream(new FileInputStream(filePath));
            UserContainStatic readObject = (UserContainStatic) inStream.readObject();
            System.out.println("序列化后：" + readObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void testserialVersionUIDWrite() throws Exception {
        // 创建User实例
        User user = new User("小白", 3, 99);
        System.out.println("序列化前：" + user.toString());
        FileOutputStream fileOutputStream = new FileOutputStream("./user123.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

        outputStream.writeObject(user);
        // 关闭流
        outputStream.close();
        fileOutputStream.close();
    }

    @Test
    public void testserialVersionUIDRead() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("./user123.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        User user = (User) objectInputStream.readObject();
        System.out.println(user);
        objectInputStream.close();
        fileInputStream.close();
    }
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 区别fastjson,属性相同就可以直接反序列化, 对于java来收必须包含全制定名
     * 这么看来fastjson真的挺好用的
     *
     * @throws Exception
     */
    @Test
    public void testSerializableRead1() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("./user123.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        UserClone user = (UserClone) objectInputStream.readObject();
        System.out.println(user);
        objectInputStream.close();
        fileInputStream.close();
    }

    @Test
    public void testserialVersionUIDWrite1() throws Exception {
        // 创建User实例
        User1 user = new User1(1, 3 + "", 99 + "");
        System.out.println("序列化前：" + user.toString());
        FileOutputStream fileOutputStream = new FileOutputStream("./user123.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

        outputStream.writeObject(user);
        // 关闭流
        outputStream.close();
        fileOutputStream.close();


        FileInputStream fileInputStream = new FileInputStream("./user123.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        User1 user12 = (User1) objectInputStream.readObject();
        System.out.println(user);
        objectInputStream.close();
        fileInputStream.close();
    }
}
