package com.jiamingku.lambda.sgg.com.atguigu.java8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/*
 * 一、 Stream 的操作步骤
 *
 * 1. 创建 Stream
 *
 * 2. 中间操作
 *
 * 3. 终止操作
 */
public class TestStreamAPI1 {

    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 666.66),
            new Employee(101, "张三", 18, 9999.99),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    //2. 中间操作
    /*
        映射
		map——接收 Lambda ， 将元素转换成其他形式或提取信息。
		     也就是:接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。并且返回一个新流

		flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
	 */
    @Test
    public void test1() {

//        Stream<Employee> es = emps.stream();
//        Stream<String> str = emps.stream().map((e) -> e.getName());
//
//        System.out.println("-------------------------------------------");
//
        List<String> strList = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
//
//        Stream<String> stream = strList.stream().map(String::toUpperCase);
//        stream.forEach(System.out::println);

        // 每个元素是一个stream对象
        Stream<Stream<Character>> stream2 = strList.stream().map(TestStreamAPI1::filterCharacter);

        stream2.forEach((sm) -> {
            sm.forEach(System.out::println);
        });

        System.out.println("---------------------------------------------");

        // 合并成为一个对象
        Stream<Character> stream3 = strList.stream().flatMap(TestStreamAPI1::filterCharacter);
        stream3.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    /*
        sorted()——自然排序,如果排序聚合没有实现接口--就会出现 异常
        sorted(Comparator com)——定制排序
     */
    @Test
    public void test2() {
        emps.stream()
                .map(Employee::getName)
                .sorted()
                .forEach(System.out::println);

        System.out.println("------------------------------------");

        // 这个排序你必须的也是必须的可比较的接口
        // emps.stream().sorted().forEach(System.out::println);

        System.out.println("------------------------------------");

        emps.stream().sorted((x, y) -> {
            if (x.getAge() == y.getAge()) {
                return x.getName().compareTo(y.getName());
            } else {
                return Integer.compare(x.getAge(), y.getAge());
            }
        }).forEach(System.out::println);

        System.out.println(" ==== ");
        emps.stream().sorted(Comparator.comparing(Employee::getAge).thenComparing(Comparator.comparing(Employee::getName)))
                .forEach(System.out::println);


        emps.stream().sorted(Comparator.comparing(Employee::getAge).thenComparing(Employee::getName))
                .forEach(System.out::println);

        emps.stream().sorted(Comparator.comparing(Employee::getAge).thenComparing(Employee::getName))
                .forEach(System.out::println);
    }

    @Test
    public void tt() {
        emps.stream().filter(e -> {
            System.out.println(" 段落");
            return e.getSalary() > 5000;
        }).limit(2).forEach(System.out::println);
    }

    public <T> void test() {
        List<T> l = new ArrayList<>();
        List<Object> ls = (List<Object>)l;

    }
}
