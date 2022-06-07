package com.jiamingku.j2se.annotation.learn.Inherited;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Testable {
}
