package com.jiamingku.j2se.refelct.proxy.assemble;

/**
 * 所有的这些技术都是为了更好适应需求的变化,大多数技术都是解决重复问题,--同样,代理模式也是一样的 <br/>
 * <p>
 * 1.使用组合的继承来说,类的扩充量相对来说减少了
 * <p>
 * 2.组合面向接口编程,接口编程好处太多,主要的目的就是给调用者一个统一使用标准.
 * <p>
 * 3.如果要被代理的接口非常多, 组合的方式也是有诟病的.动态代理出现了.
 * <p>
 * 1.上面的测试代码只能给了MoveAble接口做了代理
 * 2.仔细观察上面的代码,其实代码是可以抽象出来很多东西
 * 3.1 {@link MyProxyOne}
 * 3.2 实现接口
 * 3.2 包含"被代理对象(接口类型的对象)"
 * 3.3 实现"接口方法(被代理对象方法)"---最重要的就是这个方法里面调用"被代理对象的方法"
 * <p>
 * 3.4 通过上面的总结,可抽象出来一种模型如下
 * 3.4.1 动态的生成.java文件, 里面涉及到接口的地方设置成为变量
 * 3.4.2 java文件溶解到jvm中 {@link LoadClassFromCreateFile}
 * 3.4.3 升级版本{@link com.jiamingku.j2se.refelct.proxy.proxy1.Client}
 * 3.4.4 技术点攻克之后只要把门想写的代码给他写到文件中就行了,如下图所示
 * <code>
 * for(Method m : methods) {
 * methodStr += "@Override" + rt +
 * "public void " + m.getName() + "() {" + rt +
 * "   long start = System.currentTimeMillis();" + rt +
 * "   System.out.println(\"starttime:\" + start);" + rt +  //我们需要执行的代码
 * "   t." + m.getName() + "();" + rt +
 * "   long end = System.currentTimeMillis();" + rt +       //我们需要执行的代码
 * "   System.out.println(\"time:\" + (end-start));" + rt +
 * "}"
 * }
 * </code>
 */
public class Client {

    public static void main(String[] args) {
        Tank t = new Tank();
        // 单接口的所谓静态代理。
        MoveAble m1 = new MyProxyOne(t);
        MoveAble m2 = new MyProxyTwo(m1);
        m2.move();

        System.out.println("========下一个,很清晰的执行上下逻辑顺序========");

        MoveAble m3 = new MyProxyTwo(t);

        MoveAble m4 = new MyProxyOne(m3);

        m4.move();
    }
}
