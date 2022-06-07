package com.framework.jmx;

import org.junit.Test;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.Permission;

/**
 * --基本的概述
 * https://zq99299.github.io/java-tutorial/essential/environment/security.html
 * <p>
 * https://blog.csdn.net/weixin_30629653/article/details/99784681
 * --
 * 安全管理器就相当于应用程序在执行一些操作的时候-进行一次检查
 * 固定的动作进行检查，
 * <p>
 * --启动安全管理器
 * https://www.cnblogs.com/straybirds/p/8410958.html
 * <p>
 * --AccessController.doPrivileged
 * https://blog.csdn.net/jiangtianjiao/article/details/87909065
 * <p>
 * <p>
 * org.apache.commons.pool2.impl.CallStackTest 2.4.3 里面有一段源码
 */
public class SecurityManagerTest {
    public void test1() {
        SecurityManager appsm = System.getSecurityManager();
        System.out.println(appsm);
        SecurityManager security = System.getSecurityManager();
        System.out.println(security);
        SecurityManager s = new SecurityManager();
        System.out.println(s);
//        System.setSecurityManager(s);
        System.exit(-1);
        appsm = System.getSecurityManager();
        System.out.println(appsm);
        System.exit(1);
    }

    /**
     * 其实就相当于钩子函数的作用
     * 简单的来说就是安全管理器你指定了一些操作
     * ，应用程序在执行对应的操作时候，就会执行相应的操作，感觉像钩子函数.
     */
    @Test
    public void testExistSecurityManager() {
        NoExitSecurityManager manager = new NoExitSecurityManager();
        System.setSecurityManager(manager);
        try {
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("33");
    }


    /**
     * 线程池：里面有代码如下：
     * this.acc = System.getSecurityManager() == null ?
     * null :
     * AccessController.getContext()
     */
    @Test
    public void testAccess() {
        AccessControlContext accessControlContext = AccessController.getContext();
        System.out.println(accessControlContext.toString());
    }

    /**
     * 线程池方法：：：：：：
     */
//    private void checkShutdownAccess() {
//        SecurityManager security = System.getSecurityManager();
//        if (security != null) {
//            security.checkPermission(shutdownPerm);
//            final ReentrantLock mainLock = this.mainLock;
//            mainLock.lock();
//            try {
//                for (ThreadPoolExecutor.Worker w : workers)
//                    security.checkAccess(w.thread);
//            } finally {
//                mainLock.unlock();
//            }
//        }
//    }

    /**
     * 拦截系统退出
     */
    private static class NoExitSecurityManager extends SecurityManager {

        boolean exitFilter = true;

        @Override
        public void checkPermission(Permission perm) {
        }

        @Override
        public void checkPermission(Permission perm, Object context) {
        }

        @Override
        public void checkExit(int status) {
            System.out.println("status:" + status);
            super.checkExit(status);
            if (exitFilter) {
                throw new ExitException(status);
            }
        }
    }

    protected static class ExitException extends SecurityException {
        private static final long serialVersionUID = 1L;
        public final int status;
        public ExitException(int status) {
            super("成功拦截System.ext(0)！");
            this.status = status;
        }
    }
}
