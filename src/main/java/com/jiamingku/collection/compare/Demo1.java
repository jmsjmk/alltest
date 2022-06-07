package com.jiamingku.collection.compare;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiamingku on 2018/12/26.
 * <p>
 * 排序泛型的使用方法,代码的逻辑抽象非常关键，对业务产生高度的抽象是一个能力。
 */
public class Demo1 {
    public static class SortObject {
        // 元素在list中的index越小，优先级越高
        public static List<String> RANGE_PRIORITY = new ArrayList<>();
        public static List<String> TYPE_PRIORITY = new ArrayList<>();
        public static List<String> ACTIONTYPE_PRIORITY = new ArrayList<>();

        static {
            RANGE_PRIORITY.add(PromotionConstants.PROMOTION_RANGE_ALL);
            RANGE_PRIORITY.add(PromotionConstants.PROMOTION_RANGE_BRAND);
            RANGE_PRIORITY.add(PromotionConstants.PROMOTION_RANGE_SKU);

            TYPE_PRIORITY.add(PromotionConstants.PROMOTION_TYPE_MONEY);
            TYPE_PRIORITY.add(PromotionConstants.PROMOTION_TYPE_PRESENT);
            TYPE_PRIORITY.add(PromotionConstants.PROMOTION_TYPE_DISCOUNT);

//            ACTIONTYPE_PRIORITY.add(PromotionConstants.PROMOTION_ACTION_TYPE_FULLMNTMNS);
            ACTIONTYPE_PRIORITY.add(PromotionConstants.PROMOTION_ACTION_TYPE_BUYPRESENT);
            ACTIONTYPE_PRIORITY.add(PromotionConstants.PROMOTION_ACTION_TYPE_DISCOUNT4ALL);
            ACTIONTYPE_PRIORITY.add(PromotionConstants.PROMOTION_ACTION_TYPE_DISCOUNT4ONE);
        }

        public static int compareActionTypePriority(String range1, String range2) {
            return comparePriority(range1, range2, ACTIONTYPE_PRIORITY);
        }

        /**
         * comparePriority:(元素在list中的index越小，优先级越高). <br/>
         * TODO(这里描述这个方法适用条件 – 可选).<br/>
         *
         * @param v1
         * @param v2
         * @param sortMeta
         * @return
         * @author jiamingku
         * @since JDK 1.6
         */
        public static <T> int comparePriority(T v1, T v2, List<T> sortMeta) {
            int i1 = sortMeta.indexOf(v1);
            int i2 = sortMeta.indexOf(v2);
            i1 = i1 == -1 ? 100 : i1;
            i2 = i2 == -1 ? 100 : i2;
            if (i1 == i2)
                return 0;
            return i1 < i2 ? 1 : -1;
        }

        public static int compareRangePriority(String range1, String range2) {
            return comparePriority(range1, range2, RANGE_PRIORITY);
        }

        public static int compareTypePriority(String range1, String range2) {
            return comparePriority(range1, range2, TYPE_PRIORITY);
        }
    }
}

class PromotionConstants {
    public static String PROMOTION_RANGE_ALL = "";
    public static String PROMOTION_RANGE_BRAND = "";
    public static String PROMOTION_RANGE_SKU = "";
    public static String PROMOTION_TYPE_MONEY = "";
    public static String PROMOTION_TYPE_PRESENT = "";
    public static String PROMOTION_TYPE_DISCOUNT = "";
    public static String PROMOTION_ACTION_TYPE_BUYPRESENT = "";
    public static String PROMOTION_ACTION_TYPE_DISCOUNT4ALL = "";
    public static String PROMOTION_ACTION_TYPE_DISCOUNT4ONE = "";
}
