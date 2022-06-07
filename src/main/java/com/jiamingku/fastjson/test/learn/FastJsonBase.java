package com.jiamingku.fastjson.test.learn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jiamingku.fastjson.test.learn.bean.AllTypeBean;
import com.jiamingku.fastjson.test.learn.bean.AllTypeBeanClone;
import com.jiamingku.fastjson.test.learn.bean.UserNoGetAndPrivateMethod;
import com.jiamingku.j2se.enumtest.Enum2;
import org.junit.Test;

import java.util.*;

@SuppressWarnings("all")
public class FastJsonBase {

    /**
     * 要对格式敏感------对于数值类型是不会进行+双引号的
     */
    @Test
    public void testListInteger() {
        List<Integer> list = new ArrayList<>();
        list.add(100);
        list.add(101);
        list.add(103);
        // ----有的类+特性也不会输出
        String listString = JSON.toJSONString(list, SerializerFeature.WriteClassName);
        System.out.println(listString);
        System.out.println(" === ");
        System.out.println(" === ");
        Map map = new LinkedHashMap();
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);
        map.put("key3", "adf");
        String mapString = JSON.toJSONString(map, SerializerFeature.WriteClassName);
        System.out.println("mapString = " + mapString);
        mapString = JSON.toJSONString(map, SerializerFeature.WriteClassName);
        System.out.println("mapString ------------= " + mapString);

