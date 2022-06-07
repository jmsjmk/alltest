package com.jiamingku.j2se.refelct.property.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zk
 * @Description: 测试用例
 * @date 2018-10-08 15:22
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