package com.jiamingku.j2se.refelct.property.demo;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 转换类
 */
public class PropertyEditorSample {

    /**
     * 进行转换
     */
    public static Node convert(Map<String, String> parameters) throws Exception {
        //注册bean的编辑器,放到一个WeakHashMap中
        // --------------注册这个是非常重要的方法- 这个东西是下面:
        PropertyEditorManager.registerEditor(User.class, UserEditor.class);

        Node node = new Node();
        BeanInfo bi = Introspector.getBeanInfo(Node.class);

        //获取所有的属性
        PropertyDescriptor[] pds = bi.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            Class<?> propertyType = pd.getPropertyType();
            String name = pd.getName();
            System.out.println("pd.getPropertyType() = " + propertyType + "       pd.getName():: " + name);
            Method writeMethod = pd.getWriteMethod();
            if (propertyType == Class.class) {
                //ignore
            } else if (propertyType == String.class) {
                writeMethod.invoke(node, parameters.get(pd.getName()));
            } else {
                //根据类型查找bean的编辑器,获取到了UserEditor的实例
                // ------------------找到编辑器-------------------------------------
                PropertyEditor editor = PropertyEditorManager.findEditor(propertyType);
                if (editor != null) {
                    // --------------------调用编辑器------并将值设置到属性编辑器中。
                    editor.setAsText(parameters.get(pd.getName()));

                    // 反射 注入需要的值------------
                    writeMethod.invoke(node, editor.getValue());
                } else {
                    System.out.println("no editor for:" + pd.getName());
                }
            }
        }
        return node;
    }

}