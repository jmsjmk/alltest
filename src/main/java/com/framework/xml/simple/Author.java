package com.framework.xml.simple;

import org.simpleframework.xml.Attribute;

public class Author {
    @Attribute(required=false)
    private String name;     //作者名
    @Attribute(required=false)
    private String phone;   //作者电话
    public Author() {
        super();
    }
    public Author(String name, String phone) {
        super();
        this.name = name;
        this.phone = phone;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Override
    public String toString() {
        return "Author [name=" + name + ", phone=" + phone + "]";
    }

}
