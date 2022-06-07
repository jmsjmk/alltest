package com.jiamingku.lambda.sgg.com.atguigu.java8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;


public class TestStreamAPI4 {

    List<SkuNumBo> skuNumBos = Arrays.asList(
            new SkuNumBo(1, 21),
            new SkuNumBo(1, 22),
            new SkuNumBo(1, 23),
            new SkuNumBo(1, 24),
            new SkuNumBo(1, 25)
    );

    //3. 终止操作
    /*
        归约
		reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
	 */
    @Test
    public void test1() {
        Map<Integer, Integer> map = skuNumBos.stream().
                collect(Collectors.toMap(SkuNumBo::getId, SkuNumBo::getAge, (v1, v2) -> {
            return v1 + v2;
        }));
        System.out.println("map = " + map);


        Map<Integer, Integer> map1 = skuNumBos.stream()
                .collect(Collectors.toMap(SkuNumBo::getId, SkuNumBo::getAge, (v1, v2) -> v1 + v2));
        System.out.println("map = " + map1);

        /**
         * 转换成为map时候-key重复，抛出异常
         * Map<Integer, Integer> map2 = emps.stream().collect(Collectors.toMap(SkuNumBo::getId, SkuNumBo::getAge));
         */
        Map<Integer, Integer> map2 = skuNumBos.stream().collect(Collectors.toMap(SkuNumBo::getId, SkuNumBo::getAge, (v1, v2) -> v2));
        System.out.println("map2 = " + map2);

        Map<Integer, Integer> map3 = skuNumBos.stream()
                .collect(Collectors.toMap(SkuNumBo::getId, SkuNumBo::getAge, (v1, v2) -> v1));
        System.out.println("map3 = " + map3);

        List<SkuNumBo> r = new ArrayList<>();
        map.forEach((k, v) -> {
            r.add(new SkuNumBo(k, v));
        });


        List<SkuNumBo> r1 = new ArrayList<>();
        map.forEach((k, v) -> r.add(new SkuNumBo(k, v)));


        // 最终优化
        List<SkuNumBo> r3 = new ArrayList<>();
        skuNumBos.stream().collect(Collectors.toMap(SkuNumBo::getId, SkuNumBo::getAge, (v1, v2) -> v1 + v2))
                .forEach((k, v) -> r3.add(new SkuNumBo(k, v)));
        System.out.println("r3 = " + r3);
        System.out.println("emps = " + skuNumBos);
    }

}
