package com.framework.spring.aop.aop7;

public class SleepHelperAspect{
    public void beforeSleep(){
        System.out.println("睡觉前要脱衣服！");
    }

    public void afterSleep(){
        System.out.println("起床后要穿衣服！");
    }
}
