package com.framework.spring.spel;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Created by jiamingku on 2018/11/5.
 * <p>
 * 也是注入bean-只是拿另外的属性-方法获取值进行装配，就是特殊的装配方式
 */
public class SpelTest1 {

    /**
     * https://blog.csdn.net/zhoudaxia/article/details/38174169
     */
    @Test
    public void helloWorld() {

        ExpressionParser parser = new SpelExpressionParser();
        Expression expression =
                parser.parseExpression("('Hello' + ' World').concat(#end)");
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("end", "!");
        // System.out.println("expression = " + expression.getValue());
        Assert.assertEquals("Hello World!", expression.getValue(context));
        System.out.println(" expression.getValue(context) = " + expression.getValue(context));
    }

    @Test
    public void testParserContext() {
        ExpressionParser parser = new SpelExpressionParser();
        ParserContext parserContext = new ParserContext() {
            @Override
            public boolean isTemplate() {
                return true;
            }

            @Override
            public String getExpressionPrefix() {
                return "#{";
            }

            @Override
            public String getExpressionSuffix() {
                return "}";
            }
        };
        String template = "#{'Hello '}#{'World!'}";
        Expression expression = parser.parseExpression(template, parserContext);
        Assert.assertEquals("Hello World!", expression.getValue());

        System.out.println(" = " + expression.getValue());
    }

    /**
     * 类类型表达式：使用“T(Type)”来表示java.lang.Class实例，“Type”必须是类全限定名，“java.lang”包除外，
     * 即该包下的类可以不指定包名；使用类类型表达式还可以进行访问类静态方法及类静态字段。
     */
    @Test
    public void testClassTypeExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        //java.lang包类访问
        Class<String> result1 = parser.parseExpression("T(String)").getValue(Class.class);
        System.out.println("result1 = " + result1);
        Assert.assertEquals(String.class, result1);
        //其他包类访问
//        String expression2 = "T(cn.javass.spring.chapter5.SpELTest)";
//        Class<String> result2 = parser.parseExpression(expression2).getValue(Class.class);
//        Assert.assertEquals(SpelTest1.class, result2);
//        //类静态字段访问
//        int result3=parser.parseExpression("T(Integer).MAX_VALUE").getValue(int.class);
//        Assert.assertEquals(Integer.MAX_VALUE, result3);
//        //类静态方法调用
//        int result4 = parser.parseExpression("T(Integer).parseInt('1')").getValue(int.class);
//        Assert.assertEquals(1, result4);
    }
}
