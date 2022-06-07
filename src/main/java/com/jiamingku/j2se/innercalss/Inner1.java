package com.jiamingku.j2se.innercalss;

/**
 * 单利模式的初始化快
 * 这也就是为什么我们在单利模式的使用了，静态内部类,实现了懒加载，并且避免了多线程问了。
 */
public class Inner1 {
    // 类被使用的时候就会被执行
    static {
        System.out.println("宿主类,静态初始化快");
    }

    // 创建对象的时候就会执行
    {
        System.out.println("初始化快");
    }

    /**
     * <p>
     * </p>
     *
     * @param args
     */
    public static void main(String[] args) {
        Inner1 i = new Inner1();
        System.out.println("执行完初始化,使用内部类的时候才");
        i.getIn(); // 此方法不掉 不会执行 In的静态初始化块
    }

    static class In {
        // 啥时候用到啥时候才会执行
        static {
            System.out.println("内部类初始化块");
        }

        public static final String INSTANCE = "dsdf";
    }

    public In getIn() {
        return new In();
    }
}