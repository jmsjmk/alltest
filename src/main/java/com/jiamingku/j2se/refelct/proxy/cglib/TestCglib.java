package com.jiamingku.j2se.refelct.proxy.cglib;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.ImmutableBean;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

public class TestCglib {

    @Test
    public void testImmutableBean() throws Exception {
        SampleBean bean = new SampleBean();
        bean.setValue("Hello world");
        SampleBean immutableBean = (SampleBean) ImmutableBean.create(bean); //创建不可变类


        Assert.assertEquals("Hello world", immutableBean.getValue());
        bean.setValue("Hello world, again"); //可以通过底层对象来进行修改
        Assert.assertEquals("Hello world, again", immutableBean.getValue());

        immutableBean.setValue("Hello cglib"); //直接修改将throw exception
        System.out.println(".....");
    }

    @Test
    public void testBeanGenerator() throws Exception {
        BeanGenerator beanGenerator = new BeanGenerator();
        beanGenerator.addProperty("value", String.class);
        Object myBean = beanGenerator.create();
        Method setter = myBean.getClass().getMethod("setValue", String.class);
        setter.invoke(myBean, "Hello cglib");

        Method getter = myBean.getClass().getMethod("getValue");
        Assert.assertEquals("Hello cglib", getter.invoke(myBean));

        System.out.println("getter.invoke(myBean) = " + getter.invoke(myBean));
    }


    @Test
    public void testBeanCopier() throws Exception {
        BeanCopier copier = BeanCopier.create(SampleBean.class, OtherSampleBean.class, false);//设置为true，则使用converter
        SampleBean myBean = new SampleBean();
        myBean.setValue("Hello cglib");
        OtherSampleBean otherBean = new OtherSampleBean();
        copier.copy(myBean, otherBean, null); //设置为true，则传入converter指明怎么进行转换
        Assert.assertEquals("Hello cglib", otherBean.getValue());
    }
}

class OtherSampleBean {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

