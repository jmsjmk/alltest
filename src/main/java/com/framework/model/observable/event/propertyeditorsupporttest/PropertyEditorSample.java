package com.framework.model.observable.event.propertyeditorsupporttest;

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
        PropertyEditorManager.registerEditor(User.class, UserEditor.class);
        Node node = new Node();
        BeanInfo bi = Introspector.getBeanInfo(Node.class);
        //获取所有的属性
        PropertyDescriptor[] pds = bi.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            Class<?> propertyType = pd.getPropertyType();
            Method writeMethod = pd.getWriteMethod();
            if (propertyType == Class.class) {
                //ignore
            } else if (propertyType == String.class) {
                writeMethod.invoke(node, parameters.get(pd.getName()));
            } else {
                //根据类型查找bean的编辑器,获取到了UserEditor的实例
                PropertyEditor editor = PropertyEditorManager.findEditor(propertyType);
                if (editor != null) {
                    editor.setAsText(parameters.get(pd.getName()));
                    writeMethod.invoke(node, editor.getValue());
                } else {
                    System.out.println("no editor for:" + pd.getName());
                }
            }
        }
        return node;
    }

}
