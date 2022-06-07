package com.framework.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;

public class logoo {
    public static void main(String[] args) {
        int a  = (120/30) + 1 ;
        System.out.println("a = " + a);

        int b =  100 %20;
        System.out.println("b = " + b);

        System.out.println(6%6);
    }
}
