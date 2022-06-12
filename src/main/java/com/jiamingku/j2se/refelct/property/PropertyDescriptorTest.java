package com.jiamingku.j2se.refelct.property;

import com.jiamingku.j2se.refelct.bo.UserInfo;
import com.jiamingku.j2se.refelct.bo.UserInfo2;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * PropertyDescriptor:jdk自带的类
 * 简单说:你传递一个class, 在给一个属性名 就可以构建这个对象:
 * eg
 * <p>
 * ::: https://blog.csdn.net/weixin_42069143/article/details/82119724::::
 * <p>
 * :::https://blog.csdn.net/shenchaohao12321/article/details/80295371:::
 * 1> https://www.jianshu.com/p/7649f86614d3
 * 2> https://www.jianshu.com/p/cae76008b36b
 */

/**
 * PropertyDescriptor:的另外一种写法:或者是他的工具也可以-方面用户使用
 * <p>
 * // 获取bean信息
 * BeanInfo beanInfo = Introspector.getBeanInfo(UserInfo.class);
 * // 获取bean的所有属性列表
 * PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
 * <p>
 * ---------------------------------------------------------------------------
 * 其实就一套api的使用
 * <p/>
 * PropertyDescriptor propDesc = new PropertyDescriptor(userName, UserInfo.class);
 * Method methodSetUserName = propDesc.getWriteMethod();
 * <p>
 * <p/>
 * BeanInfo beanInfo = Introspector.getBeanInfo(UserInfo2.class);
 * PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
 */
public class PropertyDescriptorTest {

    public static void main(String[] args) throws Exception {
        UserInfo userInfo = new UserInfo();
        PropertyDescriptorTest.setProperty(userInfo, "userName");
        System.out.println("userInfo = " + userInfo.getUserName());


        System.out.println(" --------------------------------------------- ");
        try {
            UserInfo2 userInfo2 = new UserInfo2();
            PropertyDescriptorTest.setProperty2(userInfo2, "userName");
        } catch (Exception e) {
            System.out.println("缺少get方法发生异常!");
        }
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

    /// -------------------------------------------------------------------------------
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

    // 通过内省设置bean的某个属性值
    public static void setPropertyByIntrospector(UserInfo userInfo, String userName) throws Exception {
        // 获取bean信息
        BeanInfo beanInfo = Introspector.getBeanInfo(UserInfo2.class);
        // 获取bean的所有属性列表
        PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
        // 遍历属性列表，查找指定的属性
        if (proDescrtptors != null && proDescrtptors.length > 0) {
            for (PropertyDescriptor propDesc : proDescrtptors) {
                // 找到则写入属性值
                if (propDesc.getName().equals(userName)) {
                    Method methodSetUserName = propDesc.getWriteMethod();
                    methodSetUserName.invoke(userInfo, "alan");  // 写入属性值
                    System.out.println("set userName:" + userInfo.getUserName());
                    break;
                }
            }
        }
    }

    // 通过内省获取bean的某个属性值
    public static void getPropertyByIntrospector(UserInfo userInfo, String userName) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(UserInfo2.class);
        PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
        if (proDescrtptors != null && proDescrtptors.length > 0) {
            for (PropertyDescriptor propDesc : proDescrtptors) {
                if (propDesc.getName().equals(userName)) {
                    Method methodGetUserName = propDesc.getReadMethod();
                    Object objUserName = methodGetUserName.invoke(userInfo);
                    System.out.println("get userName:" + objUserName.toString());
                    break;
                }
            }
        }
    }


    public static void getPropertyByIntrospector1(UserInfo userInfo, String userName) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(UserInfo2.class);
        PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();

        if (proDescrtptors != null && proDescrtptors.length > 0) {
            for (PropertyDescriptor propDesc : proDescrtptors) {
                if (propDesc.getName().equals(userName)) {
                    Method methodGetUserName = propDesc.getReadMethod();
                    Object objUserName = methodGetUserName.invoke(userInfo);
                    System.out.println("get userName:" + objUserName.toString());
                    break;
                }
            }
        }
    }
}
