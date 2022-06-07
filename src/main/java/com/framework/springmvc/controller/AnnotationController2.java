package com.framework.springmvc.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jiamingku on 2020/4/24.
 */
@Controller
@RequestMapping("/web2")
@Api(value = "看看效果222", description = "车辆保险相关接口", position = 1)
public class AnnotationController2 {

    @RequestMapping("/t2estMapping")
    public void test() {
        System.out.println("true eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee= " + true);

        throw new NullPointerException("测试");

//        List<String> list = new ArrayList<>();
    }

    @RequestMapping("/t")
    public void test2(Bean bean) {
        System.out.println("true eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee= " + true);

        System.out.println("bean = " + bean);
        throw new NullPointerException("测试");

//        List<String> list = new ArrayList<>();
    }


}
