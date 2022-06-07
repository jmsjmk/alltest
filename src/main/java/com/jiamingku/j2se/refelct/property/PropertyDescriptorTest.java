package com.jiamingku.j2se.refelct.property;

import com.jiamingku.j2se.refelct.bo.UserInfo;
import com.jiamingku.j2se.refelct.bo.UserInfo2;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * PropertyDescriptor:jdk自带的类
 * 简单说:你传递一个class, 在给一个属性名 就可以构建这个对象:
 * eg
 *
 * ::: https://blog.csdn.net/weixin_42069143/article/details/82119724::::
 * :::https://blog.csdn.net/shenchaohao12321/article/details/80295371:::
 *  1> https://www.jianshu.com/p/7649f86614d3
 *  2> https://www.jianshu.com/p/cae76008b36b
 *
 */
public class PropertyDescriptorTest {

    public static void main(String[] args) throws Exception {
        UserInfo userInfo = new UserInfo();
        PropertyDescriptorTest.setProperty(userInfo, "userName");
        System.out.println(" ======= ");
        System.out.println("userInfo = " + userInfo.getUserName());

        System.out.println(" --------------------------------------------- ");

        userInfo.setUserName("111111111111111111111111111111");

        getProperty(userInfo, "userName");

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        // ---------------------------没有get set方法的呢?


        UserInfo2 userInfo2 = new UserInfo2();
        PropertyDescriptorTest.setProperty2(userInfo2, "userName");
        System.out.println(" ======= ");
        System.out.println("userInfo = " + userInfo.getUserName());



    }

    // 设置bean的某个属性值
    public static void setProperty(UserInfo userInfo, String userName) throws Exception {
        // 获取bean的某个属性的描述符
        PropertyDescriptor propDesc = new PropertyDescriptor(userName, UserInfo.class);
        // 获得用于写入属性值的方法
        Method methodSetUserName = propDesc.getWriteMethod();
        // 写入属性值
        methodSetUserName.invoke(userInfo, "wong");
        System.out.println("set userName:" + userInfo.getUserName());
    }

    // ---------------------------缺少get set方法 会发生异常.
    // 设置bean的某个属性值
    public static void setProperty2(UserInfo2 userInfo, String userName) throws Exception {
        // 获取bean的某个属性的描述符
        PropertyDescriptor propDesc = new PropertyDescriptor(userName, UserInfo2.class);
        // 获得用于写入属性值的方法
        Method methodSetUserName = propDesc.getWriteMethod();
        // 写入属性值
        methodSetUserName.invoke(userInfo, "wong");

    }

    // 设置bean的某个属性值
    public static void setProperty3(UserInfo2 userInfo, String userName) throws Exception {
        // 获取bean的某个属性的描述符
        PropertyDescriptor propDesc = new PropertyDescriptor(userName, UserInfo2.class);

//        propDesc.setPropertyEditorClass();
        // 获得用于写入属性值的方法
        Method methodSetUserName = propDesc.getWriteMethod();
        // 写入属性值
        methodSetUserName.invoke(userInfo, "wong");

    }


    // 获取bean的某个属性值
    public static void getProperty(UserInfo userInfo, String userName) throws Exception {
        // 获取Bean的某个属性的描述符
        PropertyDescriptor proDescriptor = new PropertyDescriptor(userName, UserInfo.class);
        // 获得用于读取属性值的方法
        Method methodGetUserName = proDescriptor.getReadMethod();
        // 读取属性值
        Object objUserName = methodGetUserName.invoke(userInfo);
        System.out.println("get userName:" + objUserName.toString());
    }
}
