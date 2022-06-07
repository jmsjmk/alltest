package com.jiamingku.test;

@SuppressWarnings("all")
public class XiuShiFuSonOtherPackageTest {

    /**
     * protected修复符在子
     *
     * @param args
     */
    public static void main(String[] args) {
        XiuShiFuSonOtherPackage x = new XiuShiFuSonOtherPackage();

        /**
         * 必须在子类里面反问。跳出子类里面，任何位置都不可以
         */
        // System.out.println(x.protected_value);
        // x.protected_method();

        /**
         * 注意：在子类中可见,必须是子类对象.protected
         *
         * 那怕，是父类对象也是不可以
         *
         */
        // XiuShiFu shiFu = new XiuShiFu();
        // shiFu.protected_method();

    }
}