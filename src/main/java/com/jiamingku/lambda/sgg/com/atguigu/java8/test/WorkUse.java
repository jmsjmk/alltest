package com.jiamingku.lambda.sgg.com.atguigu.java8.test;

import com.jiamingku.lambda.sgg.com.atguigu.java8.Employee;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 其实下面的写法就是一种方式的变种:
 * 1) collect(Collectors.groupingBy(Employee::getStatus, Collectors.collectingAndThen(
 * 2) collect(Collectors.groupingBy(Employee::getStatus, Collectors.mapping(
 * 3) collect(Collectors.groupingBy(Employee::getStatus, Collectors.reducing(
 * 4) collect(Collectors.groupingBy(Employee::getStatus, Collectors.toMap(
 */
@SuppressWarnings("all")
public class WorkUse {

    public static void main(String[] args) {
        String a = WorkUse.class.getName();
        System.out.println(a);
    }

    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 79, 6666.66, Employee.Status.BUSY),
            new Employee(101, "张三", 18, 66661.66, Employee.Status.FREE),
            new Employee(103, "王五", 28, 66662.66, Employee.Status.VOCATION),
            new Employee(104, "赵六1", 8, 66663.66, Employee.Status.BUSY),
            new Employee(104, "赵六1", 8, 66663.66, Employee.Status.BUSY),
            new Employee(105, "田七", 38, 66664.66, Employee.Status.BUSY)
    );


    @Test
    public void testMap() {


        Map<Integer, String> collect2 = emps.stream().collect(Collectors.toMap(Employee::getId, Employee::getName, (v1, v2) -> v2));
        System.out.println(collect2);

//        Map<Integer, String> collect = emps.stream().collect(Collectors.toMap(Employee::getId, Employee::getName));
//        Map<Integer, Employee> collect1 = emps.stream().collect(Collectors.toMap(Employee::getId, Function.identity()));
//
//
//        Map<Integer, Employee> collect2 = emps.stream().collect(Collectors.toMap(Employee::getId, Function.identity()));
//        Map<Integer, String> coll1 = emps.stream().collect(Collectors.toMap(Employee::getId, Employee::getName, (v2, v1) -> v1));

        Map<Integer, String> collect = employees.stream().collect(Collectors.toMap(Employee::getId, Employee::getName, (v1, v2) -> v2));
        System.out.println("collect = " + collect);

        Map<Integer, Employee> collect1 = employees.stream().collect(Collectors.toMap(Employee::getId, Function.identity(), (v1, v2) -> v1));
        System.out.println(collect1);
    }

    /**
     * List<Employee> emps = Arrays.asList(
     * new Employee(102, "李四", 79, 6666.66, Employee.Status.BUSY),
     * new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
     * new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION),
     * new Employee(104, "赵六1", 8, 7777.77, Employee.Status.BUSY),
     * new Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
     * );
     * <p>
     * 1)Map<Employee.Status, List<Employee>>
     * 2) Map<Employee.Status, List<Integer>>
     * 2.1)
     * 2.2)
     */
    //分组
    @Test
    public void test5555555555555555555555555555() {
        new Employee();


        Map<Employee.Status, List<Employee>> map = emps.stream().collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);
        // 这个属于结果集在进行 一次加工
        Map<Employee.Status, List<Integer>> MAP = emps.stream().
                collect(Collectors.groupingBy(Employee::getStatus, Collectors.collectingAndThen(Collectors.toList(), list ->
                        list.stream().map(Employee::getId).collect(Collectors.toList()))));


        Map<Integer, Map<Integer, String>> collect1 = emps.stream().collect(Collectors.groupingBy(Employee::getId, Collectors.toMap(Employee::getId, Employee::getName, (v1, v2) -> v2)));

        // ----更简单的写法
        Map<Employee.Status, List<Integer>> MAP22211 = emps.stream().
                collect(Collectors.groupingBy(Employee::getStatus, Collectors.mapping(Employee::getId, Collectors.toList())));


        Map<Employee.Status, Map<Integer, String>> collect = emps.stream().collect(Collectors.groupingBy(Employee::getStatus,
                Collectors.toMap(Employee::getId, Employee::getName, (v1, v2) -> v2)));
        /**
         * 使用的时候一定要注意 一些null的问题，属性位null的等
         */
        Map<Employee.Status, Map<Double, Integer>> doubleIntegerMap = emps.stream().collect(Collectors.groupingBy(Employee::getStatus,
                Collectors.toMap(Employee::getSalary, Employee::getAa, (v1, v2) -> v2)));


        Map<Employee.Status, Integer> MAP2221331 = emps.stream().
                collect(Collectors.groupingBy(Employee::getStatus, Collectors.reducing(0, Employee::getAge, Integer::sum)));

        Map<String, String> map111 = new HashMap<>();
        System.out.println("MAP = " + MAP);
    }


    //分组
    @Test
    public void testdsfsdflsdlfkjsldfksldjflsdjflskdfjlsd5() {
        new Employee();

        // 这个属于结果集在进行 一次加工
//        Map<Employee.Status, List<Integer>> MAP = emps.stream().
//                collect(Collectors.groupingBy(Employee::getStatus, Collectors.collectingAndThen(Collectors.toList(), list ->
//                        list.stream().map(Employee::getId).collect(Collectors.toList()))));
//
//        Map<Employee.Status, List<Integer>> MAP1 = emps.stream().
//                collect(Collectors.groupingBy(Employee::getStatus, Collectors.collectingAndThen(Collectors.toList(), list ->
//                        list.stream().map(Employee::getId).collect(Collectors.toList()))));
//
//        Map<Employee.Status, List<Integer>> MAP11 = emps.stream().
//                collect(Collectors.groupingBy(Employee::getStatus, Collectors.collectingAndThen(Collectors.toList(), list ->
//                        list.stream().map(Employee::getId).collect(Collectors.toList()))));
//
//        Map<Employee.Status, Map<Double, Integer>> doubleIntegerMap = emps.stream().collect(Collectors.groupingBy(Employee::getStatus,
//                Collectors.toMap(Employee::getSalary, Employee::getAa))
//        );

        Map<Employee.Status, List<Integer>> MAP111111 = emps.stream().collect(Collectors.groupingBy(Employee::getStatus,
                Collectors.collectingAndThen(Collectors.toList(), l -> l.stream().map(Employee::getId).collect(Collectors.toList()))));
        System.out.println(MAP111111);


        // ----更简单的写法
        Map<Employee.Status, List<Integer>> MAP22211 = emps.stream().
                collect(Collectors.groupingBy(Employee::getStatus, Collectors.mapping(Employee::getId, Collectors.toList())));
        System.out.println(MAP22211);

        Map<Employee.Status, Integer> MAP2221331 = emps.stream().
                collect(Collectors.groupingBy(Employee::getStatus, Collectors.reducing(0, Employee::getAge, Integer::sum)));

        Map<String, String> map111 = new HashMap<>();
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

    List<Employee> employees = Arrays.asList(
            new Employee(102, "李四", 59, 666.66),
            new Employee(101, "张三", 18, 9999.99),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    @Test
    public void test8() {
        String str = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",", "----", "----"));

        System.out.println(str);
    }

    // --------------------自己的额外操作
    @Test
    @SuppressWarnings("all")
    public void test0() {
        // 1.
        Map<Integer, List<Integer>> map = employees.stream().collect(Collectors.toMap(Employee::getAge, employee -> {
                    List<Integer> list1 = new ArrayList<>();
                    list1.add(employee.getAge());
                    return list1;
                }, (List<Integer> v1, List<Integer> v2) -> {
                    v2.addAll(v1);
                    return v2;
                }
        ));

        Map<Integer, List<Integer>> map1 = employees.stream().collect(Collectors.toMap(Employee::getAge,
                employee -> {
                    List<Integer> list1 = new ArrayList<>();
                    list1.add(employee.getAge());
                    return list1;
                }, (v1, v2) -> {
                    v2.addAll(v1);
                    return v2;
                }
        ));

        System.out.println("map = " + map);
        System.out.println(" ================ ");
        Map<Integer, List<Integer>> map3 = employees.stream().collect(Collectors.toMap(Employee::getAge, employee -> {
                    List<Integer> list1 = new ArrayList<>();
                    list1.add(employee.getAge());
                    return list1;
                }, (v1, v2) -> {
                    v2.addAll(v1);
                    return v2;
                }
        ));
    }
    // --------------------reduct 的使用方法-----------------------------------------

    public void testReduce() {
        Optional accResult = Stream.of(1, 2, 3, 4)
                .reduce((acc, item) -> {
                    System.out.println("acc : " + acc);
                    System.out.println("item : " + item);
                    acc += item;
                    System.out.println("acc+ : " + acc);
                    System.out.println("--------");
                    return acc;
                });
        System.out.println("accResult: " + accResult.get());
        Stream.of(1, 2, 3, 4).reduce(Integer::sum).orElse(100);
    }


    /**
     * 多级分组
     */
    @Test
    public void testt() {
        //3 apple, 2 banana, others 1
        List<Item> items = Arrays.asList(
                new Item("apple", null, new BigDecimal("9.99")),
                new Item("banana", 20, new BigDecimal("19.99")),
                new Item("orang", 10, new BigDecimal("29.99")),
                new Item("watermelon", 10, new BigDecimal("29.99")),
                new Item("papaya", 20, new BigDecimal("9.99")),
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 10, new BigDecimal("19.99")),
                new Item("apple", 20, new BigDecimal("9.99"))
        );
        Map<String, Integer> sum = items.stream().
                filter(e -> e.getQty() != null).
                collect(Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));
        System.out.println(sum);

        Map<String, List<Item>> list11 = items.stream().collect(Collectors.groupingBy(Item::getName, Collectors.toList()));
        list11.forEach((k, v) -> {
            System.out.println("k = " + k);
            v.forEach(System.out::println);

        });

        Map<String, List<Integer>> mpssss =
                items.stream().collect(Collectors.groupingBy(Item::getName, Collectors.mapping(Item::getQty, Collectors.toList())));
        System.out.println("----------------" + mpssss);

        Map<String, Long> counting = items.stream().collect(Collectors.groupingBy(Item::getName, Collectors.counting()));
        // Map<String, Integer> sum = items.stream().collect(Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));
        Map<String, List<Integer>> map = items.stream()
                .collect(Collectors.groupingBy(Item::getName, Collectors.mapping(Item::getQty, Collectors.toList())));
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            System.out.println("entry = " + entry.getKey());
            System.out.println("entry.getValue() = " + entry.getValue());

        }

        System.out.println(sum);
    }

    class Item {
        private String name;
        private Integer qty;
        private BigDecimal b;

        public Item(String name, Integer qty, BigDecimal b) {
            this.qty = qty;
            this.name = name;
            this.b = b;
        }

        public BigDecimal getB() {
            return b;
        }

        public void setB(BigDecimal b) {
            this.b = b;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", qty=" + qty +
                    ", b=" + b +
                    '}';
        }
    }
}

