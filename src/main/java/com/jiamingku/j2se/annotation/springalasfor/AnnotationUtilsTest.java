package com.jiamingku.j2se.annotation.springalasfor;

import org.junit.Test;
import org.springframework.core.annotation.AnnotationUtils;

@MyAnnotation(value = "aa")
public class AnnotationUtilsTest {
 
    @Test
    public void testAliasfor3() {
        Object o ;
        MyAnnotation ann = AnnotationUtils.findAnnotation(getClass(), MyAnnotation.class);
        System.out.println(ann.value());
        System.out.println(ann.alias());
    }
}
