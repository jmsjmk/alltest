package com.jiamingku.fastjson.test.serializeable.base;

import com.google.common.base.Objects;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * 另外的一种序列化机制::https://my.oschina.net/wangmengjun/blog/1588096
 * <p>
 * 部分序列化:
 * 1)私有方法writeObject和readObject，完成部分属性的序列化(必须私有+无返回值)
 * <p>
 * 2)Externalizable: 实现属性的部分序列化
 */
public class ExternalizableTest implements Externalizable {

    private transient String content = "是的，我将会被序列化，不管我是否被transient关键字修饰--默认的是肯定不会被序列化---" +
            "只要重写了方法自己序列化了就可以";

    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println(out.getClass().getSimpleName());
        ObjectOutputStream o = (ObjectOutputStream) out;
//         o.defaultWriteObject();// TestSerializationTransient
        System.out.println(" 序列化方法被调用");
        o.writeObject(content);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println(" 返序列化方法被调用");
        ObjectInputStream in1 = (ObjectInputStream) in;
//         in1.defaultReadObject();

        content = (String) in1.readObject();
    }

    public static void main(String[] args) throws Exception {
        ExternalizableTest et = new ExternalizableTest();
        et.setAge("+10");
        System.out.println("et:" + et);
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream(new File("test")));
        out.writeObject(et);

        ObjectInput in = new ObjectInputStream(new FileInputStream(new File("test")));
        et = (ExternalizableTest) in.readObject();
        System.out.println("-----" + et.content);
        System.out.println("-----age:" + et.getAge());
        out.close();
        in.close();
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("content", content)
                .add("age", age)
                .toString();
    }
}