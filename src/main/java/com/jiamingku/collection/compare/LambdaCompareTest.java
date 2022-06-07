package com.jiamingku.collection.compare;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * https://blog.csdn.net/albg_boy/article/details/76293838
 * ================java8的简化写法
 * https://blog.csdn.net/albg_boy/article/details/76293838 ——这个是重点===业务的抽象能力也很重要
 *
 *
 * <p>
 * Created by jiamingku on 2019/1/26.
 */
public class LambdaCompareTest {


    public static List<CompareStudentTest> getTestData() {
        List<CompareStudentTest> compareStudentTestList = new ArrayList<>();
        CompareStudentTest compareStudentTest1 = new CompareStudentTest(108, "s1", "100");
        CompareStudentTest compareStudentTest2 = new CompareStudentTest(102, "s2", "100");
        CompareStudentTest compareStudentTest3 = new CompareStudentTest(103, "s3", "100");
        CompareStudentTest compareStudentTest4 = new CompareStudentTest(103, "s4", "100");
        CompareStudentTest compareStudentTest5 = new CompareStudentTest(104, "s5", "100");

        compareStudentTestList.add(compareStudentTest1);
        compareStudentTestList.add(compareStudentTest2);
        compareStudentTestList.add(compareStudentTest3);
        compareStudentTestList.add(compareStudentTest4);
        compareStudentTestList.add(compareStudentTest5);
        return compareStudentTestList;
    }

    public static void main(String[] args) {

        List<CompareStudentTest> compareStudentTestList = getTestData();

        // 还有更简单的方法-- 看下面
        System.out.println("student5 = " + compareStudentTestList);
        compareStudentTestList.sort(
                ((Comparator<CompareStudentTest>) ((s1, s2) -> s1.getAge() - s2.getAge())).
                        thenComparing((s1, s2) -> s1.getName().compareTo(s2.getName()))
        );
        System.out.println("student5 = " + compareStudentTestList);
        compareStudentTestList = getTestData();
        Collections.sort(compareStudentTestList, Comparator.comparing(CompareStudentTest::getAge).thenComparing(CompareStudentTest::getName));
        System.out.println("studentList = " + compareStudentTestList);
        Collections.sort(compareStudentTestList, Comparator.comparing(CompareStudentTest::getAge).thenComparing(CompareStudentTest::getName).reversed());
        System.out.println("studentList = " + compareStudentTestList);
    }

    @Test
    public void test1() {
        List<CompareStudentTest> compareStudentTests = getTestData();
        Collections.sort(compareStudentTests, (x1, y) -> x1.getAge() - y.getAge());
        compareStudentTests.forEach(System.out::println);
    }

    @Test
    public void test2() {
        List<CompareStudentTest> compareStudentTests = getTestData();
        Collections.sort(compareStudentTests, CompareStudentTest::compareTo);
        compareStudentTests.forEach(System.out::println);
        Collections.sort(compareStudentTests, (x, y) -> x.compareTo(y));
    }

    @Test
    public void test3() {
        List<CompareStudentTest> compareStudentTests = getTestData();
        Collections.sort(compareStudentTests, Comparator.comparing(compareStudentTest -> compareStudentTest.getAge()));
        Collections.sort(compareStudentTests, Comparator.comparing(CompareStudentTest::getAge));
    }
}
