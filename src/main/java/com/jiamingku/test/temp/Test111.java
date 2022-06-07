package com.jiamingku.test.temp;

import java.util.Objects;

public class Test111 {
    private String name;
    private String age;

    /**
     * 递归 在问题规模便强大的时候会出现 栈溢出问题
     *
     * @param args
     */
    public static void main(String[] args) {
        int seed = 114040;
//        int a = sum(seed);
//        System.out.println(a);

        int a1 = sum1(seed, 0);
        System.out.println(a1);
    }


    public static int sum(int i) {
        if (i == 1) return 1;
        int result = i + sum(i - 1);
        return result;
    }

    public static int sum1(int i, int j) {
        if (i == 1) return j+1;
        return sum1((i - 1), j + i );
    }

    public synchronized void asMethod() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test111 test111 = (Test111) o;
        return Objects.equals(name, test111.name) && Objects.equals(age, test111.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    // -104682389
    // -104682389
//    public static void main(String[] args) {
//        Integer[] a = new Integer[10];
//        System.out.println(a.getClass());
//        int[] b = new int[100];
//        System.out.println(b.getClass());
//        System.out.println("Integer.class = " + Integer.class);
//        System.out.println("int.class = " + int.class);
//        System.out.println( char[].class);
//        System.out.println( Character[].class);
//        System.out.println(void.class);
//    }
}
