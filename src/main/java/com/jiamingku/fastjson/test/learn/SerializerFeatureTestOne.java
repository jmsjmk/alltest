package com.jiamingku.fastjson.test.learn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jiamingku.fastjson.test.learn.bean.*;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;

import java.util.*;

/**
 * 首先fastJson=的全局配置:https://blog.csdn.net/lihaiyong92/article/details/90262574
 * http://kimmking.github.io/2017/06/06/json-best-practice/
 * https://www.cnblogs.com/zf29506564/p/6669870.html    ---输出
 * https://github.com/alibaba/fastjson/wiki/TypeReference  范型输出
 * <p>
 * <p>
 * ---------------------------------------------------
 * ---------------------------------------------------
 * ---------------------------------------------------
 * 其实还有一种根据属性来设置的方式
 * // 值传递---返回的值-赋一个新变量.
 * int a = SerializerFeature.config(JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.QuoteFieldNames, false);
 * System.out.println(JSON.toJSONString(jsonObject1, a));
 * <p>
 * --------------------------------------------------
 * SerializerFeature.WriteClassNam 指定了--在反序列化的时候
 * -- 类的权限定名字必须一样
 */
@SuppressWarnings("all")
public class SerializerFeatureTestOne {

    public static void main(String[] args) {
        /**
         * 所谓的全局: json在序列化的时候会按照指定的格式去序列化(设置一次就在jvm中全局通用)
         */
        // 是否输出值为null的字段,默认为false
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.WriteMapNullValue.getMask();
        // 数值字段如果为null,输出为0,而非null
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.WriteNullNumberAsZero.getMask();
        // List字段如果为null,输出为[],而非null
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.WriteNullListAsEmpty.getMask();
        // 字符类型字段如果为null,输出为 "",而非null
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.WriteNullStringAsEmpty.getMask();

        word = new Word();
        word.setA("a");
        word.setB(2);
        word.setIntegerValue(null);
        word.setC(true);
        word.setD("d");
        word.setE("");
        word.setF(null);
        word.setDate(new Date());
        // String s = JSON.toJSONString(word, SerializerFeature.WriteNullListAsEmpty);
        String s = JSON.toJSONString(word, true);
        System.out.println("s = " + s);
    }


    // --------------------------------------------------------------------------日期------------------------------------------------------------------------------------------

    /**
     * 日期的设置
     */
    @Test
    public void writeDateUseDateFormat1() {
        DateTestDemo dateTestDemo = new DateTestDemo();
        dateTestDemo.setName("dateTestDemo");
        dateTestDemo.setDate(new Date());
        dateTestDemo.setDate1(new Date());
        dateTestDemo.setDate2(new Date());
        // 1.
        String s = JSON.toJSONString(dateTestDemo);
        System.out.println(s);

        // 1.1 --设置默认的格式--默认是:"2020-06-19 09:48:00" --全局
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        // --不指定--输出还是默认的
        System.out.println(JSON.toJSONString(dateTestDemo));
        // --不指定--输出还是默认的
        System.out.println(JSON.toJSONString(dateTestDemo, SerializerFeature.WriteDateUseDateFormat));
    }

    /**
     * WriteDateUseDateFormat:全局修改日期格式,默认为false。
     */
    @Test
    public void writeDateUseDateFormat2() {
        DateTestDemo dateTestDemo = new DateTestDemo();
        dateTestDemo.setName("dateTestDemo");
        dateTestDemo.setDate(new Date());
        dateTestDemo.setDate1(new Date());
        dateTestDemo.setDate2(new Date());
        String s = JSON.toJSONStringWithDateFormat(dateTestDemo, "yyyy----MM-----dd HH:mm:ss");
        System.out.println("s = " + s);
    }


    // --------------------------------------------------------------------循环引用的问题-------------------------------------------------------------------------------------

