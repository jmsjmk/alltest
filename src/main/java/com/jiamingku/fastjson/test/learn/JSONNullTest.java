package com.jiamingku.fastjson.test.learn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jiamingku.fastjson.test.learn.bean.AllTypeBean;
import com.jiamingku.fastjson.test.learn.bean.User;
import com.jiamingku.fastjson.test.learn.bean.test.Par;
import com.jiamingku.fastjson.test.learn.bean.test.Par1;
import org.junit.Test;

import java.util.Map;

/**
 * 边缘问题: "", null ,"null" 这些在进行转换对象跟属性是截然不同的
 * <p>
 * 1.有结构的就是尺寸=0 {},[]
 * <p>
 * --------------------------------直接返回序列化
 * 2."null"    返序列化全部是-null
 * 3.""        返序列化全部是-null
 * 3.null引用   返序列化全部是-null
 * <p>
 * --------------------------------但是作为属性来说就不太一样.
 * 1.对于作为属性来首,根据接收的具体类型,可能产生的结果不太一样.
 * {@link JSONNullTest#testNullObject()}
 */
@SuppressWarnings("all")
public class JSONNullTest {

    /**
     * null 返回对象就是null, "null"返回的对象也是null
     */
    @Test
    public void jsonObject11() {
        String a = "null";
        Object map = JSON.parseObject(a);
        System.out.println("map = " + map);
        User u = JSON.parseObject(a, User.class);
        System.out.println("u = " + u);
        // --一定要仔细否则会误导自己 *******************************88
        String ss = JSON.parseObject(a, String.class);
        System.out.println("ss = " + ss);
        if (ss == null) {
            System.out.println(" null 字符串=长度=4转换成为 string 是null对象");
        } else {
            System.out.println(" 不是空，");
        }
    }


    /**
     * null 在转换成为json字符串的时候4个长度
     * ""   在转换成为json字符串的时候2个长度-就是2个双引号
     */
    @Test
    public void t11111() {

        String s = JSON.toJSONString(null);
        if (s == null) {
            System.out.println("is null");
        } else {
            System.out.println("is not null ");
        }
        System.out.println("null 转换json串: 长度=4的-- " + s);
        s = JSON.toJSONString("");
        System.out.println("s.length() = " + s.length() + "\t" + s);
        System.out.println("s = " + s);
        String s1 = "";
        System.out.println("s1.length() = " + s1.length());
        s = JSON.toJSONString(s1);
        System.out.println("s.length() = " + s.length());
        System.out.println("s = " + s);
    }

    /**
     * -----简单类型的null是不报错的，反序列化回来是系统的初始化值.
     */
    @Test
    public void testSimpleType() {
        AllTypeBean allTypeBean = new AllTypeBean();
        String s = JSON.toJSONString(allTypeBean);
        System.out.println(s);
        String s1 = "{\"byteValue\":null,\"douValue\":0.0,\"floValue\":0.0,\"intValue\":0}";
        allTypeBean = JSON.parseObject(s1, AllTypeBean.class);
        System.out.println(allTypeBean);
    }

    // ======================================================================================================
    // ======================================================================================================
    // ======================================================================================================
    // ======================================================================================================
    // JSONObject = {} or null

    /**
     * 1.结构有--但是尺寸没有--转换回来对象不为null,只是调用了个默认的构造器构建出来的对象
     * 2.集合也是一样的
     */
    @Test
    public void jsonObjectNullTest() {
        String a = "{}";
        Map map = JSONObject.parseObject(a, Map.class);
        System.out.println("map = " + map);
        System.out.println("map.size() = " + map.size());
        User u = JSON.parseObject(a, User.class);
        System.out.println("u = " + u);
        JSONObject j = JSONObject.parseObject("");
        if (j == null) {
            System.out.println("j = " + j);
        }
    }

    @Test
    public void josnArrayNullTest2() {
        String a = "";
        JSONArray jsonArray = JSON.parseArray(a);
        System.out.println("map = " + jsonArray);

        a = null;
        jsonArray = JSON.parseArray(a);
        System.out.println("map = " + jsonArray);

        Object o1 = JSONArray.parseArray("null");
        System.out.println("o1 = " + o1);

        a = "[]";
        jsonArray = JSON.parseArray(a);
        System.out.println("map = " + jsonArray);
    }

    // ======================================================================================================
    // ======================================================================================================
    // ======================================================================================================
    // ======================================================================================================
    @Test
    public void jsonObjectNullTest1212() {
        String a = "";
        Map map = JSON.parseObject(a, Map.class);
        System.out.println("map = " + map);
        User u = JSON.parseObject(a, User.class);
        System.out.println("u = " + u);
        Object o = JSON.parseObject(a);
        System.out.println("o = " + o);
        Object o1 = JSON.parse(a);
        System.out.println("o1 = " + o1);
        String ss = JSON.parseObject(a, String.class);
        System.out.println("ss = " + ss);
    }

