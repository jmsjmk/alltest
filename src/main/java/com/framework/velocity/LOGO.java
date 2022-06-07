package com.framework.velocity;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LOGO {
    public static void main(String[] args) {

        String s = "<div class=\"item active\">\n" +
                "                <div class=\"row\">\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                    \n" +
                "                </div>\n" +
                "                <div class=\"row\">\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                </div>\n" +
                "                <div class=\"row\">\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                </div>\n" +
                "                <div class=\"row\">\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                </div>\n" +
                "                <div class=\"row\">\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/logo/about_us.jpg\"/>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"item\">\n" +
                "                <div class=\"row\">\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                </div>\n" +
                "                <div class=\"row\">\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                </div>\n" +
                "                <div class=\"row\">\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                </div>\n" +
                "                <div class=\"row\">\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                </div>\n" +
                "                <div class=\"row\">\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"item\">\n" +
                "                <div class=\"row\">\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                </div>\n" +
                "                <div class=\"row\">\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                </div>\n" +
                "                <div class=\"row\">\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                </div>\n" +
                "                <div class=\"row\">\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                </div>\n" +
                "                <div class=\"row\">\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                    <img src=\"../images/about_us.jpg\"/>\n" +
                "                </div>\n" +
                "            </div>";
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.setProperty(Velocity.ENCODING_DEFAULT, "utf-8");
        ve.setProperty(Velocity.INPUT_ENCODING, "utf-8");
        ve.setProperty(Velocity.OUTPUT_ENCODING, "utf-8");
        ve.init();

        Template t = ve.getTemplate("logo.vm");
        VelocityContext ctx = new VelocityContext();

        ctx.put("logo", s);


        StringWriter sw = new StringWriter();

        t.merge(ctx, sw);

        System.out.println(sw.toString());
    }
}
