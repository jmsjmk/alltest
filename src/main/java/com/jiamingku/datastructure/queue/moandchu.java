package com.jiamingku.datastructure.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiamingku on 2017/5/3.
 */
public class moandchu {

    public static void main(String[] args) {
        int a = 109;
        int b = 238;

        // 就是求余数
        int aa = a % 100;
        // 就是求商
        int bb = a / 100;
        System.out.println(aa);
        System.out.println(bb);

        int cc = 100 %100;
        System.out.println(cc);
        System.out.println("====");

        List list = new ArrayList(10);

        System.out.println(list.size());

        System.out.println(list.isEmpty());
        int coreNum = Runtime.getRuntime().availableProcessors();
        System.out.println(coreNum);


    }
}
