package com.jiamingku.j2se.accesscontroller.innercalsstest.inn;

import com.google.common.base.Objects;

/**
 * Created by jiamingku on 2019/10/8.
 */
public class TT {
    private String name;
    private String age;
    private String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TT tt = (TT) o;
        return Objects.equal(name, tt.name) && Objects.equal(age, tt.age) && Objects.equal(text, tt.text);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, age, text);
    }

    public static void main(String[] args) {
        // what 是不见的
        // What t = new What();
    }
}