    @Test
    public void jsonObjectNullTest13212() {
        String a = "null";
        Map map = JSON.parseObject(a, Map.class);
        System.out.println("map = " + map);
        User u = JSON.parseObject(a, User.class);
        System.out.println("u = " + u);
        Object o = JSON.parseObject(a);
        System.out.println("o = " + o);
        Object o1 = JSON.parse(a);
        System.out.println("o1 = " + o1);
        String ss = JSON.parseObject(a, String.class);
        System.out.println("ss = " + ss);
    }

    /**
     * "null" 在转换对象的时候是没啥问题的, 但是作为属性来说问题就大
     * <p>
     * "null" 如果直接转换成为 String 对象就是 null
     * "null" 作为一个json串中某个属性的值, 你在进行反序列化的时候 如果是String 类型的话 = 长度4的串
     * "null" 作为一个json传中的某个属性值, object类型 进行接收的话，也是4个长度的
     * <p>
     * ====
     * 对于"" 作为属性的时候呢？
     */
    @Test
    public void testTT() {
        User u = new User();
        String s = JSON.toJSONString(u, SerializerFeature.WriteMapNullValue);
        System.out.println("s = " + s);


        String s1 = "{\"age\":0,\"birth\":null,\"name\":\"null\"}";
        User u1 = JSON.parseObject(s1, User.class);
        System.out.println(u1);
        System.out.println("u1.getName().length() = " + u1.getName().length());

        String s2 = "{\"age\":0,\"birth\":null,\"name\":null,\"objctNUll\":\"null\"}";
        u1 = JSON.parseObject(s2, User.class);
        System.out.println(u1);
        System.out.println("u1.getName().length() = " + ((String) u1.getObjctNUll()).length());

        String s123 = "null";
        User uu22 = JSON.parseObject(s123, User.class);

        // "null" 直接作为属性转换成为对象时候报错-，但是直接转换成为对象时候不报错
        s2 = "{\"age\":0,\"birth\":null,\"hashMap\":null,\"name\":null,\"objctNUll\":null,\"u1\":\"\"}";

        User uu = JSON.parseObject(s2, User.class);

        System.out.println("uu.getU1() = " + uu.getU1());
        System.out.println("uu.getObjctNUll() = " + uu.getObjctNUll());
        // ---出现异常
        // System.out.println("uu.getObjctNUll() = " + uu.getName().length());

        s2 = "{\"age\":0,\"birth\":null,\"hashMap\":null,\"name\":\"\",\"objctNUll\":\"\",\"u1\":\"\"}";

        uu = JSON.parseObject(s2, User.class);

        System.out.println("uu.getName() = " + uu.getName().length());
        System.out.println(uu.getObjctNUll().getClass().getSimpleName());
        System.out.println("uu.getU1() = " + uu.getU1());


    }


    @Test
    public void testNullObject() {
        Par p = new Par();
        p.setA("v1");
        p.setB("v2");
        String s = JSONObject.toJSONString(p);
        System.out.println("s = " + s);
        String ss = "{\"a\":\"v1\",\"b\":\"v2\",\"o\":\"\"}";
        /**
         * 正常 "" 在parseObject 是 null  =但是对于object类型的属性来说，来说他可以按照字符串来处理
         */
        Par p1 = JSONObject.parseObject(ss, Par.class);
        if (p1.getO() == null) {
            System.out.println("is null");
        } else {
            int l = p1.getO().toString().length();
            System.out.println("l = " + l);
        }
        System.out.println("p1 = " + p1);


        String s1s = "{\"a\":\"v1\",\"b\":\"v2\",\"o\":\"\"}";
        /**
         * 对于Integer 与 Object 类型来说 都是一样的。
         */
        Par1 p12 = JSONObject.parseObject(s1s, Par1.class);
        if (p12.getO() == null) {
            System.out.println("is null");
        } else {
            int l = p1.getO().toString().length();
            System.out.println("l = " + l);
        }
        System.out.println("p1 = " + p1);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ======================================================================================================
    @Test
    public void jsonStrToJsonObject() {
        String s = "{\"k1\":[1,\"2\",3],\"k2\":[4,5,6],\"k3\":\"1\"}";
        JSONObject jsonObject = JSONObject.parseObject(s);
        System.out.println("jsonObject = " + jsonObject);
        for (Map.Entry<String, Object> en : jsonObject.entrySet()) {
            System.out.println("en.getKey() = " + en.getKey());
            System.out.println("en.getValue().getClass() = " + en.getValue().getClass());
        }
    }
}
