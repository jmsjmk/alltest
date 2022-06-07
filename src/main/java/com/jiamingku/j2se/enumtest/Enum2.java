package com.jiamingku.j2se.enumtest;

/**
 * Created by jiamingku on 2017/6/26.
 */
public enum Enum2 {

    // ------------------------------------------------------------------------------------------------------
    // EnumMap<AntStatus, String> antMessages = new java.util.EnumMap<>(AntStatus.class);

    // ----------------------------------------------------------------------------------------------------
    // 枚举类必须放在类的第一行
    //    public void a() {
    //        System.out.println("=");
    //    }

    // -------------------------------------------------------------------------------------------------------

    A("xiaoming", "3", "好嗯"),
    B("xiaoming", "3", "好嗯"),
    C("xiaoming", "3", "好嗯");

    private String name;
    private String age;
    private String description;

    /**
     * 构造器私有，没有写就是private
     *
     * @param age
     * @param description
     * @param name
     */
    Enum2(String age, String description, String name) {
        this.age = age;
        this.description = description;
        this.name = name;
    }

    public static void main(String[] args) {
        System.out.println("A.name() = " + A.name());

        if (A instanceof Enum) {
            System.out.println("args = ");
        } else {
            System.out.println(" ===== ");
        }
        //Enum2.valueOf(Grade.class, Grade.A.name());
        Grade A = Enum2.valueOf(Grade.class, "A");
        Enum2 A1 = Enum2.valueOf("A");
        System.out.println("A = " + A);
//        Enum.valueOf(String.class, "ddd");
    }

    // -------------------------------------------------------------------------------------------------------------------------

}
