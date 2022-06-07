package com.framework.spring.annotationandxml;//package com.jiamingku.spring.annotationandxml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A {
    private B bbb;
    private C ccc;

    public A() {
        System.out.println(" 创建 A: " + this);
    }

    @Autowired
    public void setBbb(B bbb) {
        System.out.println("设置 A.bbb 属性: " + bbb);
        this.bbb = bbb;
    }

    @Autowired
    public void setCcc(C ccc) {
        System.out.println("设置 A.ccc 熟悉过: " + ccc);
        this.ccc = ccc;
    }
}