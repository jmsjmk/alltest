package com.jiamingku.lambda.sgg.com.atguigu.exer;

import java.util.*;
import java.util.stream.Collectors;

import com.jiamingku.lambda.sgg.com.atguigu.java8.Employee;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;


public class TestLambda {

    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "李四", 59, 6666.66),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    @Test
    public void intnull() {
//        Integer integer = null;
//        int a = integer;
//        OptionalInt.of(integer);
        Object o = Optional.empty().get();
        if (Objects.isNull(o)) {
            System.out.println("o = " + o);
        }
    }


    @Test
    public void ttt() {
        emps = new ArrayList<>();
        int a = OptionalInt.of(emps.stream().mapToInt(x -> 1).reduce(0, (x, y) -> x + y)).getAsInt();
        System.out.println("a = " + a);
        a = emps.stream().map(x -> 1).reduce(0, (x, y) -> x + y).intValue();
        System.out.println("a = " + a);
        a = emps.stream().map(x -> 1).reduce(Integer::sum).orElse(1000000);
        System.out.println("a = " + a);
    }

    @Test
    public void test1() {


        Object o = null;
        System.out.println("o.getClass().getSimpleName() = " + o.getClass().getSimpleName());


        Collections.sort(emps, (e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return -Integer.compare(e1.getAge(), e2.getAge());
            }
        });

        for (Employee emp : emps) {
            System.out.println(emp);
        }
    }

    @Test
    public void test2() {
        String trimStr = strHandler("\t\t\t 我大尚硅谷威武   ", (str) -> str.trim());
        System.out.println(trimStr);

        String upper = strHandler("abcdef", (str) -> str.toUpperCase());
        System.out.println(upper);

        String newStr = strHandler("我大尚硅谷威武", (str) -> str.substring(2, 5));
        System.out.println(newStr);
    }

    //需求：用于处理字符串
    public String strHandler(String str, MyFunction mf) {
        return mf.getValue(str);
    }

    @Test
    public void test3() {
        op(100L, 200L, (x, y) -> x + y);

        op(100L, 200L, (x, y) -> x * y);
    }

    //需求：对于两个 Long 型数据进行处理
    public void op(Long l1, Long l2, MyFunction2<Long, Long> mf) {
        System.out.println(mf.getValue(l1, l2));
    }

}
