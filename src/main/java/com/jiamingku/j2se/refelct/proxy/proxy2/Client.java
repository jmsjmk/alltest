package com.jiamingku.j2se.refelct.proxy.proxy2;


import com.jiamingku.j2se.refelct.proxy.proxy1.InvocationHandler;
import com.jiamingku.j2se.refelct.proxy.proxy1.Proxy;

/**
 * 自己实现的动态代理
 */
public class Client {
    public static void main(String[] args) throws Exception {
        UserMgr mgr = new UserMgrImpl();
        InvocationHandler h = new TransactionHandler(mgr);
        UserMgr u = (UserMgr) Proxy.newProxyInstance(UserMgr.class, h);
        u.addUser();
    }
}
