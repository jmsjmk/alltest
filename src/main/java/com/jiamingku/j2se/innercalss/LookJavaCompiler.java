package com.jiamingku.j2se.innercalss;

import com.jiamingku.j2se.innercalss.instance.Interface;

/**
 * Created by jiamingku on 2020/8/25.
 */
public class LookJavaCompiler {

    static class staticClassMethodOut {

    }

    private static Interface anInterface = new Interface() {
        @Override
        public void a() {

        };
    };


    class classMethodOut {

    }

    class bst {

    }

    public void t() {
        class methodClass {

        }

        class bsttt {

        }
    }

    public void t1() {
        class methodClass {

        }

        class bsttt {

        }
    }

    public void t12(final String a3) {
        System.out.println("a3 = " + a3);
    }

    public static void main(String[] args) {
        LookJavaCompiler lookJavaCompiler = new LookJavaCompiler();
        String a = "333";
        lookJavaCompiler.t12(a);
    }
}
