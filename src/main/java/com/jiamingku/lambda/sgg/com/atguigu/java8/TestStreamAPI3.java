package com.jiamingku.lambda.sgg.com.atguigu.java8;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class TestStreamAPI3 {

    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 79, 6666.66, Employee.Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION),
            new Employee(104, "赵六1", 8, 7777.77, Employee.Status.BUSY),
            new Employee(104, "赵六2", 8, 7777.77, Employee.Status.FREE),
//            new Employee(104, "赵六3", 8, 7777.77, Employee.Status.FREE),
            new Employee(104, "赵六4", 8, 7777.77, Employee.Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
    );

    @Test
    public void testMap() {

//        Map<Integer, String> map1 = emps.stream().collect(Collectors.toMap(Employee::getId, Employee::getName));

//        System.out.println("map = " + map);

        Map<Integer, String> map1 = emps.stream().collect(Collectors.toMap(Employee::getId, Employee::getName, (v1, v2) -> v2));
        System.out.println("map1 = " + map1);

        Map<Integer, Employee> map2 = emps.stream().collect(Collectors.toMap(Employee::getId, Function.identity()));
//        emps.stream().map()
//        emps.stream().peek();

    }

    //3. 终止操作
    /*
    归约
	reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
	 */
    @Test
    public void test1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        long a = list.stream().count();
        System.out.println("a = " + a);

//        list = new ArrayList<>();
        a = list.stream().map(e -> 1).reduce(Integer::sum).orElse(10000);
        System.out.println("a = " + a);

        Integer sum = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        System.out.println("-----------因为没有初始值,所以会返回optional-----------------------------");
        Optional<Double> op = emps.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println(op.get());
        System.out.println(" ===================================== ");
        int ssss = list.stream().mapToInt(i -> i.intValue()).reduce((x, y) -> x + y).orElse(0);
        System.out.println("ssss = " + ssss);
        list.stream().mapToInt(Integer::intValue).reduce(Integer::sum);

        int suum1 = list.stream().reduce(Integer::sum).orElse(0);
        System.out.println("suum1 = " + suum1);

        System.out.println(" ================ ");
        // reduce 的处理流程就是-- 每次x+y 的结果 赋给x
        Integer sum1 = list.stream().reduce(0, (x, y) -> {
            System.out.println("x = " + x);
            return x + y;
        });
    }

    //需求：搜索名字中 “六” 出现的次数
    @Test
    public void test2() {
        Optional<Integer> sum = emps.stream()
                .map(Employee::getName)
                .flatMap(TestStreamAPI1::filterCharacter)
                .map((ch) -> {
                    if (ch.equals('六'))
                        return 1;
                    else
                        return 0;
                }).reduce(Integer::sum);

        System.out.println(sum.get());
    }

    //collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
    @Test
    public void test3() {
        List<String> list = emps.stream().map(Employee::getName).collect(Collectors.toList());

        list.forEach(System.out::println);

        System.out.println("----------------------------------");

        Set<String> set = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());

        set.forEach(System.out::println);

        System.out.println("----------------------------------");

        HashSet<String> hs = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));

        hs.forEach(System.out::println);
    }

    @Test
    public void test4() {
        // 1.---查询薪水最大的--金额，emps如果是空集合就会出现空-所以返回的是optional
        Optional<Double> max = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.maxBy(Double::compare));
        System.out.println(max.get());

        // 2.---写法
        double d1 = emps.stream().map(Employee::getSalary).sorted(Comparator.reverseOrder()).findFirst().orElse(0.0);
        System.out.println("d = " + d1);

        Employee e = emps.stream().sorted(Comparator.comparing(Employee::getSalary, Comparator.reverseOrder())).findFirst().orElse(null);
        System.out.println("e = " + e);


        // ---------------------------可以通过收集时候获取到最大最小------------
        Optional<Employee> op = emps.stream().collect(Collectors.minBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));

        System.out.println(op.get());
        emps = new ArrayList<>();
        Double sum = emps.stream().collect(Collectors.summingDouble(Employee::getSalary));
        if (sum == null) {
            System.out.println(" ========null ");
        }
        System.out.println(sum);

        double d = emps.stream().map(Employee::getSalary).reduce(0.0d, Double::sum);
        System.out.println("d = " + d);

        Double avg = emps.stream().collect(Collectors.averagingDouble(Employee::getSalary));

        System.out.println(avg);

        // ----其实下面是等效果的
        long a = emps.stream().count();
        Long count = emps.stream().collect(Collectors.counting());

        System.out.println(count);

        System.out.println("--------------------------------------------");

        DoubleSummaryStatistics dss = emps.stream().collect(Collectors.summarizingDouble(Employee::getSalary));

        System.out.println(dss.getMax());
        System.out.println(dss.getAverage());
        System.out.println("dss.getCount() = " + dss.getCount());
    }

    //分组
    @Test
    public void test5() {
        new Employee();

        Map<Employee.Status, List<Employee>> map = emps.stream().collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);

        System.out.println(" ============ ");

        for (Map.Entry<Employee.Status, List<Employee>> entry : map.entrySet()) {
            System.out.println("entry = " + entry.getKey());

            System.out.println("entry.getValue() = " + entry.getValue());
            System.out.println("==");
        }


        System.out.println(" ==== ");
        System.out.println(" ==== ");
        System.out.println(" ---------------------------------------------------------------------------------------==== ");
        System.out.println(" ==== ");

        // 这个属于结果集在进行 一次加工
        Map<Employee.Status, List<Integer>> MAP = emps.stream().
                collect(Collectors.groupingBy(Employee::getStatus, Collectors.collectingAndThen(Collectors.toList(), list ->
                        list.stream().map(Employee::getId).collect(Collectors.toList()))));

        Map<Employee.Status, List<Integer>> MAP1 = emps.stream().
                collect(Collectors.groupingBy(Employee::getStatus, Collectors.collectingAndThen(Collectors.toList(), list ->
                        list.stream().map(Employee::getId).collect(Collectors.toList()))));

        Map<Employee.Status, List<Integer>> MAP11 = emps.stream().
                collect(Collectors.groupingBy(Employee::getStatus, Collectors.collectingAndThen(Collectors.toList(), list ->
                        list.stream().map(Employee::getId).collect(Collectors.toList()))));

        // ----更简单的写法
        Map<Employee.Status, List<Integer>> MAP22211 = emps.stream().
                collect(Collectors.groupingBy(Employee::getStatus, Collectors.mapping(Employee::getId, Collectors.toList())));

        Map<String, String> map111 = new HashMap<>();
        System.out.println("MAP = " + MAP);
    }

    //多级分组
    @Test
    public void test6() {
        Map<Employee.Status, Map<String, List<Employee>>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (e.getAge() >= 60)
                        return "老年";
                    else if (e.getAge() >= 35)
                        return "中年";
                    else
                        return "成年";
                })));
        System.out.println(map);
        System.out.println(" =================== ");
