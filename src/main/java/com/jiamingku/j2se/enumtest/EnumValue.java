package com.jiamingku.j2se.enumtest;

/**
 * Created by jiamingku on 16/8/19.
 * <p>
 * 枚举值不存在曝出异常.
 */
public enum EnumValue {
    Abss, BB;


    /**
     * 1.枚举值不存在发生异常.
     */

    public static void main(String[] args) {

        // ----------------------1.枚举值不存在发生异常.
        try {
            EnumValue bsdfsdf = EnumValue.valueOf("bsdfsdf");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        // -----------------------2.基本方法-
        for (EnumValue enumTest : EnumValue.values()) {
            System.out.println(enumTest.toString());
            System.out.println(" enumTest.name() = " + enumTest.name());
            System.out.println("enumTest.ordinal() = " + enumTest.ordinal());
        }
        System.out.println(" ===== ");

        Integer i = new Integer(0);
        if (i.equals(EnumValue.Abss)) {
            System.out.println("i = " + i);
        }

        // -----------------------3.ordinal-方法.
        System.out.println(EnumValue.Abss.ordinal());

        // -----------------------4.switch-方法. case的后面不用接枚举的类型
        EnumValue enumValue = null;
        switch (enumValue) {
            case BB:
                System.out.println("enumValue = " + enumValue);
                break;
            case Abss:
                System.out.println("enumValue111 = " + enumValue);
                break;
            default:
        }

        // -----------------------5. 枚举可以直接使用等号
        if (Abss == BB) {

        }

    }
}
