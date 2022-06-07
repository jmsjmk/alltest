package com.jiamingku.lambda.sgg.com.atguigu.java8;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/*
 * 一、Optional 容器类：用于尽量避免空指针异常
 * 	Optional.of(T t) : 创建一个 Optional 实例,如果null就抛出异常
 * 	Optional.empty() : 创建一个空的 Optional 实例
 * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
 * 	isPresent() : 判断是否包含值
 * 	orElseThrow()
 * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
 * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
 * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
 */

/**
 * 代码更加简洁,习惯就很简单了
 * <p>
 * 1.Optional.ofNullAble(a).orElse(b)
 * result = if(null) {
 * b
 * } else {
 * a
 * }
 * <p>
 * <p>
 * 2.Optional.ofNullAble(a).orElseThrow()
 * result = if (null) {
 * throw exception()
 * } else {
 * a;
 * }
 * <p>
 * 3.Optional.ofNullable(a).isPresent()
 * <p>
 * if (!=null) {
 * 执行个功能---这个样的代码在java中太多了
 * }
 * <p>
 * 4.Option.ofNullable(a).orElseGet() {
 * supplier
 * }
 * <p>
 * if (null) {
 * 执行一个供给行为接口
 * } else {
 * a
 * }
 * <p>
 * 5.Optional.ofNullable(a).map().orElse(b);
 * if (a!=null) {
 * map =>返回新的optional
 * if (!=null) {
 * 返回值
 * } else {
 * b
 * }
 * } else {
 * b
 * }
 * <p>
 * https://mp.weixin.qq.com/s/g3QLHYP946wEPTaKogjAzA
 * <p>
 * == 常用的判断也行
 * Optional.ofNullable(carInsuranceQuery.getInsuranceUserName()).filter(StringUtils::isNoneBlank).ifPresent(s -> carInsuranceQuery.setInsuranceUserName("%" + s + "%"));
 * Optional.ofNullable(carInsuranceQuery.getInsuranceCompanyName()).filter(StringUtils::isNoneBlank).ifPresent(s -> carInsuranceQuery.setInsuranceCompanyName("%" + s + "%"));
 */
@SuppressWarnings("all")
public class TestOptional {

    @Test
    public void test53() {
        Employee e1 = new Employee(101, "9999", 18, 9999.99);
        Optional.empty();
//        e1.setName(null);
        String account = Optional.ofNullable(e1).map(Employee::getName)
                .filter(StringUtils::isNoneEmpty)
                .filter(e2 -> e2.length() < 5)
                .orElseThrow(() -> new NullPointerException("333"));

        Optional.ofNullable(e1).map(Employee::getName).filter(StringUtils::isNoneEmpty).filter(e2 -> e2.length() < 5).orElseThrow(NullPointerException::new);
//        String name = Optional.ofNullable(e1.getName()).map(String::toUpperCase).orElse("");
    }


    @Test
    public void test4() {
        Employee e1 = new Employee(101, "张三", 18, 9999.99);
//        Double d1 = Optional.ofNullable(e1).map(Employee::getSalary).orElseGet(() -> 1.0);
//        System.out.println("d1 = " + d1);
//        System.out.println(" ================================================== ");
//
        Optional<Employee> op = Optional.of(e1);
//        //
//        e1.setName(null);
//        Optional<String> op2 = op.map(Employee::getName);
//        System.out.println(op2.orElse("1000"));

        Optional<String> op3 = op.flatMap((e) -> Optional.ofNullable(e.getName()));
        System.out.println(op3.orElse("1000000"));

        e1.setName(null);
        Optional<String> op4 = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(op4.get());
    }

    @Test
    public void test3() {
        Optional<Employee> op = Optional.ofNullable(new Employee());

        if (op.isPresent()) {
            System.out.println(op.get());
        }


        Optional<Employee> op1 = Optional.ofNullable(null);

        Employee a = null;
        Employee op111 = Optional.ofNullable(a).orElse(new Employee());

        if (op1.isPresent()) {
            System.out.println("op1.get() = " + op1.get());
        }

        Optional<Employee> op11 = Optional.empty();
        if (op11.isPresent()) {
            System.out.println("op1.get() = " + op1.get());
        }


        Employee emp = op.orElse(new Employee("张三"));
        System.out.println(emp);

//		Object emp2 = op.orElseGet(() -> new Object());
//		System.out.println(emp2);
//
//		System.out.println(" ============================ " );
    }


