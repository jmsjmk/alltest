package com.jiamingku.fastjson.test.learn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jiamingku.fastjson.test.learn.bean.UserBean;
import org.junit.Test;

import java.util.Map;

/**
 * 1.fastJson在进行转换的时候，如果有范型会丢失类型信息
 * <p>
 * 2.List<User>  返回的实际类型就是List<JSONObject>
 * <p>
 * 3. Map<String, UserBean> map = JSON.parseObject(s, new TypeReference<Map<String, UserBean>>() {
 * });
 */
@SuppressWarnings("all")
public class CollectionTestGeneric {
    /**
     * 3.但序列化回来的对象类型是关键.
     */
    @Test
    public void jsonStringToObjectMap() {
        String s = "{\"u2\":{\"age\":2,\"birth\":\"2017-04-10 15:14:53\",\"name\":\"nam2\"},\"u1\":{\"age\":1," +
                "\"birth\":\"2017-04-10 15:14:53\",\"name\":\"nam1\"},\"u3\":{\"age\":1,\"birth\":\"2017-04-10 " +
                "15:14:53\",\"name\":\"nam3\"}}";
        System.out.println("s = " + s);
        //  JSONObject.parseObject(s,Map.class);
        Map<String, UserBean> map = JSON.parseObject(s, Map.class);


        System.out.println(map.size() + "\n" + map);
        System.out.println("map.getClass().getSimpleName() = " + map.getClass().getSimpleName());

        for (Map.Entry<String, UserBean> entry : map.entrySet()) {
            String key = entry.getKey();
            Object u = entry.getValue();// // UserBean u = entry.getValue();*******这行是重点
            // 其实这个地方就报异常了
            System.out.println("u.getClass().getSimpleName() ----------------= " + u.getClass().getSimpleName());
            System.out.println("key:" + key);
            System.out.println("u:" + u.toString());
            System.out.println("key.getClass().getSimpleName() = " + key.getClass().getSimpleName());
            System.out.println("u.getClass().getSimpleName() = =================" + u.getClass().getSimpleName());
        }
    }

    /**
     * 1.https://github.com/alibaba/fastjson/wiki/TypeReference
     * <p>
     * 2.在反序列化的时候,如果想holder住原始的类型的话需要-需要进行使用下面的方法
     */
    @Test
    public void jsonStringToObjectMap11() {
        String s = "{\"u2\":{\"age\":2,\"birth\":\"2017-04-10 15:14:53\",\"name\":\"nam2\"},\"u1\":{\"age\":1," +
                "\"birth\":\"2017-04-10 15:14:53\",\"name\":\"nam1\"},\"u3\":{\"age\":1,\"birth\":\"2017-04-10 " +
                "15:14:53\",\"name\":\"nam3\"}}";
        //  JSONObject.parseObject(s,Map.class);
        Map<String, UserBean> map = JSON.parseObject(s, new TypeReference<Map<String, UserBean>>() {
        });

        System.out.println(map.size() + "\n" + map);
        System.out.println("map.getClass().getSimpleName() = " + map.getClass().getSimpleName());

        for (Map.Entry<String, UserBean> entry : map.entrySet()) {
            String key = entry.getKey();
            UserBean u = entry.getValue();
            // 其实这个地方就报异常了
            // UserBean u = entry.getValue();
            System.out.println("key:" + key);
            System.out.println("u:" + u.toString());
            System.out.println("key.getClass().getSimpleName() = " + key.getClass().getSimpleName());
            System.out.println("u.getClass().getSimpleName() = =================" + u.getClass().getSimpleName());
            // ------------如何解决
        }
    }
}
