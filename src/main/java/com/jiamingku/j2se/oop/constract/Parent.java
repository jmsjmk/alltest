/**
 * Project Name:T
 * File Name:TestParent.java
 * Package Name:com.jiamingku
 * Date:2015-8-17下午3:35:58
 *
 */

package com.jiamingku.j2se.oop.constract;

/**
 * ClassName:TestParent <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-8-17 下午3:35:58 <br/>
 * 
 * @author Administrator
 * @version
 * @since JDK 1.6
 * @see
 */
public class Parent {
    private static int q = 1;
    private static int qq;
    private static final int qqq = 111;
    private String aaaa = "sdsfdfsdf";
    public int aaaaaaaaaaaaaaaaaaaa = 3;
     static {
         System.out.println("类连接时候进行一次初始化，类初始化时候由进行一次");
     qq = 333;
     }

    public static void main(String[] args) {
        Parent p = new Parent();

        System.out.println("1");
        System.out.println(qq);

    }

    public Parent() {
        super();
        // TODO Auto-generated constructor stub
       System.out.println("parent... ..");
    }
}