    public static void main(String[] args) {
//        List<Integer> carModelIdList = new ArrayList<>();
//        String carModelId1s = "1,2,3";
//        Optional.ofNullable(carModelId1s).ifPresent(carModelIds -> {
//            carModelIdList.addAll(Stream.of(carModelIds.split(",")).filter(StringUtils::isNotBlank).map(Integer::valueOf).collect(Collectors.toList()));
//        });
//
//         carModelIdList = CollectionUtils.isEmpty(carModelIdList)? null: carModelIdList;
//
//        System.out.println("carModelIdList  = " + carModelIdList );
    }


    @Test
    public void test2() {
        // 空不会发出异常信息
        Optional<Employee> op = Optional.ofNullable(null);
        // 但是你get时候会发出异常
//		 System.out.println(op.get());

        Optional<Employee> op1 = Optional.empty();
        // 抛出异常
        System.out.println(op1.get());
    }

    @Test
    public void test1() {
        Employee e1 = new Employee(102, "李四", 79, 6666.66, Employee.Status.BUSY);
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("1");
        hashSet.add("12");
        hashSet.add("2");
        e1.setSuplierIds(hashSet);
        String s = Optional.ofNullable(e1).map(Employee::getSuplierIds).orElse(new HashSet<>()).stream()
                .collect(Collectors.joining(","));
        System.out.println("s = " + s);
    }

    @Test
    public void test5() {
        Man man = new Man();
        String name = getGodnessName(man);
        System.out.println(name);
    }

    //需求：获取一个男人心中女神的名字
    public String getGodnessName(Man man) {
        if (man != null) {
            Godness g = man.getGod();

            if (g != null) {
                return g.getName();
            }
        }

        return "苍老师";
    }

    //运用 Optional 的实体类
    @Test
    public void test6() {
        Optional<Godness> godness = Optional.ofNullable(new Godness("林志玲"));

        Optional<NewMan> op = Optional.ofNullable(new NewMan(godness));
        String name = getGodnessName2(op);
        System.out.println(name);
    }

    public String getGodnessName2(Optional<NewMan> man) {
        return man.orElse(new NewMan())
                .getGodness()
                .orElse(new Godness("苍老师"))
                .getName();
    }

    // -----------------------------------------------------------------------
    // -----------------------------------------------------------------------
    // -----------------------------------------------------------------------
    // -----------------------------------------------------------------------
    // -----------------------------------------------------------------------
    // -----------------------------------------------------------------------
    // -----------------------------------------------------------------------
    // -----------------------------------------------------------------------
    // -----------------------------------------------------------------------
    // -----------------------------------------------------------------------
    // -----------------------------------------------------------------------
    // -----------------------------------------------------------------------
    // -----------------------------------------------------------------------
    // -----------------------------------------------------------------------
    // -----------------------------------------------------------------------
    // -----------------------------------------------------------------------
    // -----------------------------------------------------------------------
    // -----------------------------------------------------------------------
    // -----------------------------------------------------------------------
    // -----------------------------------------------------------------------
    @Test
    public void ttt() throws Exception {
        String a = null;
//        Optional.ofNullable(a).orElseThrow(NullPointerException::new);
//
//        Optional.ofNullable(a).orElseThrow(t(100,"dd"));

        Optional.ofNullable(a).orElseThrow(t(100, "220", ttte::new));

        Optional.ofNullable(a).orElseThrow(t(100, "220", ttte::new));

        Optional.ofNullable(a).orElseThrow(ExceptionTools.t(100, 220 + "", ttte::new));
    }

    public void ddd() {
        String a = null;
        Optional.ofNullable(a).orElseThrow(NullPointerException::new);
    }

    public Supplier<Exception> t(Integer code, String value) {
        return () -> new NullPointerException(code + value);
    }

    public Supplier<Exception> t(Integer code, String value, BiFunction<Integer, String, Exception> biFunction) {
        return () -> biFunction.apply(code, value);
    }

    public Supplier<Exception> t1(Integer code, String value) {
        BiFunction<Integer, String, Exception> biFunction = ttte::new;
        return () -> biFunction.apply(code, value);
    }

    class ttte extends RuntimeException {
        private Integer code;

        public ttte(String message, Throwable cause, Integer code, String value) {
            super(message, cause);
            this.code = code;
        }

        public ttte(Integer code, String message) {
            super(message);
            this.code = code;
        }
    }
}
