package com.jiamingku.fastjson.test.learn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jiamingku.fastjson.test.learn.bean.User;
import org.junit.Test;

import java.util.*;

/**
 * 1.unix时间戳是距离1970年的秒数
 * <p>
 * 2.java的日期获取的时间--是毫秒--整整差了1000被 处理就可以获取到了
 * <p>
 * 3.用jsonObject接收参数,日期是数字-就变成Long, 日期是:yyyy-MM-dd HH:mm:ss 就变成了字符串
 */
public class FastJsonDateTest {

    /**
     * 同mysql的unix_timestamp函数
     */
    public static int unixTimestamp(long ts) {
        return (int) (ts / 1000);
    }

    /**
     * 同mysql的unix_timestamp函数
     */
    public static int unixTimestamp(Date date) {
        if (date == null) {
            return 0;
        }
        return unixTimestamp(date.getTime());
    }

    @Test
    public void unixTime() {
        Date date = new Date();
        long l = date.getTime();
        // -- db存的时间 1522650017
        // -- db存的时间 1594357729383
        long l2 = System.currentTimeMillis();
        System.out.println("l2 = " + l2);
        System.out.println("l2 = " + l);
    }


    /**
     * 日期对象---转换为字符串
     * <p>
     * {"age":1,"birth":1491819397866,"name":"nam1"}
     * 1.Date 类型会默认给你转换成为1491819397866
     */
    @Test
    public void toJsonStr() {
        User u1 = new User();
        u1.setAge(1);
        u1.setName("nam1");
        u1.setBirth(new Date());
        String json = JSON.toJSONString(u1);
        System.out.println(json);
        System.out.println(" = =================================== 设置对应的属性");
//        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";

        String aaaa = JSON.toJSONString(u1, SerializerFeature.WriteDateUseDateFormat);
        System.out.println("aaaa = " + aaaa);
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH";

        aaaa = JSON.toJSONString(u1, SerializerFeature.WriteDateUseDateFormat);
        System.out.println("aaaa = " + aaaa);

        aaaa = JSON.toJSONString(u1);
        System.out.println("aaaa333333333333333 = " + aaaa);

        aaaa = JSON.toJSONString(u1, SerializerFeature.WriteDateUseDateFormat);
        System.out.println("aaaa = " + aaaa);

        String s = "{\"age\":1,\"birth\":1592445095132,\"name\":\"nam1\"}";

        JSONObject o = JSON.parseObject(s);
        System.out.println("o.getClass().getSimpleName() = " + o.get("birth").getClass().getSimpleName());

        User user = JSON.parseObject(s, User.class);
        System.out.println("user = " + user);
    }

    /**
     * 感受下就行了-日期-"yyyy-MM-dd HH:mm:ss" 与 "数字串" 都可以给你转换成为对应的日期类型
     */
    @Test
    public void toJavaObject() {
        String s = "{\"age\":1,\"birth\":1491811647716,\"name\":\"nam1\"}";
        User u = JSON.parseObject(s, User.class);
        System.out.println(u.getBirth());
        System.out.println("==============");
        String s1 = "{\"age\":1,\"birth\":\"2017-12-12\",\"name\":\"nam1\"}";
        User u1 = JSON.parseObject(s1, User.class);
        System.out.println(u1.getBirth());
    }
}
