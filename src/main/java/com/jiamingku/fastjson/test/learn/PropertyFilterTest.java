package com.jiamingku.fastjson.test.learn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;

public class PropertyFilterTest {

    private int id;
    private String name;
    private String add;
    private String old;

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public static void main(String[] args) {
        PropertyFilterTest u = new PropertyFilterTest();
        u.setName("name");
        u.setAdd("111");

        PropertyFilter propertyFilter = (obj, name, value) -> {
            boolean flag = true;
            if (name.equalsIgnoreCase("add")) {
                flag = false;
                System.out.println(" add 属性不会被序列化的...");
            }
            return flag;
        };
        System.out.println("JSON.toJSONString(u) = " + JSON.toJSONString(u, propertyFilter));
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("add='").append(add).append('\'');
        sb.append(", id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", old='").append(old).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
