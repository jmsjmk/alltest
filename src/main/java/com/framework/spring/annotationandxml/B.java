package com.framework.spring.annotationandxml;//package com.jiamingku.spring.annotationandxml;

import org.springframework.stereotype.Component;

@Component
public class B {
    public B() {
        System.out.println("创建 B: " + this);
    }


    public void a() {
        System.out.println("true = " + true);
    }
}