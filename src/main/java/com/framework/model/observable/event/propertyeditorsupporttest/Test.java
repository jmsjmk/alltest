package com.framework.model.observable.event.propertyeditorsupporttest;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) throws Exception {
        Map<String, String> parameters = new HashMap<String, String>() {
            {
                //这里的key要和Node里面的属性名一致
                put("nodeName", "昭君");
                put("user", "zhaoJun|changhe@163.com|2018-10-08 15:06:00");
            }
        };
        Node convert = PropertyEditorSample.convert(parameters);
        System.out.println(convert.getNodeName());
        System.out.println(convert.getUser());
    }
}

