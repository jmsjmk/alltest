package com.jiamingku.j2se.annotation.learn.Inherited;

import java.lang.annotation.*;

/**
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Inheritable {
}