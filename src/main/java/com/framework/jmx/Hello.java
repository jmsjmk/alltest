package com.framework.jmx;

/*
 * 该类名称必须与实现的接口的前缀保持一致（即MBean前面的名称
 */
public class Hello implements HelloMXBean {
    private String name;

    private String age;


//    public Set<Student> llSets() {
//        final Set<Student> sets = new HashSet();
//        System.out.println("get method ....");
//        for (int i = 0; i < 10000; i++) {
//            Student s = new Student();
//            sets.add(s);
//        }
//        return sets;
//    }


    public Student[] llSets() {
        final Student[] sets = new Student[10];
        System.out.println("get method ....");
        for (int i = 0; i < 10; i++) {
            Student s = new Student();
            sets[i] = s;
        }
        return sets;
    }


//    public void setSets(Set<Object> sets) {
//
//        System.out.println("dddd");
//        this.sets = sets;
//    }

    public void getTelephone() {
        System.out.println("get Telephone");
    }

    public void helloWorld() {
        System.out.println("hello world");
    }

    public void helloWorld(String str) {
        System.out.println("helloWorld:" + str);
    }

    public String getName() {
        System.out.println("get name 123");
        return name;
    }

    public void setName(String name) {
        System.out.println("set name 123");
        this.name = name;
    }

    public String getAge() {
        System.out.println("get age 123");
        return age;
    }

    public void setAge(String age) {
        System.out.println("set age 123");
        this.age = age;
    }
}