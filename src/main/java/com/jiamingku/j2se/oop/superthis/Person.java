package com.jiamingku.j2se.oop.superthis;

/**
 * Created by mingku.jia on 2017/12/25.
 */
public class Person extends ALL {

    public void f() {
        System.out.println("Person");
    }


    public String name;
    public int age;
    public static final String staticNameValue = 100 + "";

    public Person() {
        System.out.println("  Person 构造期");
    }

    public void testPPPP() {
        System.out.println(" =-------------------------------- ");
    }

    /**
     * this: 再次理解，this指向实际调用的对象,并不一定指向当前类(this并不是指向当前类)
     */
    private void b() {
        System.out.println("this.getClass().getSimpleName() = " + this.getClass().getSimpleName());
        System.out.println("person b method ..");
    }

    public void test() {
        this.b();
    }

    public void aaa() {
        ALL all = new ALL();
        all.t();
    }

    public Person(String name, int age) {
        //this = Person{name='null', age=0}
        System.out.println("this = " + this);
        // this 引用这个创建的对象在重新赋值
        this.name = name;
        // this 引用这个创建的对象在重新赋值
        this.age = age;
        System.out.println("this.toString() = " + this.toString());
        System.out.println(" parent-==============================over ");
    }

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

    public static void main(String[] args) {
        Person person = new Person("name", 1);
        System.out.println("person = " + person);
    }

    @Override
    public String toString() {
        String a = "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
        System.out.println(" " + a);
        return a;
    }
}
