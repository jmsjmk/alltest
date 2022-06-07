package com.jiamingku.collection.guava;

import com.google.common.collect.Lists;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Guava has a nice index method, that returns a Multimap indexed by the expression.
 * <p>
 * https://stackoverflow.com/questions/13625050/grouping-objects-in-a-map
 * ListMultimap<Integer, Person> multimap = Multimaps.index(personList, new Function<Person, Integer>(){
 * public Integer apply(Person source){
 * return source.getCountryId();
 * }});
 * <p>
 * <p>
 * 这个问题的产生 是是下面的这个
 * <p>
 * public static Map<Long, List<SplitactionLog>> groupByUuid(List<SplitactionLog> splitactionLogs) {
 * Map<Long, List<SplitactionLog>> splitactionLogInfoMap = new HashMap<>();
 * if (CollectionUtils.isEmpty(splitactionLogs)) {
 * return splitactionLogInfoMap;
 * }
 * Long apportionDiscountInfoId;
 * List<SplitactionLog> list;
 * for (SplitactionLog splitactionLog : splitactionLogs) {
 * apportionDiscountInfoId = splitactionLog.getApportionDiscountInfoId();
 * list = splitactionLogInfoMap.get(apportionDiscountInfoId);
 * if (CollectionUtils.isEmpty(list)) {
 * list = new ArrayList<>();
 * splitactionLogInfoMap.put(apportionDiscountInfoId, list);
 * }
 * list.add(splitactionLog);
 * }
 * return splitactionLogInfoMap;
 * }
 */

/**
 * Created by jiamingku on 2018/12/12.
 */
public class Test {

    public static void main(String[] args) {
        final List<String> CONTEXTS = Lists.newArrayList("/", "/erp-gateway-web", "/erp-gateway-web/");

        if (CONTEXTS.contains("/erp-gateway-web/")) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
