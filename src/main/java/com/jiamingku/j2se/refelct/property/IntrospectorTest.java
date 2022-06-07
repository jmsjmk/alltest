package com.jiamingku.j2se.refelct.property;

import com.jiamingku.j2se.refelct.bo.UserInfo;
import com.jiamingku.j2se.refelct.bo.UserInfo2;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * PropertyDescriptor:的另外一种写法:或者是他的工具也可以-方面用户使用
 * <p>
 * // 获取bean信息
 * BeanInfo beanInfo = Introspector.getBeanInfo(UserInfo.class);
 * // 获取bean的所有属性列表
 * PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
 * <p>
 * <p>
 * <p>
 * ::: https://blog.csdn.net/weixin_42069143/article/details/82119724::::
 * :::https://blog.csdn.net/shenchaohao12321/article/details/80295371:::
 * 1> https://www.jianshu.com/p/7649f86614d3
 * 2> https://www.jianshu.com/p/cae76008b36b
 */
public class IntrospectorTest {

    public static void main(String[] args) throws Exception {
        UserInfo userInfo = new UserInfo();

        setPropertyByIntrospector(userInfo, "userName");

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
}
