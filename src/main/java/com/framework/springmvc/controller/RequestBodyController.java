package com.framework.springmvc.controller;

import com.framework.springmvc.bo.User;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jiamingku on 2020/4/24.
 */
@Controller
@RequestMapping("/req")
@Api(value = "看看效果333", description = "车辆保险相关接口", position = 1)
public class RequestBodyController {


    @Autowired
//	@Qualifier("aaa")
            AnnotationController2 annotationController2;


    @RequestMapping("/testMapping1")
    public void test(@RequestBody User user, @RequestHeader("tt") String tt) {
        System.out.println("true = " + true);


        System.out.println("user = " + user);

        System.out.println("tt = " + tt);
        System.out.println("tt = " + tt);
        System.out.println("tt = " + tt);
        System.out.println("tt = " + tt);

    }


    @RequestMapping("/testMapping")
    public void test(@RequestBody User user) {
        System.out.println("true = " + true);


        System.out.println("user = " + user);

    }


    @RequestMapping("/testMapping2")
    public void test(HttpEntity<String> entity) {
        System.out.println("true = " + true);


        System.out.println("user = " + entity);

        org.springframework.http.HttpHeaders httpHeaders = entity.getHeaders();

        String value = httpHeaders.get("tt").toString();
        System.out.println("value = " + value);


        String s = entity.getBody();
        System.out.println("s = " + s);
    }
}
