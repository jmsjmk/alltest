package com.jiamingku.collection.compare;

import java.io.Serializable;
import java.util.Comparator;

class OrderItemVo {
}

/**
 * 当有了lambda表达式之后--很多东西都变的那么自然，而且简单
 * <p>
 * 按参与多品分摊的价格排序(多品促销前的价格)
 * Created by weiguangjian on 2017/6/13.
 */
public class OrderItemComparator implements Comparator<OrderItemVo>, Serializable {

    private static final long serialVersionUID = -2876921354070401900L;
    private static final String COMPARE_CALCULATE_PRICE = "calculatePrice";
    private static final String AFTER_PROMO_APPORTIONED = "afPromoApportioned";

    //排序字段
    private String compareField;

    private OrderItemComparator(String compareField) {
        this.compareField = compareField;
    }


    @Override
    public int compare(OrderItemVo o1, OrderItemVo o2) {
//        if(COMPARE_CALCULATE_PRICE.equals(compareField)){
//            return  BigDecimalUtils.compare( o1.getCalculatePrice(), o2.getCalculatePrice());
//        }else if(AFTER_PROMO_APPORTIONED.equals(compareField)){
//            return  BigDecimalUtils.compare(o1.getAfPromoAppritiond(),o2.getAfPromoAppritiond());
//        }
        return 0;
    }


    //按参与多品分摊的价格排序(多品促销前的价格)
    public static OrderItemComparator getCalculatePriceComparator() {
        return new OrderItemComparator(COMPARE_CALCULATE_PRICE);
    }

    public static OrderItemComparator getAfPromoApportionedComparator() {
        return new OrderItemComparator(AFTER_PROMO_APPORTIONED);
    }
}