    /**
     * 关于循环引用的问题....解决FastJson中“$ref 循环引用”的问题
     * SerializerFeature.DisableCircularReferenceDetec
     */
    @Test
    public void testCircularSerializable() {
        Group group = new Group();
        User u1 = new User();
        u1.setAge(1);
        u1.setName("name1");
        u1.setBirth(new Date());

        User u2 = new User();
        u2.setAge(2);
        u2.setName("name2");
        u2.setBirth(new Date());

        List<User> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);
        // {"create":1539419079251,"name":"gname","users":[{"age":1,"birth":1539419079251,"name":"name1"},
        // {"age":2,"birth":1539419079251,"name":"name2"},{"$ref":"$.users[0]"}]}
        // 这句话可能导致上面的问题
        users.add(u1);
        users.add(u1);
        users.add(u1);
        group.setUsers(users);
        /**
         * 阿里巴巴fastjson为了提高传输效率，会将传输给压缩
         */
        System.out.println(JSON.toJSONString(group));
        System.out.println();
        System.out.println(JSON.toJSONString(group, SerializerFeature.DisableCircularReferenceDetect));
    }

    @Test
    public void testCircularDeSerializable() {
        /**
         * 开启循环引用了, 是可以反序列化的
         */
        String s = "{\"users\":[{\"age\":1,\"birth\":1591503588776,\"name\":\"name1\"},{\"age\":2,\"birth\":1591503588776,\"name\":\"name2\"},{\"$ref\":\"$.users[0]\"}," +
                "{\"$ref\":\"$.users[0]\"},{\"$ref\":\"$.users[0]\"}]}";

        Group g = JSON.parseObject(s, Group.class);

        int a = g.getUsers().size();
        System.out.println("a = " + a);

        User user = g.getUsers().get(4);
        System.out.println("user = " + user);

        /**
         * 不开启循环引用--也是可以的。
         */
        s = "{\"users\":[{\"age\":1,\"birth\":1591504100090,\"name\":\"name1\"},{\"age\":2,\"birth\":1591504100090,\"name\":\"name2\"},{\"age\":1,\"birth\":1591504100090," +
                "\"name\":\"name1\"},{\"age\":1,\"birth\":1591504100090,\"name\":\"name1\"},{\"age\":1,\"birth\":1591504100090,\"name\":\"name1\"}]}";

        g = JSON.parseObject(s, Group.class);
        a = g.getUsers().size();
        System.out.println("a = " + a);
        user = g.getUsers().get(4);
        System.out.println("user = " + user);

    }

    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * SerializerFeature.WriteClassNam ----序列化
     * https://www.cnblogs.com/lpob/p/11853592.html ===关于特性的
     */
    @Test
    public void testSerializerFeatureWriteClassName() {
        Map map = new HashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);
        map.put("key3", "adf");

        Map map1 = new HashMap<>();
        map1 = new HashMap<>();
        map1.put("key1", 1);
        map1.put("key2", 2);
        map1.put("key3", 3);
        map1.put("key3", "adf");
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map);
        list.add(map1);
        String mapString = JSON.toJSONString(list);
        System.out.println("mapString = " + mapString);
        mapString = JSON.toJSONString(list, SerializerFeature.WriteClassName);
        System.out.println("mapString = " + mapString);
    }

    /**
     * SerializerFeature.WriteClassNam 指定了--在反序列化的时候
     * -- 类的权限定名字必须一样
     */
    @Test
    public void testDeSerializerFeatureWriteClassName() {
        User u = new User();
        u.setAge(1);
        u.setName("name");
        u.setBirth(new Date());

        String mapString = JSON.toJSONString(u, SerializerFeature.WriteClassName);
        System.out.println("mapString = " + mapString);
        // ---强制要求反序列话时候一定要符合标准
        String ss = "{\"@type\":\"com.jiamingku.fastjson.test.learn.bean.User\",\"age\":1,\"birth\":1592534805201,\"name\":\"name\"}";

        UserCCCC userccc = JSON.parseObject(ss, UserCCCC.class);
        System.out.println("userccc = " + userccc);
    }

    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 去掉引号-key的引号, 反序列化没问题，单引号，双引号，等一些问题都是一样的没啥区别
     * <p>
     * 1.可以不用修改全局的变量
     */
    @Test
    public void removeFiledNameQuoteOne() {
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("key1", "val1");
        jsonObject1.put("key2", 1);

        // 全局设置---
        // JSON.DEFAULT_GENERATE_FEATURE = SerializerFeature.config(JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.QuoteFieldNames, false);

        // 值传递---返回的值-赋一个新变量.
        int a = SerializerFeature.config(JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.QuoteFieldNames, false);
        System.out.println(JSON.toJSONString(jsonObject1, a));

        System.out.println("a-------------------------------------------");

        // ----------------------------
        User u = new User();
        u.setName("name");
        u.setAge(100);
        String us = JSON.toJSONString(jsonObject1);
        User u1 = JSON.parseObject(us, User.class);
        System.out.println("u1 = " + u1);
        System.out.println("us = " + us);
    }


    /**
     * 单引号-反序列化也是没有问题的--兼容性很好
     */
    @Test
    public void removeFiledNameQuoteTwo() {
        // ----------------------------
        User u = new User();
        u.setName("name");
        u.setAge(100);
        System.out.println(JSON.toJSONString(u, SerializerFeature.UseSingleQuotes));
        String s1 = "{'age':100,'name':'name'}";
        User u11 = JSONObject.parseObject(s1, User.class);
        System.out.println("u11 = " + u11);
    }

    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 将对象转为array输出,减少数据传输..端上配合稍微差了点
     */
    @Test
    public void beanToArray() {
        init();
        System.out.println(JSON.toJSONString(word));
        String ssss = JSON.toJSONString(word, SerializerFeature.BeanToArray);
        System.out.println("ssss = " + ssss);
        // ----但是在反序列化之后有问题--忽略这哥特性没啥意识
        // Word word = JSON.parseObject(ssss, Word.class);
        JSON.parseObject(ssss, Word.class, Feature.SupportArrayToBean);
        System.out.println("word = " + word);
    }


    /**
     * SortField:
     */
    @Test
    public void sortField() {
        init();
        System.out.println(JSON.toJSONString(word));
        System.out.println(JSON.toJSONString(word, SerializerFeature.SortField));
    }


    /**
     * 3:
     * UseISO8601DateFormat:Date使用ISO8601格式输出，默认为false
     */
    @Test
    public void useISO8601DateFormat() {
        init();
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置UseISO8601DateFormat后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.UseISO8601DateFormat));
    }


    // ---------------异常类自动开起来了@type支持
    @Test
    public void test() {
        try {
            throw new NullPointerException("ce");
        } catch (Exception e) {
            String s = JSON.toJSONString(e);
            System.out.println("s = " + s);
        }
    }

    @Test
    public void nullProperties() {
        // 是否输出值为null的字段,默认为false
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.WriteMapNullValue.getMask();
        // 数值字段如果为null,输出为0,而非null
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.WriteNullNumberAsZero.getMask();
        // List字段如果为null,输出为[],而非null
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.WriteNullListAsEmpty.getMask();
        // 字符类型字段如果为null,输出为 "",而非null
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.WriteNullStringAsEmpty.getMask();

        Map<String, Object> map = new LinkedHashMap<>();
        /** 空属性不会序列化*/
        map.put("k1", null);
        map.put("k2", "v2");
        List list = null;
        map.put("Listisnull", list);
        List listIsEmpty = new ArrayList<>();
        map.put("listIsEmpty", listIsEmpty);

        String a = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
        System.out.println("a = " + a);

        a = JSON.toJSONString(map, SerializerFeature.WriteNullListAsEmpty);
        System.out.println("a = " + a);
        System.out.println(" ===========================对比两种序列化方式==================================== ");

        String listtoST = JSON.toJSONString(list, SerializerFeature.WriteNullListAsEmpty);
        System.out.println("listtoST = " + listtoST);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static Word word;

    // Dfastjson.parser.autoTypeSupport=true

    private static void init() {
        word = new Word();
        word.setA("a");
        word.setB(2);
        word.setC(true);
        word.setD("d");
        word.setE("");
        word.setF(null);
        word.setDate(new Date());

        List<PropertyFilterTest> list = new ArrayList<PropertyFilterTest>();
        PropertyFilterTest propertyFilterTest0 = new PropertyFilterTest();
        propertyFilterTest0.setId(1);
        propertyFilterTest0.setOld("11");
        propertyFilterTest0.setName("用户1");
        propertyFilterTest0.setAdd("北京");
        PropertyFilterTest propertyFilterTest1 = new PropertyFilterTest();
        propertyFilterTest1.setId(2);
        propertyFilterTest1.setOld("22");
        propertyFilterTest1.setName("用户2");
        propertyFilterTest1.setAdd("上海");
        PropertyFilterTest propertyFilterTest3 = new PropertyFilterTest();
        propertyFilterTest3.setId(3);
        propertyFilterTest3.setOld("33");
        propertyFilterTest3.setName("用户3");
        propertyFilterTest3.setAdd("广州");

        list.add(propertyFilterTest3);
        list.add(propertyFilterTest1);
        list.add(null);
        list.add(propertyFilterTest0);

        word.setList(list);

        Map<String, Object> map = new HashedMap();
        map.put("mapa", "mapa");
        map.put("mapo", "mapo");
        map.put("mapz", "mapz");
        map.put("user1", propertyFilterTest1);
        map.put("user3", propertyFilterTest3);
        map.put("user4", null);
        map.put("list", list);
        word.setMap(map);
    }


}