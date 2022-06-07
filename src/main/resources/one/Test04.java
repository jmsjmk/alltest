package com.jiamingku.lambda.one;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

import java.util.HashSet;
import java.util.Set;

/**
 * 集合的操作：交集、差集、并集
 * Sets.intersection()交集
 * Sets.difference()差集
 * Sets.union()并集
 */
public class Test04 {

    public static void main(String[] args) {
        Set<Integer> sets = Sets.newHashSet(1, 2, 3, 4, 5, 6);
        Set<Integer> sets1 = Sets.newHashSet(1, 2, 3, 4, 5, 6);




        Set<Integer> sets2 = Sets.newHashSet(3, 4, 5, 6, 7, 8, 9);
        // 交集
        System.out.println("交集为：");
        SetView<Integer> intersection = Sets.intersection(sets2, sets);
        for (Integer temp : intersection) {
            System.out.println(temp);
        }
        // 差集
        System.out.println("差集为：");
        SetView<Integer> diff = Sets.difference(sets2, sets);
        for (Integer temp : diff) {
            System.out.println(temp);
        }
        // 并集
        System.out.println("并集为：");
        SetView<Integer> union = Sets.union(sets, sets2);
        for (Integer temp : union) {
            System.out.println(temp);
        }


        System.out.println("====================");

        System.out.println("sets = " + sets);
        Set<Integer> set111 = new HashSet<>();
        set111.add(3331);
        Set<Integer> set222 = new HashSet<>();
        set222.add(333);


        Set<Integer> s = Sets.newHashSet(set111);
        Set<Integer> s2 = Sets.newHashSet(set222);

        // 交集
        System.out.println("交集为：");
        SetView<Integer> intersection1 = Sets.intersection(set222, set111);
        for (Integer temp : intersection1) {
            System.out.println(temp);
            System.out.println("====\" = ====");
        }
        // 差集
        System.out.println("差集为：");
        SetView<Integer> diff1 = Sets.difference(set222, set111);
        for (Integer temp : diff1) {
            System.out.println(temp);
        }
        // 并集
        System.out.println("并集为：");
        SetView<Integer> union1 = Sets.union(set222, set111);
        for (Integer temp : union1) {
            System.out.println(temp);
        }
    }

}
