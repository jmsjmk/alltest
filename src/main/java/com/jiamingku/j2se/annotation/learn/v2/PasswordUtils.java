package com.jiamingku.j2se.annotation.learn.v2;

import java.util.List;

public class PasswordUtils {

    @UseCase(id = 47, description = "Passwords must contain at least one numeric")
    public boolean validatePasswords(String password) {
        return (password.matches("\\w*\\d\\w*"));
    }

    @UseCase(id = 48)
    public String encryptPassword(String password) {
        return new StringBuilder(password).reverse().toString();
    }

    @UseCase(id = 49, description = "New passwords can't equal previously used ones")
    public boolean checkForNewPassword(List<String> prevPasswords, String password) {
        return !prevPasswords.contains(password);
    }

    /**
     * 有默认值的注解，不用指定属性的值得
     *
     * @NoDefaultValue() 也可以，括号都可以省略
     */
    @NoDefaultValue
    public boolean checkForNewPassword1(List<String> prevPasswords, String password) {
        return !prevPasswords.contains(password);
    }
}