        Map<Enum2, Integer> map1 = new HashMap<>();
        Enum2 a = Enum2.A;
        map1.put(a, 100);
        map1.put(Enum2.B, 100);
        System.out.println("JSON.toJSONString(map1) = " + JSON.toJSONString(map1));
        SerializeWriter out = new SerializeWriter();
        JSONSerializer serializer = new JSONSerializer(out);
//        serializer.config(SerializerFeature.WriteEnumUsingToString, false);
        serializer.config(SerializerFeature.WriteClassName, true);
        serializer.write(map1);
        String s = out.toString();
        System.out.println(s);
    }

    /**
     * 1.如果bean缺少get方法, 序列化时候不会输出此key, 私有的get方法也不会输出(也不会序列话)
     * 2.json串的key=======是get方法的名字,并不是属性的名字---甚至属性存在与否都没关系, 如果一个类没有get方法就出现{}
     * 3.无参构造器也必须的有.
     * 4.在执行反序列化的时候,一些系统的默认值得，简单类型会被赋予上去.
     */
    @Test
    public void testBaseBean() {
        UserNoGetAndPrivateMethod u = new UserNoGetAndPrivateMethod();
//        u.setAge(1);
        u.setName("30");
        // ---get方法的后缀驼峰 是序列化的key
        // u.setTttt("300");
        u.setT(10 + "");
        u.setBirth(new Date());
        String s = JSON.toJSONString(u);
        System.out.println("s = " + s);
        s = " {\"t\":\"300\"}";
        System.out.println(" ====================== ");

        try {
            UserNoGetAndPrivateMethod ur = JSON.parseObject(s, UserNoGetAndPrivateMethod.class);
            System.out.println("ur = " + ur);
        } catch (Exception e) {
            // 没有无参数构造器也不会报错.
            e.printStackTrace();
        }
    }


    /**
     * 1.json 的格式问题 key 一般都是双引号的问题,可以序列化时候不指定双引号, 在反序列化的时也是没问题的。
     * 2. ------------------ 有时候在日志的文件中，看到输出都是\"是什么原因呢？---对应的字符串-就包含双引号
     * 3.{@link SerializerFeatureTestOne#removeFiledNameQuoteOne()}
     */
    @Test
    public void testParse() {
        String s = "{\"key1\":1,\"key2\":2,\"key3\":\"adf\"}";
        JSONObject jsonObject = JSON.parseObject(s);
        System.out.println("JSON.toJSONString(jsonObject) = " + JSON.toJSONString(jsonObject));
        System.out.println("jsonObject = " + jsonObject.getClass().getSimpleName());
        System.out.println("==================================== ");

        // json 要格式良好-虽然可以进行反序列化--但是尽量别这么搞
        s = "{key1:1,key2:2,key3:1,789:789}";
        JSONObject jsonObject1 = JSON.parseObject(s);
        System.out.println("jsonObject1 = " + jsonObject1);
        System.out.println("==================================== ");
        s = "{\"key1\":1,\"key2\":\"2\",\"key3\":\"ddddd\"}";
        jsonObject1 = JSON.parseObject(s);
        System.out.println("jsonObject1 = " + jsonObject1);
        // ------------------ 有时候在日志的文件中，看到输出都是\"是什么原因呢？
        Map<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("key", s);
        stringStringMap.put("key2", JSON.toJSONString(jsonObject));
        stringStringMap.put("key3", "\"11\"");

        System.out.println(" JSON.toJSONString(stringStringMap) = " + JSON.toJSONString(stringStringMap));
        Map<String, String> maps = new HashMap<>();
        maps.put("key", "value");
        System.out.println("value");
    }

    /**
     * 日期类型是个时间戳，并且对应的类型给你对应的转换了
     */
    @Test
    public void baseType() {
        AllTypeBean allTypeBean = new AllTypeBean();
        byte b = 1;
        allTypeBean.setByteValue(b);
        allTypeBean.setByteReferenceValue(new Byte(b));

        allTypeBean.setDateValue(new Date());
        allTypeBean.setDoubleReferenceValue(new Double(1.20));
        allTypeBean.setDouValue(34.4d);

        allTypeBean.setFloatValue(new Float(3.0f));
        allTypeBean.setFloatReferenceValue(33.3f);

        allTypeBean.setIntegerReferenceValue(new Integer(122));
        allTypeBean.setIntValue(333);

        allTypeBean.setStringValue("ssss");
        String json = JSON.toJSONString(allTypeBean);
        System.out.println("序列化=============数字类型没有双引号===========\n" + json);

        json = JSON.toJSONString(allTypeBean, SerializerFeature.WriteDateUseDateFormat);
        System.out.println("格式化下日期==========================\n" + json);
        System.out.println("反序列化串子没格式日期======\n");
        String ss = "{\"byValue\":1,\"byteValue\":1,\"dateValue\":1591511147058,\"douValue\":34.4,\"doubleValue\":1.2,\"floValue\":33.3,\"floatValue\":3,\"intValue\":333," +
                "\"integerValue\":122,\"stringValue\":\"ssss\"}";

        /**
         * 如果反序列化的时候是个jsonObject的话(没有指定具体的类型),
         * 日期类型：  序列化串中是数字，jsonObject里面就是数字-Long
         * 日期类型是： 序列化中是"2020-12-12 12：12：12" ,反序列话的值得就是String
         *
         * ====
         * 如果反序列化指定了具体的类型，---date--会根据 数字，或者字符串给你自己返回到日期类型
         */
        JSONObject o = JSON.parseObject(ss);
        System.out.println("反序列化串子没格式日期--没有指定具体的类型--用jsonObject去接收======\n" + o);
        System.out.println("o.getClass().getSimpleName() = " + o.getClass().getSimpleName());
        String simplename = o.get("dateValue").getClass().getSimpleName();
        // ----没指定类型他按照long来进行处理
        System.out.println("日期类型对应的是long类型 = " + simplename);


        ss = "{\"byValue\":1,\"byteValue\":1,\"dateValue\":\"2020-06-07 14:29:07\",\"douValue\":34.4,\"doubleValue\":1.2,\"floValue\":33.3,\"floatValue\":3,\"intValue\":333," +
                "\"integerValue\":122,\"stringValue\":\"ssss\"}";

        o = JSON.parseObject(ss);
        System.out.println("反序列化串子格式话日期======22222222222222\n" + o);
        System.out.println("o.getClass().getSimpleName() = " + o.getClass().getSimpleName());
        String simplename1 = o.get("dateValue").getClass().getSimpleName();
        System.out.println("日期类型对应的是String类型 = " + simplename1);

        Object o1 = JSON.parseObject(ss, AllTypeBean.class);
        System.out.println("o1 = " + o1);
    }

    /**
     * 关于一些属性--不设置的问题,引用类型跟简单类型是不一样的操作
     * 简单类型-在反序列化的时候会进行系统的默认值设置,同样引用类型也有就是null
     */
    @Test
    public void baseTypeDefault() {
        AllTypeBeanClone allTypeBean = new AllTypeBeanClone();
        allTypeBean.setStringValue("ssss");
        allTypeBean.setDouValue(100.00d);
        String json = JSON.toJSONString(allTypeBean);
        System.out.println("序列化========================:\n" + json);
        /**
         * 对于简单类型来说-你指定null 是没有问题的。==不报错=== 还会给系统默认的值
         */
        String s1 = "{\"byValue\":null,\"douValue\":100.0,\"floValue\":null,\"intValue\":0,\"stringValue\":\"ssss\"}";
        AllTypeBeanClone allTypeBean1 = JSON.parseObject(s1, AllTypeBeanClone.class);
        System.out.println(allTypeBean1);
        Long a = 33333333333333L;
        System.out.println("a = " + a.intValue());
    }

    // -------------------------------------------关于反序列化到具体对象的问题---------------------------------------------------------------
    // -------------------------------------------关于反序列化到具体对象的问题---------------------------------------------------------------
    // -------------------------------------------关于反序列化到具体对象的问题---------------------------------------------------------------
    // -------------------------------------------关于反序列化到具体对象的问题---------------------------------------------------------------

    /**
     * 直接转换成目标类型与转换成为jsonObject主要是区别什么呢？
     * 1。转换jsonObject的话-数据类型会阔大-几乎都能存放(long,BigInteger)
     * 注意:简单类型会抛出异常,引用类型会截取(int=11888888888888888888 报错, Integer i =11888888888888888888正常)
     * integer类型很大超过了范围-但是你用jsonObject接的时候能接受过来,jsonObject.getInteger("key")----就出现截取的问题
     * <p>
     * 2。转换目标类型，数值类型如果能够没有"" 括起来的话, 会对应的将值进行截短，==等价于转jsonObject 之后执行getInteger
     * 如果直接转换成为匹配对象的话-。要求就严格多了，--建议用这种方式进行反序列化-效率也高-不符合的抛出异常也是很合理的事情
     * Integer = "12" --可以，
     * Integer = "12.01" --不可以，
     * Integer = 12.01 --可以，====但是会被截短(int 也是一样的道理)
     * ---所以说在使用的时候--的尽量要跟-序列化的类型一样---对于设计传递的格式非法就报错没,也没什么好纠结的地方-系统认为请求错误也未尝不可
     * <p>
     * ==================================================
     * 高版本的就是对于数值类型兼容的，会进行响应的截短，--简单的理解就是没有用双引号括起来的那种类型.
     */
    @Test
    public void baseType1() {
        String s = "{\"byValue\":144444,\"byteValue\":1,\"dateValue\":1591955691452,\"douValue\":34.4,\"doubleValue\":1.2,\"floValue\":33.3,\"floatValue\":3.0," +
                "\"intValue\":11," +
                "\"integerReferenceValue\":11888888888888888888.45," +
                "\"integerValue\":\"344443333333333333333333333333333\",\"stringValue\":\"ssss\"}";

        JSONObject o = JSON.parseObject(s);
        String simpleName = o.get("intValue").getClass().getSimpleName();
        System.out.println("intValue = " + o.get("intValue"));
        System.out.println("simpleName = " + simpleName);

        // 对于数值类型,不断的变化,integer,大的话变成long
        simpleName = o.get("byValue").getClass().getSimpleName();
        System.out.println("byteValue = " + o.get("byValue"));
        System.out.println("simpleName = " + simpleName);

        /**
         * 将byValue":1222, 截短了,int 类型超过了长度会进行截短
         *
         * 关键设计到转型-的问题就操蛋了---尤其字符串类型,如果类型兼容还可以。
         */
        AllTypeBean allTypeBean = JSON.parseObject(s, AllTypeBean.class);
        System.out.println("allTypeBean = " + allTypeBean);

//        JSONObject.toJSONString()
    }


    /**
     * 如果日期的格式不正确 -----会报错===转换对应的类型
     * 如果数据越界了。。..---- 会给截短==这个是你转换成为jsonObject时候,在进行getXXXX(getInteger)类似的
     * <p>
     * 如果 --float -->integer类型呢 也会给你截短 12.01 转换成为int是没有问题的，但是"12.01"这样是不行的
     * 如果 --字符串 --> integer 类型 如果是兼容类型没问题,否则报错
     */
    @Test
    public void baseType2() {
        String ss = "{\"byValue\":1,\"byteValue\":90.901,\"dateValue\":\"2020-06-07 14:23:07\",\"douValue\":34.4,\"doubleValue\":1.2,\"floValue\":33.3,\"floatValue\":3," +
                "\"intValue\":339999933333333333333333333333333333333333333333333333333333333333333333333333333333339999993," +
                "\"integerValue\":\"122\",\"stringValue\":\"ssss\"}";
        JSONObject o = JSON.parseObject(ss, JSONObject.class);
        System.out.println("o = " + o);
        String intValueName = o.get("intValue").getClass().getName();
        System.out.println("intValueName = " + intValueName);
        Integer i = o.getInteger("intValue");
        System.out.println("转换成为JsonObject之后进行Integer时候可能会出现长度缩短的问题也就是截取了:::::: = " + i);
        System.out.println("o.get(\"dateValue\").getClass().getSimpleName() = " + o.get("dateValue").getClass().getSimpleName());

        // -----------------------------------------------------------
        // ------再次转换也发生异常...
        AllTypeBean AA = o.toJavaObject(AllTypeBean.class);
        System.out.println(AA);

        // -----------------------------------------------------------
        /**
         * 在格式发生错误的时候---这个获取抛出异常
         */
        System.out.println(o.getDate("dateValue"));
        System.out.println(" =============================== ");
        AllTypeBean al = JSON.parseObject(ss, AllTypeBean.class);
        System.out.println("al = " + al);
    }

    /**
     * 1.fastJson转换的时候一步到位,--格式不兼容越界全部异常处理.不要用jsonObject作为桥梁
     * <p>
     * JSONObject jsonObject = JSON.parseObject(jsonstring); A a = jsonObject.toJavaObject(A.class);
     * 推荐方式 ：A a = JSON.parseObject(jsonstring, A.class);
     */
    @Test
    public void baseType3() {
        System.out.println("true = " + Integer.MAX_VALUE);
        String ss = "{\"byValue\":1,\"byteValue\":90.901,\"dateValue\":\"2020-06-07 14:23:07\",\"douValue\":34.4,\"doubleValue\":1.2,\"floValue\":33.3,\"floatValue\":3," +
                "\"intValue\":3333," +
                "\"integerReferenceValue\":\"2147483647333331\",\"stringValue\":\"ssss\"}";
        AllTypeBean al = JSON.parseObject(ss, AllTypeBean.class);
        System.out.println("al = " + al);
    }
}
