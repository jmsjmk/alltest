package com.jiamingku.j2se;

import org.junit.Test;

public class ForTest {

    @Test
    public void demo() {
        outer:
        while (true) {
            go:
            for (int i = 0; i < 10; i++) {
                System.out.println("i值为: " + i);
                if (i == 2) {
                    inter:
                    for (int j = 0; i < 3; j++) {
                        System.out.println("j值为: " + j);
                        if (j == 1) {
                            System.out.println("j==1跳出inter");
                            break inter;
                        }
                    }
                }
                if (i == 3) {
                    System.out.println("i==3，继续循环go");
                    // 不紧紧可以continue go，还可以continue 到outer
                    // continue go;
                    continue outer;
                }
            }
        }
    }

    @Test
    public void demo1() {
        outer:
        while (true) {
            go:
            for (int i = 0; i < 10; i++) {
                System.out.println("i值为: " + i);
                if (i == 2) {
                    inter:
                    for (int j = 0; i < 3; j++) {
                        System.out.println("j值为: " + j);
                        if (j == 1) {
                            System.out.println("j==1跳出inter");
                            break inter;
                        }
                    }
                }
                if (i == 3) {
                    System.out.println("i==3，继续循环go");
                    continue outer;
                }
//                if (i == 4) {
//                    System.out.println("i==4，跳出outer");
//                    continue outer;
//                }
//
//                if (i == 5) {
//                    System.out.println("i==5，跳出outer");
//                    break outer;
//                }
            }
        }
    }


    @Test
    public void testBreak() {


        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i);
            /** break 只会跳出一层循环*/
            for (int j = 0; i < 10; j++) {
                System.out.println("j = " + j);
                break;
            }
            System.out.println(" === ");
        }
    }

    // ----------------------------------------------

    /**
     * 在没有指定赋值语句的时候,这里面的字节码是一样的，
     * 你可以认为没区别
     */
    public void testIAddAdd() {
        int i = 10;
        ++i;
    }

    public void testAddAddI() {
        int i = 10;
        i++;
    }

    /**
     * 对于有赋值语句的时候,
     * i++ 需要个临时变量保存原来的值
     * <p>
     * ++j也是需要的多余一个变量，从字节码你可以看出来
     * <p>
     * ====================
     * 但是网上都说 ++j 效率好些。?
     */
    public void testAdd() {
        int i = 10;
        int a = i++;

        int j = 20;
        int b = ++j;
    }

    @Test
    public void test111() {
        int i = 10;
        i = i++;
        System.out.println(i);
    }

    // ----------------------------------------------
    static class T {
    }

    @Test
    public void testGoTo() {
        /*
          continue这里 i 值还是继续相加
         */
        out:
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            for (int j = 0; j < 10; j++) {
                System.out.println("---" + j);
                if (j == 5) {
                    continue out;
                }
                if (j == 4) {
                    continue;
                }
            }
        }
    }


    @Test
    @SuppressWarnings("ALL")
    public void testGoTo2() {
        /*
          continue这里 i 值还是继续相加
         */
        out:
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            th3:
            for (int j = 0; j < 10; j++) {
                System.out.println("---" + j);
                if (j == 5) {
                    continue th3;// ----****等同于写了continue.
                }
                if (j == 4) {
                    continue;
                }
            }
        }
    }


    @Test
    @SuppressWarnings("ALL")
    public void testGoTo12() {
        /*
          continue这里 i 值还是继续相加
         */
        out:
        for (int i = 0; i < 100; i++) {
            System.out.println(i);

            for (int j = 0; j < 10; j++) {
                System.out.println("---" + j);
                if (j == 5) {
                    continue out;// ----****等同于写了continue.
                }

            }
        }
    }


    @Test
    @SuppressWarnings("ALL")
    public void testGoToBreak() {
        out:
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            th3:
            for (int j = 0; j < 10; j++) {
                if (j == 3) {
//                    break th3;// ----****等同于写了continue.
                    continue out;
                }
                System.out.println("---" + j);
                if (j == 4) {
                    break out;
                }
            }
        }
    }
}
