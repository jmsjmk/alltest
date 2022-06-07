package com.framework.spring.scan;

import org.junit.Test;
import org.springframework.util.AntPathMatcher;

import java.io.File;

/**
 *
 *
 * Created by jiamingku on 2019/11/26.
 */
public class SpringTestScanPackage {

    private static String s = "/jms/erp/web/**/controller/*/*.class";

    private static String path = "/jms/erp/web";

    public static void main(String[] args) {

        AntPathMatcher antPathMatcher = new AntPathMatcher();

//        File f = new File(path);
//
//        File[] dirContents = f.listFiles();
//
//        for (File content : dirContents) {
//            String currPath = StringUtils.replace(content.getAbsolutePath(), File.separator, "/");
//            System.out.println("currPath = " + currPath);
//            if (antPathMatcher.match(s, currPath + "/")) {
//                System.out.println("yes");
//            }
//        }

        boolean b = antPathMatcher.match(s, path + "/1222/controller" + File.separator + "1/a.class");
        System.out.println("b = " + b);


    }

    /**
     * *号匹配目录/ ** 不仅可以匹配目录还可以匹配当前目录下面的东西
     */
    @Test
    public void t() {
        String s = "/jms/erp/web/**/*.class";

        String path = "/jms/erp/web";
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String p = path + File.separator + "1/1/33.class";
        boolean b = antPathMatcher.match(s, p);
        System.out.println(b);
    }

    /**
     * 并且匹配的一级目录
     */
    @Test
    public void t1() {
        String s = "/jms/erp/web/*/*.class";

        String path = "/jms/erp/web";
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String p = path + File.separator + "1/33/33.class";
        boolean b = antPathMatcher.match(s, p);
        System.out.println(b);
    }
}
