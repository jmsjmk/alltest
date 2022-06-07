package com.jiamingku.j2se.innercalss.multiextend;

/**
 * Created by jiamingku on 2018/6/13.
 *
 * 内部类最大的好处就是多继承
 *
 */
public class Demo extends A {


    public class DemoB extends B {

    }

    public class DemoC extends C {


    }

    public void demo() {
        DemoB demoB = new DemoB();

        demoB.b();
    }
}
