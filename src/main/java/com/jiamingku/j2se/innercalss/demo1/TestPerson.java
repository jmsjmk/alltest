package com.jiamingku.j2se.innercalss.demo1;

/**
 * Created by jiamingku on 2018/5/17.
 */
@SuppressWarnings("all")
public class TestPerson {
    public static void main(String[] args) {
        // 语法还是有点古怪
        Person.Name pn1 = new Person().new Name();
        pn1.secondName = "secondName";
        // 受到访问修饰符的限制
        // pn1.firstName = "firstName";
        Person person = new Person();
        Person.Name pn2 = person.new Name();
        pn1.p();
    }
}
