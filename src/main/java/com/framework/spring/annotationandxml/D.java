package com.framework.spring.annotationandxml;

/**
 * Created by jiamingku on 2018/11/6.
 */
public class D {
    private A a;
    private B b;
    private C d;


    public void t() {
        b.a();
    }
    public A getA() {
        return a;
    }

    /**
     * 此bean是通过注解扫描到容器中，
     * 在配置文件中可以，直接配置。

     <bean id="d" class="com.framework.spring.annotationandxml.D">
     <property name="b" ref="b"/>
     <property name="a" ref="a1"/>
     </bean>
     *
     * @param a
     */
    public void setA(A a) {
        System.out.println(" = D ssssssssssseeeettttttttttttttttttttt" );
        this.a = a;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public C getD() {
        return d;
    }

    public void setD(C d) {
        this.d = d;
    }
}
