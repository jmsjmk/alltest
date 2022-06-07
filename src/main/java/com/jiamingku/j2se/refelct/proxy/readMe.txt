1.动态代理api的理解.
Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), classes.toArray(new Class[0]), this);
1.1 类加载器
1.2 需要代理的接口(这地方你随便写,你想代理那个接口就代理那个接口,也就是多生成几个回掉方法.)
1.3 就是回到里面要指定的方法,真正干活的(还是反射里面的 method.invoke(object))方法
---
缓存问题:

-- https://blog.csdn.net/danchu/article/details/70238002
动态代理的集中技术点:
1.jdk动态代理
2.cglib（底层用的asm框架)
3.aspectj-这个就是在编译的时候做一些手脚.