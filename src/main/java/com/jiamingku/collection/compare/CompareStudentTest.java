
package com.jiamingku.collection.compare;


import org.junit.Test;

import java.util.*;

@SuppressWarnings("all")
public class CompareStudentTest implements Comparable<CompareStudentTest> {
    public String name;
    public String old;
    public int age;

    public CompareStudentTest(int age, String name, String old) {
        this.age = age;
        this.name = name;
        this.old = old;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(CompareStudentTest o) {
        /**
         @Override public int compareTo(TTT o) {
         /**
          * 相当于 这个对象比 其他对象数值小 还返回了1 所以降序，
         */
        return o.age > this.getAge() ? 1 : (o.age == this.getAge() ? 0 : -1);
        /**
         * 在进行比较的时候这个比较对象与this很关键,自然排序的关键，在于当前对象
         *
         * this对象比另外对象大 =1 一定是升序
         *
         * this对象比另外对象大 = -1一定是降序

         // return this.age > o.getAge() ? 1 : (o.age == this.getAge() ? 0: -1);
         }
         */
    }

    @Override
    public String toString() {
        return "Student [age=" + age + "]";
    }


    public static void main(String[] args) {
        List<CompareStudentTest> lists = new ArrayList<>();
        CompareStudentTest s1 = new CompareStudentTest(111, "1", "1");
        CompareStudentTest s2 = new CompareStudentTest(121, "2", "2");
        CompareStudentTest s3 = new CompareStudentTest(123421, "3", "3");
        CompareStudentTest s4 = new CompareStudentTest(113331, "4", "4");

        lists.add(s2);
        lists.add(s3);
        lists.add(s4);
        lists.add(s1);
        Collections.sort(lists);
        System.out.println("lists = " + lists);
    }


    /**
     * Collections.reverseOrder() 只是将比较的对象调转过来就行了
     * {@link BaseUseCompare#demo2()}
     */
    @Test
    @SuppressWarnings("all")
    public void demo1() {
        Integer[] numbers = {1, 8, 2, 3, 5};
        // 转换成List,再按入参提供的降序排列器，降序排列
        Collections.sort(Arrays.asList(numbers), Collections.reverseOrder());
        p(numbers);
        System.out.println("======================= ");
        Collections.sort(Arrays.asList(numbers));
        p(numbers);
    }


    @Test
    @SuppressWarnings("all")
    public void demo2() {
        Integer[] numbers = {1, 8, 2, 3, 5};
        // 转换成List,再按入参提供的降序排列器，降序排列
        Collections.sort(Arrays.asList(numbers), new Comparator<Integer>() {
            /**
             * 第一个参数是第一个元素
             * 第二个参数是第二个元素
             *
             * 第一个元素与第二个元素的比较，如果大于0 就在后面，你对应的调转下就是降序
             *
             * @param o1 第一个元素
             * @param o2 第二个元素
             * @return
             */
            @Override
            public int compare(Integer o1, Integer o2) {
                return -(o1.compareTo(o2));
            }
        });
        p(numbers);
        System.out.println("======================= ");
        Collections.sort(Arrays.asList(numbers));
        p(numbers);
    }

    @Test
    @SuppressWarnings("all")
    public void demo3() {
        Integer[] numbers = {1, 8, 2, 3, 5};
        // 转换成List,再按入参提供的降序排列器，降序排列
        Collections.sort(Arrays.asList(numbers), new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        p(numbers);
    }

    public static void p(Integer[] integers) {
        for (Integer i : integers) {
            System.out.println("i = " + i);
        }
    }
}
