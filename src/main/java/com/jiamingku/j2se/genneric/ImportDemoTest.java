package com.jiamingku.j2se.genneric;

/**
 * 关于泛型的两个概念.深入理解(好多年前就应该搞定的东西)
 * 1)自限定类型
 * <p>
 * 2)协助泛型类
 * 协助泛型类 官方的说法叫协助泛型类。目的就是限定泛型类的类型,好处就是返回确定类型的子类。think175
 * 泛型绑定上限的目的是什么呢？保证解析结果是HttpMessage的子类也就是可以得到具体子类的类型
 * 例子:
 * public interface HttpMessageParser<T extends HttpMessage> {
 * T parse()throws IOException,HttpException;
 * }
 * ----
 * 1)如果不绑定感觉也没啥区别,但是你对应的实现接口只能面向一个类型的参数，因为方法重写的时候,参数的类型必须是跟定义接口的类型一致，针对于某些业务来说
 * 参数的类型各种各种，你可以统一就会导致接受参数臃肿难维护,
 * 举个例子来说明吧：com.yoho.erp.stock.service.center.business.BusinessService---可以很好的解释类型绑定(协助泛型类)
 * 2)
 * 如果不设计协助泛型类的话,http请求时候请求报文格式就很多中,最终都要转换成为一个复杂的报文格式, 才能用设计中的模板,但是你设计了绑定,
 * 可以确定进来的每个类型,只要继承下,其实就将复杂的问题细化了
 */

public class ImportDemoTest {


    // --------------------------------------------------------------------------------------
        // ----https://www.cnblogs.com/storml/p/7930565.html  泛型的擦除补偿...
    /**
     * 泛型工厂潜在的存在风险.
     */
    static class ClassAsFactory<T> {
        T x;

        public ClassAsFactory(Class<T> kind) {
            try {
                x = kind.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    class Employee {
    }

    static class InstantiateGenericType {
        public static void main(String[] args) {
            new ClassAsFactory<Employee>(Employee.class);
            System.out.println("ClassAsFactory<Employee> succeeded");
            try {
                // Integer并没有默认构造器，所以调用newInstance()时会报Error
                new ClassAsFactory<Integer>(Integer.class);
            } catch (Exception e) {
                System.out.println("ClassAsFactory<Integer> failed");
            }
        }
    }

    // --------------------------------------------------------------------------------------

    // ---java的伙计们希望如下实现
    interface FactoryI<T> {
        T create();
    }

    static class Foo<T> {
        public final T x;

        public <F extends FactoryI<T>> Foo(F factory) {
            x = factory.create();
        }
    }

    // 直接实现FactoryI接口
    static class IntegerFactory implements FactoryI<Integer> {
        @Override
        public Integer create() {
            return new Integer(0);
        }
    }

    // 创建静态内部类实现FactoryI接口
    static class Widget {
        public static class Factory implements FactoryI<Widget> {
            @Override
            public Widget create() {
                return new Widget();
            }
        }
    }

    static class FactoryConstraint {
        public static void main(String[] args) {
            new Foo<Integer>(new IntegerFactory());
            new Foo<Widget>(new Widget.Factory());
        }
    }

    //---------------------------------
}
