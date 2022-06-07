package com.jiamingku.fastjson.test.learn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

public class SerializerFeatureTestTwo {

    public class Student {
        private String name;
        private int age;
        private boolean isMale;
        private Student gf;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public boolean isMale() {
            return isMale;
        }

        public void setMale(boolean isMale) {
            this.isMale = isMale;
        }

        public Student getGf() {
            return gf;
        }

        public void setGf(Student gf) {
            this.gf = gf;
        }
    }

    private static ValueFilter filter = new ValueFilter() {
        @Override
        public Object process(Object obj, String s, Object v) {
            if (v == null)
                return "";
            return v;
        }
    };

    public static void main(String[] args) {
        new SerializerFeatureTestTwo().foo();
        new SerializerFeatureTestTwo().bar();


        System.out.println(" ======= ");
        Map<String, Object> m = new HashMap<>();
        m.put("code", 100);
        m.put("value", "ddd");
        String s = JSON.toJSONString(m);
        JSONObject j = JSON.parseObject(s);
        System.out.println("j.getString(\"code\") = " + j.getString("code"));


    }

    private void foo() {
        System.out.println("foo()---------------------------");
        JSONObject j1 = new JSONObject();
        j1.put("name", "zhangsan");
        j1.put("age", 13);
        j1.put("isMale", true);
        j1.put("gf", null);

        Map<String, Object> fav = new HashMap<>();

        Set<String> books = new HashSet<>();
        books.add("三国");
        books.add("史记");

        fav.put("history", books);
        String[] arts = new String[]{};
        fav.put("arts", arts);
        String[] musics = new String[]{"北京欢迎你", "画心"};
        fav.put("musics", musics);
        List<String> sports = new ArrayList<>();
        fav.put("sports", sports);
        j1.put("fav", fav);
        List<Student> classmates = new ArrayList<Student>();
        classmates.add(new Student());
        Student lisi = new Student();
        lisi.setMale(false);
        lisi.setAge(11);
        classmates.add(lisi);
        Student zhangsan = new Student();
        zhangsan.setAge(13);
        zhangsan.setName("张三");
        zhangsan.setMale(true);
        zhangsan.setGf(lisi);
        classmates.add(zhangsan);
        j1.put("classmates", classmates);
        String str = null;
        j1.put("str", str);

        System.out.println(j1.toString());
        System.out.println();
        // -----字符串对象--null 话会变成""， 注意str 这个属性，不是按照自负传来进行处理的
        System.out.println(JSON.toJSONString(j1,  SerializerFeature.WriteNullStringAsEmpty));
        System.out.println();
        // -----所有的null 全部 变成 null
        System.out.println(JSON.toJSONString(j1, SerializerFeature.WriteMapNullValue));
        System.out.println();

        // ----- 字符串变成空传---空 就是null
        System.out.println(JSON.toJSONString(j1, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
        System.out.println();
        System.out.println(" 65555555=== " );
        System.out.println(JSON.toJSONString(j1, filter, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
        System.out.println();
        System.out.println(JSON.toJSONString(j1, SerializerFeature.WriteNullStringAsEmpty));
        System.out.println();
        System.out.println(JSON.toJSONString(j1, filter, SerializerFeature.WriteNullStringAsEmpty));
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        Map<String, JSONObject> m = new HashMap<String, JSONObject>();
        m.put("key", j1);
        System.out.println(JSON.toJSONString(m, SerializerFeature.WriteNonStringKeyAsString, SerializerFeature.WriteNullStringAsEmpty));
        System.out.println(JSON.toJSONString(m, filter, SerializerFeature.WriteNonStringKeyAsString, SerializerFeature.WriteNullStringAsEmpty));

    }

    private void bar() {
        System.out.println("bar()---------------------------");
        Student zhangsan = new Student();
        zhangsan.setAge(13);
        zhangsan.setName("张三");
        zhangsan.setMale(true);
        Student lisi = new Student();
        // lisi.setName("lisi");
        lisi.setMale(false);
        lisi.setAge(11);
        zhangsan.setGf(lisi);
        System.out.println(JSON.toJSONString(zhangsan, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
        System.out.println(JSON.toJSONString(zhangsan, SerializerFeature.WriteMapNullValue));
        System.out.println(JSON.toJSONString(zhangsan, SerializerFeature.WriteNullStringAsEmpty));
        // ----效率挺好
        System.out.println(JSON.toJSONString(zhangsan));
        System.out.println(JSON.toJSONString(zhangsan, filter));
        System.out.println(JSON.toJSONString(zhangsan, filter, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty));
    }

}