package com.jiamingku.j2se.refelct.property.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zk
 * @Description: 测试用例
 * @date 2018-10-08 15:22
 * <p>
 * ----------------------------------------------------------------
 * 整体的概括如下:
 * 1.java定义一个接口PropertyEditor 属性编辑器,实现他
 * -------
 * 2.Java为PropertyEditor提供了一个方便的实现类：PropertyEditorSupport，该类实现了PropertyEditor接口并提供默认实现，
 * 一般情况下，用户可以通过扩展这个方便类设计自己的属性编辑器。
 * ------
 * 3.BeanInfo接口, 同时java也提供了一个方便:BeanInfo接口有一个常用的实现类：SimpleBeanInfo，
 * 一般情况下，可以通过扩展SimpleBeanInfo实现自己的功能。
 * -------
 * 4.如果某个JavaBean的常见类型属性没有通过BeanInfo显式指定属性编辑器，IDE将自动使用PropertyEditorManager中注册的对应默认属性编辑器。
 *    //---PropertyDescriptor.setPropertyEditorClass();上面应该说的是这个.
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Map<String, String> parameters = new HashMap<String, String>() {
            {
                //这里的key要和Node里面的属性名一致
                put("nodeName", "曹操");
                put("user", "zhaoJun|changhe@163.com|2018-10-08 15:06:00");
            }
        };

        Node convert = PropertyEditorSample.convert(parameters);
        System.out.println(convert.getNodeName());
        System.out.println(convert.getUser().hashCode());
    }
}