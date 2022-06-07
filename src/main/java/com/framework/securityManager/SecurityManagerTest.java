package com.framework.securityManager;

/**
 * Created by jiamingku on 2020/2/25.
 *
 * https://www.cnblogs.com/yiwangzhibujian/p/6207212.html   =======最基础的简介
 */
public class SecurityManagerTest {
    public static void main(String[] args) {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            System.out.println("is not null");
        } else {
            System.out.println("is null");
        }
    }
}
