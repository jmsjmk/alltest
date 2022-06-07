package com.jiamingku.j2se.oop.superthis.construct;

import com.jiamingku.j2se.oop.superthis.ALL;
import com.jiamingku.j2se.oop.superthis.Son;

/**
 * Created by jiamingku on 2020/6/11.
 */
public class ThisTest {
    private String s1;
    private String s2;
    private String s3;

    /**
     * 调用 其他构造器--不需要"点"符号
     *
     * @param s1
     */
    public ThisTest(String s1) {
        this(s1, "d");
    }

    public ThisTest(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }

    public static void main(String[] args) {

//        ALL A = new Son();
//        A.t();

    }
}
