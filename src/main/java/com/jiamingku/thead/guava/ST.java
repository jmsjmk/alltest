package com.jiamingku.thead.guava;

import java.io.File;

public class ST {
    public static void main(String[] args) {
        String s = String.class.getName();
        System.out.println(s);

        File file = new File("/web/inf/a");
        String filePath = file.getPath();
        System.out.println(filePath);
        int jarFile = filePath.lastIndexOf("!/");

        System.out.println(jarFile);
    }
}
