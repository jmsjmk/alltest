package com.framework.spring.configannotation;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomizeComponent {
     String value() default "";
}