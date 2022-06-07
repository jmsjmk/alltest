package com.jiamingku;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by jiamingku on 2019/11/5.
 */
public class AAA {


    static {
        a = 100;
    }

    static int a = 10000;


    public static void main(String[] args) {
        String property = System.getProperty("java.class.path");
        System.out.println("property = " + property);


        File[] classPath = getClassPath(property);
        Arrays.stream(classPath).sequential().forEach(System.out::println);

    }

//    =================

//}

    private static File[] getClassPath(String var0) {
        File[] var1;
        if (var0 != null) {
            int var2 = 0;
            int var3 = 1;
            boolean var4 = false;

            int var5;
            int var7;
            for(var5 = 0; (var7 = var0.indexOf(File.pathSeparator, var5)) != -1; var5 = var7 + 1) {
                ++var3;
            }

            var1 = new File[var3];
            var4 = false;

            for(var5 = 0; (var7 = var0.indexOf(File.pathSeparator, var5)) != -1; var5 = var7 + 1) {
                if (var7 - var5 > 0) {
                    var1[var2++] = new File(var0.substring(var5, var7));
                } else {
                    var1[var2++] = new File(".");
                }
            }

            if (var5 < var0.length()) {
                var1[var2++] = new File(var0.substring(var5));
            } else {
                var1[var2++] = new File(".");
            }

            if (var2 != var3) {
                File[] var6 = new File[var2];
                System.arraycopy(var1, 0, var6, 0, var2);
                var1 = var6;
            }
        } else {
            var1 = new File[0];
        }

        return var1;
    }
}
