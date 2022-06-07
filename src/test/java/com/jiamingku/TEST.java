package com.jiamingku;

import org.springframework.core.io.UrlResource;
import org.springframework.util.ResourceUtils;

import java.net.MalformedURLException;
import java.net.URL;

public interface TEST {

    static void t() {
        System.out.println("true = " + true);
    }

     static  void  ddd() {
        System.out.println("true = " + true);
    }

    default void  a() {
        System.out.println("true = " + true);
    }


    public static void main(String[] args) throws MalformedURLException {


        String location = "classpath:/bac/sdfsdf/sdfsdf/*/ad//";

        int  end = location.length();
        System.out.println("end = " + end);

        int end1 = location.lastIndexOf('/', end - 2) + 1;
        System.out.println("end1 = " + end1);

        char c = location.charAt(33);
        System.out.println("c = " + c);

    }

    public static  String determineRootDir(String location) {
//        int prefixEnd = location.indexOf(':') + 1;
//        int rootDirEnd = location.length();
//        while (rootDirEnd > prefixEnd && getPathMatcher().isPattern(location.substring(prefixEnd, rootDirEnd))) {
//            rootDirEnd = location.lastIndexOf('/', rootDirEnd - 2) + 1;
//        }
//        if (rootDirEnd == 0) {
//            rootDirEnd = prefixEnd;
//        }
//        return location.substring(0, rootDirEnd);
        return null;
    }
}
