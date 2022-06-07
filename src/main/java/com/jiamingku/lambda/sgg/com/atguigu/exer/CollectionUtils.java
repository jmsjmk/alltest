package com.jiamingku.lambda.sgg.com.atguigu.exer;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://blog.csdn.net/fzy629442466/article/details/84765070 跟个分组
 *
 */
public class CollectionUtils {

    public static <T> List<List<T>> divide(List<T>origin , int size){
        if(null == origin){
            return Collections.emptyList();
        }

        int block = (origin.size() + size -1) / size;
        return IntStream.range(0,block).
                boxed().map(i->{
                    int start = i*size;
                    int end = Math.min(start + size,origin.size());
                    return origin.subList(start,end);
        }).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(divide(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 3));
    }
}