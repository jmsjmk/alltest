package com.middleserver.binlog;

import java.text.SimpleDateFormat;
import java.util.*;

public class Test11 {

    private static Map<String, List<Object>> shardingAliquot1(final List<String> shardingUnits, final List<Object> allBinlogs) {
        Map<String, List<Object>> result = new LinkedHashMap<>(allBinlogs.size(), 1);
        int itemCountPerSharding = allBinlogs.size() / shardingUnits.size();
        System.out.println("itemCountPerSharding = " + itemCountPerSharding);
        int count = 0;
        for (String each : shardingUnits) {
            List<Object> shardingItems = new ArrayList<>(itemCountPerSharding + 1);
            for (int i = count * itemCountPerSharding; i < (count + 1) * itemCountPerSharding; i++) {
                System.out.println(" = ");
                shardingItems.add(allBinlogs.get(i));
            }
            result.put(each, shardingItems);
            count++;
        }
        return result;
    }


    public static void main(String[] args) throws Exception {
        Date d = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String s1 = simpleDateFormat.format(d);
        System.out.println("s1 = " + s1);

        // 2021-10-27 14:35:21

        Date d1 = simpleDateFormat.parse("2021-10-30 14:39:21");

        System.out.println("d1.getTime() = " + d1.getTime());


    }
}