//		for (Map.Entry<Employee.Status,> map.entrySet())
    }

    //分区
    @Test
    public void test7() {
        Map<Boolean, List<Employee>> map = emps.stream().collect(Collectors.partitioningBy((e) -> e.getSalary() >= 5000));
        System.out.println(map);
        for (Map.Entry<Boolean, List<Employee>> entry : map.entrySet()) {
            System.out.println("entry = " + entry.getKey());
            System.out.println("entry.getValue() = " + entry.getValue());
        }
    }

    //
    @Test
    public void test8() {
        String str = emps.stream().map(Employee::getName).collect(Collectors.joining(",", "----", "----"));
        System.out.println(str);
    }

    @Test
    public void test9() {
        emps = new ArrayList<>();
        Optional<Double> sum = emps.stream().map(Employee::getSalary).collect(Collectors.reducing(Double::sum));
        System.out.println(sum.get());
    }


    public static void main(String[] args) {

    }


    @Test
    public void ttt33() {
        //3 apple, 2 banana, others 1
        List<String> items = Arrays.asList("apple", "apple", "banana","banana","banana","banana","banana", "apple", "orange", "banana", "papaya");

        Map<String, Long> result = items.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("result = " + result);
        Map<String, Long> finalMap = new LinkedHashMap<>();
        Map<String, Long> finalMa1p = new LinkedHashMap<>();

        //Sort a map and add to finalMap
        result.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue()
                        .reversed()).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

        System.out.println(finalMap);

        result.entrySet().stream()
                .forEachOrdered(e -> finalMa1p.put(e.getKey(), e.getValue()));
//        System.out.println(finalMa1p);
//        result.entrySet().stream()
//                .sorted(Map.comparingByValue()
//                        .reversed()).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));
//
//        System.out.println(finalMap);

    }
}
