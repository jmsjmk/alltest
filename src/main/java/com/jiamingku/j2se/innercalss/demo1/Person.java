package com.jiamingku.j2se.innercalss.demo1;

@SuppressWarnings("all")
public class Person {

    private static String test = "test";
    private String testOutClass = "outClass";
    String test1 = "innerClass";

    public void a() {
        System.out.println("this.test = " + this.test);
    }

    /**
     * 构建一个内部类
     */
    public class Name {
        private String t = "t";
        /* 内部类可以与外围类，变量名称相同*/
        String test1 = "innerClass";
        private String firstName;
        public String secondName;

        public void p() {
            System.out.println("t = " + testOutClass);
            System.out.println("===========================================");
            System.out.println("sex:" + sex);
            // 操作的是自己的范围的变量
            System.out.println("test   = " + this.test1);
            /* 看上去this操作了静态的变量，其实不是的*/
            System.out.println("test   = " + Person.this.test1);
            System.out.println("test1 = " + Person.this.test);
        }
    }

    public static void abc() {
        // 内部类可以定义在方法体里面，但是方法里面不能在定义静态内部类，同样不能有访问修饰符
        class t {

        }
//         static class boy{}
    }

    public static void main(String[] args) {
        // 语法还是有点古怪
        Person.Name pn1 = new Person().new Name();
        pn1.secondName = "secondName";
        // 受到访问修饰符的限制
        pn1.firstName = "firstName";
        Person person = new Person();
        Person.Name pn2 = person.new Name();
        pn1.p();

        Person.Group group = new Person.Group();
    }

    public static class Group {
    }

    private char sex;

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public void pringInnerClass() {
        Name name = new Name();
        System.out.println("name:" + name.firstName);
        System.out.println("name:" + name.secondName);
    }
}