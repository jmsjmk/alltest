package com.jiamingku.fastjson.test.learn;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

@SuppressWarnings("all")
public class FastJsonTestInteger {

    /**
     * ================在转换成为jsonObject时候是有-类型相对来说宽泛-也就是什么都能存放进来
     * <p>
     * "weight:12.38 ---- 这样的获取Integer是没问题的，11111111111111111111111转换int没问题，只是截短了
     * <p>
     * weight:"12.38"----这样的获取Integer是有问题的，同样转换jsonObject之后在getInteger，也是有问题的
     * <p>
     * {@link com.jiamingku.fastjson.test.learn.annotion.User#main(java.lang.String[])}
     */
    @Test
    public void a() {
        String a = "{\"expressNumber\":9999009090,\"expressId\":132,\"weight\":12.38,\"sendTime\":\"2018-05-12\"}";
        String a1 = "{\"expressNumber\":9999009090,\"expressId\":132,\"weight\":\"12\",\"sendTime\":\"2018-05-12\"}";
        String a3 = "{\"expressNumber\":9999009090,\"expressId\":132,\"weight\":\"12.23\",\"sendTime\":\"2018-05-12\"}";
        JSONObject jsonObject = JSONObject.parseObject(a1);
        try {
            Integer i = jsonObject.getInteger("weight");
            System.out.println(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * 如果用对象封装的话-,这个是没问题的--但是会被截段
         */
        try {
            double d = jsonObject.getDouble("weight");
            System.out.println("d = " + d);

            JSONObject jjj = JSONObject.parseObject(a);
            double dd = jjj.getDouble("weight");
            System.out.println("dd = " + dd);

            Integer i = jjj.getInteger("weight");
            System.out.println("i = " + i);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(" =========================== ");
        String aaaaa = jsonObject.getString("weight");
        System.out.println("aaaaa = " + aaaaa);
        /**
         * 转换jsonObject ---- jsonObject.get("expressNumber"); 是bigInteger
         * 转换jsonObject ---- jsonObject.getInteger("expressNumber"); 会给截短
         */
        // {@see com.jiamingku.fastjson.test.learn.annotion.User#main(java.lang.String[])}
        Integer j = jsonObject.getInteger("expressNumber");
        System.out.println("j = " + j);
        Object v = jsonObject.get("expressNumber");
        System.out.println("v = " + v);

        // ----------------------------问题来了

        JSONObject jsonObject1 = JSONObject.parseObject(a3);

        // Integer i = jsonObject1.getInteger("weight"); error
        Double ij = jsonObject1.getDouble("weight");
        System.out.println("i = " + ij);
    }
}
