package com.framework.log.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.NDC;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by jiamingku on 16/7/12.
 */
public class MdcAndNdc {

    private static final Logger LOG = Logger.getLogger("MMDDCC");

    @Before
    public void testBefort() {
        PropertyConfigurator.configure("/Users/jiamingku/IdeaProjects/alltest/src/main/java/com/framework/log/log4j/log4jMdcAndNdc.properties");
    }

    @Test
    public void testMdc() {
        Logger LOG = Logger.getLogger("MMDDCC");
        // log4j的mdc %X{so:-} 可以打出来   MDC.put("so:-" , "eeeeso");
        // log4j的mdc %X{so:-} 打印不出来   MDC.put("so" , "eeeeso");
        MDC.put("so", "eeeeso");

        MDC.put("easy", "eeeeeeasy");
        LOG.info("a" + "DDDD");
        // log4j不支持占位符的
//        LOG.info("a{}", b);
    }

    /**
     * 使用方式上，NDC 通过在 PatternLayout 增加 %x 获取信息，
     * 而 MDC 通过 %X{key} 获取对应 key 的信息。 注意下,MDC 是大写 X，
     * 我在后面的试验开始时使用的小写的，结果无法正确输出
     */
    @Test
    public void ndc() {
        NDC.push("1");
        NDC.push("2");
        NDC.push("3");
        LOG.info("ddddd");
        System.out.println("------------------------------------------------------");
        String s = NDC.pop();
        System.out.println("pop1= " + s);
        s = NDC.pop();
        System.out.println("pop2= " + s);

        s = NDC.pop();
        System.out.println("pop3= " + s);
    }
}
