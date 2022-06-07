package com.jiamingku.fastjson.test.learn;

import com.alibaba.fastjson.JSON;
import com.jiamingku.fastjson.test.learn.bean.StudentJsonFileld;
import com.jiamingku.fastjson.test.learn.bean.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jiamingku on 2017/4/11.
 */
public class AnnotionTest {
    /*
        @JSONField(ordinal = 2)
        @JSONField(ordinal = 1)
        @JSONField(deserialize=false)
        @JSONField(serialize=false)
        @JSONField(format="yyyyMMdd")
        @JSONField(name="ID")
     */
    @Test
    public void testAnnotion() {
        com.jiamingku.fastjson.test.learn.bean.Student s = new com.jiamingku.fastjson.test.learn.bean.Student();
        User u1 = new User();
        u1.setAge(1);
        u1.setName("name1");
        u1.setBirth(new Date());

        User u2 = new User();
        u2.setAge(2);
        u2.setName("name2");
        Date d1 = new Date();
        u2.setBirth(d1);
        System.out.println("d1.getTime() = " + d1.getTime());

        List<User> list = new ArrayList<>();
        list.add(u1);
        list.add(u1);
        list.add(u2);

        s.setName("studnetname");
        s.setAge(100);
        s.setUsers(list);

        String str = JSON.toJSONString(s, true);
        System.out.println(str);
        String str1 = JSON.toJSONString(s, false);
        System.out.println("str1 = " + str1);
    }

    @Test
    public void strToJava() {
        String a = "{\"age\":100,\"name\":\"studnetname\",\"ts\":[{\"age\":1,\"birth\":1491894079492," +
                "\"name\":\"name1\"},{\"age\":2,\"birth\":1491894079492,\"name\":\"name2\"}]}";
        StudentJsonFileld s = JSON.parseObject(a, StudentJsonFileld.class);
        System.out.println(s.getName());
        for (User u : s.getUsers()) {
            System.out.println(u);
        }
    }
}
