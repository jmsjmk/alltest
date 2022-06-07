package com.framework.spring.annotationandxml;

import org.springframework.stereotype.Component;

@Component
public class C {
    public C() {
        System.out.println("设置 C: " + this);
    }
}  