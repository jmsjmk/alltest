package com.jiamingku.md5;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
//@Builder
public class RechargeContext   {

    private static final long serialVersionUID = -1157914978203831141L;


    /**
     * 订单
     */
    private String orderModel="aaa";

    private BigDecimal mjAmount = new BigDecimal("0");

//    public static void main(String[] args) {
//        RechargeContext build = RechargeContext.builder().build();
//        System.out.println("build = " + build);
//    }
}