package com.jiamingku.j2se.exception;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by jiamingku on 2018/12/19.
 */
public class FinallyReturnException {

    public static void main(String[] args) {
        int a = getResult1();
        System.out.println("a = " + a);

        System.out.println(" ========================== ");

        a = getResultForFinallyReturn();
        System.out.println("a = " + a);

        try {
            getResult1Exception();
        }catch (Exception E) {
            System.out.println("E.getClass() = " + E.getClass());
        }
    }

    /**
     * 下面的代码在catch里面赋6,在finally里面赋予了5,在返回的结果还是6,没有更改why?
     * <p>
     * 原因:当java运行的过程中如果try语句块或者catch 语句块遇到了return或者throw e语句，方法会结束-此时返回值多少就是多少(或者是异常)，
     * 但是 java虚拟机不会立即结束方法，他会去找是否有 finally 语句块：
     * 1.如果没有finally立即执行结束.
     * <p>
     * 2.如果有会执行 finally 语句块，如果 finally语句块中有return， throw语句会马上结束此方法 并且屏蔽try语句块中的返回结果。
     * 如果没有返回值或异常信息的话，不会改变返回的值
     * <p>
     * <p>
     * 3.结论:finally 语句块中如果有return 语句会屏蔽 try 块，或者 catch 块中的return 的返回结果。
     * <p>
     * Finally 可以与try 语句配对使用（没有 catch语句）
     * Try 也可以有catch 配对使用（没有 finally语句块）
     * 三者中只要存在 try +两者中的一个，或者 try +两者 都可以，
     * 在catch中如果有return 语句，finally 的语句块也会被执行。除非在 catch异常中调用,System.exit(1) 强制退出.Finally
     * <p>
     * Pjava 疯狂讲义355 页面 有段话很重要
     * <p>
     * 如果finally 里面没有返回值, 无论如何修改返回值都 不会影响 catch 里面的return结果
     * 除非finally里面报错抛出异常
     */
    private static int getResult1() {
        int result = 1;
        try {
            FileInputStream file = new FileInputStream("d:\\dddd.txt");
            System.out.println(file);
        } catch (FileNotFoundException e) {
            result = 6;
            return result;
        } finally {
            System.out.println("finally result before update :" + result);
            result = 5;
            System.out.println("finally result after update :" + result);
            if (false) {
                // finally 抛出异常 中断返回值
                throw new NullPointerException("FIN");
            }
        }
        return result;
    }

    private static int getResult1Exception() {
        int result = 1;
        try {
            FileInputStream file = new FileInputStream("d:\\dddd.txt");
            System.out.println(file);
        } catch (FileNotFoundException e) {
            result = 6;
            throw new NullPointerException(e.getMessage()); 
        } finally {
            throw new IllegalArgumentException("illll"); 
        }
      
    }

    @Test
    public void testGetResult1() {
        int returnResult = getResult1();
        System.out.println("returnResult = " + returnResult);
    }

    // -------------------------------------------------------------------------------------------------------

    /**
     * finally 不会更改 return的值, 除非finally 里面有返回值语句
     */
    private static int getResultForFinallyReturn() {
        int result = 1;
        try {
            result = 100;
            return 100;
        } catch (Exception e) {
            e.printStackTrace();
            result = 6;
            return result;
        } finally {
            System.out.println("finally result before update :" + result);
            result = 5;
            System.out.println("finally result after update :" + result);
            // 屏蔽返回值
            return result;

        }
    }

    /**
     * @return
     */

    private static int getResultForFinallyThrow() throws NullPointerException {
        int result = 1;
        try {
            result = 100;
            return 100;
        } catch (Exception e) {
            e.printStackTrace();
            result = 6;
            return result;
        } finally {
            System.out.println("finally result before update :" + result);
            result = 5;
            System.out.println("finally result after update :" + result);
            // 屏蔽返回值,将异常抛出
            throw new NullPointerException("NULL");

        }
    }

    @Test
    public void testGetResult() {
        int result = getResultForFinallyThrow();
        System.out.println("result = " + result);
    }

    @Test
    public void testGetResult11() {
        int result = getResultForFinallyReturn();
        System.out.println("result = " + result);
    }
    // -------------------------------------------------------------------------------------------------------

    private static int getResultForSystemExist() {
        int result1 = 1;
        try {
            result1 = 1002;
            throw new NullPointerException("Kong");
        } catch (Exception e) {
            e.printStackTrace();
            result1 = 6;
            // 根本就不会指定到finally
            System.exit(1);
            return result1;
        } finally {
            System.out.println("finally result before update :" + result1);
            result1 = 5;
            System.out.println("finally result after update :" + result1);
            return result1;
        }
    }

    @Test
    public void testGetResultForSystemExist() {
        int result = getResultForSystemExist();
        System.out.println("result = " + result);
    }
}
