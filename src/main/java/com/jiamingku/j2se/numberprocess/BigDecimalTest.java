package com.jiamingku.j2se.numberprocess;

import com.alibaba.druid.sql.visitor.SQLASTOutputVisitor;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by jiamingku on 2018/12/29.
 */
@SuppressWarnings("all")
public class BigDecimalTest {

    // ---------------------------------------------------------------------------------------------------------------
    // 舍入算法--舍入算法算法
    @Test
    public void test12332() {
//        BigDecimal bigDecimal = new BigDecimal("0.007");
//        String s = bigDecimal.setScale(2).toPlainString();
//        System.out.println("s = " + s);
        String a = "{}";
        JSONObject jsonObject = JSON.parseObject(a);
        String activityRuleId = jsonObject.getString("activityRuleId");
        System.out.println("activityRuleId = " + activityRuleId);



//        BigDecimal b10 = bigDecimal.setScale(0, RoundingMode.HALF_EVEN);
//        System.out.println("b1 = " + b10);
//        System.out.println("bigDecimal = " + bigDecimal);
//        // --------------------------------------------
//        BigDecimal b1 = new BigDecimal("10");
//        BigDecimal b2= null;
//        BigDecimal b3 = b1.subtract(b2);
//        System.out.println("b3 = " + b3);
//

    }

    @Test
    public void testPayment() {
        BigDecimal b1 = new BigDecimal("3.5232");
        BigDecimal b2 = new BigDecimal("3.522222222222222222222222222222222222");

        BigDecimal b111 = b1.subtract(b2, MathContext.DECIMAL64);
        System.out.println("b111 = " + b111);

        BigDecimal b112 = b1.subtract(b2, MathContext.DECIMAL128);
        System.out.println("b112 = " + b112);
        System.out.println("b112.setScale(6,RoundingMode.HALF_EVEN) = " + b112.setScale(6, RoundingMode.HALF_EVEN));

        BigDecimal b113 = b1.subtract(b2, MathContext.DECIMAL32);
        System.out.println("b112 = " + b113);

        System.out.println("977777777777777777777777777777778".length() );
    }

    // ---------------------------------------------------------------------------------------------------------------

    /**
     * 1、用float或者double变量构建BigDecimal对象(最好用字符串处理)
     * 2、通过调用BigDecimal的加，减，乘，除等相应的方法进行算术运算。
     * 3、把BigDecimal对象转换成float，double，int等类型 （intvalue）
     * ============================================
     * BigDecimal.setScale()方法用于格式化小数点
     * setScale(1)表示保留一位小数，默认用四舍五入方式
     * setScale(1,BigDecimal.ROUND_DOWN)直接删除多余的小数位，如2.35会变成2.3 ，相当月截断
     * setScale(1,BigDecimal.ROUND_UP)进位处理，2.35变成2.4
     * setScale(1,BigDecimal.ROUND_HALF_UP)四舍五入，2.35变成2.4
     * setScaler(1,BigDecimal.ROUND_HALF_DOWN)四舍五入，2.35变成2.3，如果是5则向下舍
     *
     * @param args
     */
    public static void main(String[] args) {
        // 这就是为什么创建bigdecimal用字符，比较好
        BigDecimal b1 = new BigDecimal("0.236");
        BigDecimal b2 = new BigDecimal(0.236);
        BigDecimal b3 = new BigDecimal(0.236F);
        System.out.println(b1); //输出0.236
        System.out.println(b2); //输出    0.2359999999999999875655021241982467472553253173828125
        System.out.println(b3); //输出0.236000001430511474609375
        // 转换各种类型
        System.out.println("b1.intValue() = " + b1.intValue());
        System.out.println("b3.floatValue() = " + b3.floatValue());
        System.out.println(b3.floatValue());
    }


    /**
     * 减法操作
     * <p>
     * 用 bigdecimal对象进行减法运算的时候，他没有像 divide方法那样指定标度，所以在计算的时候可能出现意想不到的结果，你可以设置 标度，与取舍模式 ，
     * 得到我们想要的结果通过下面的方式，也可以通过格式化方式得到。
     */
    @Test
    public void test1() {
        BigDecimal db1 = new BigDecimal(Float.parseFloat("10.00"));
        BigDecimal db2 = new BigDecimal(Float.parseFloat("9.6"));
        System.out.println("db2 = " + db2);
        BigDecimal res = db1.subtract(db2);
        System.out.println("res = " + res);
        res = res.setScale(4, BigDecimal.ROUND_HALF_UP);
        System.out.println("res = " + res);
        // ---------------------------------------
        Float f1 = Float.parseFloat("10.00");
        System.out.println("f1 = " + f1);
        Float f2 = Float.parseFloat("9.6");
        System.out.println("f2 = " + f2);
    }

    /**
     * 精度就是小数点后面的位数，加减操作 取最长的精度作为结果的精度
     */
    @Test
    public void test2() {
        // 用float 计算时候不准确,尽量用字符串构建
        BigDecimal b1 = new BigDecimal(Float.parseFloat("10.00"));
        System.out.println(b1); // 10
        // ---用bigdecimal 进行转换的时候，必须的使用字符串--用浮点型会出现意外的效果
        BigDecimal b2 = new BigDecimal(Float.parseFloat("9.6"));
        System.out.println(b2); // 9.6000003814697265625
        BigDecimal res = b1.subtract(b2);
        System.out.println(res); // 0.3999996185302734375
        // 结果四舍五入
        res = res.setScale(4, BigDecimal.ROUND_HALF_UP);
        System.out.println(res);
        System.out.println("===========下面是为什么使用字符串构建=======");
        // 用string构件的对象计算是准确的
        BigDecimal b11 = new BigDecimal("10.000");
        System.out.println(b11); // 10.000 默认减法运算取精度最高的来进行
        BigDecimal b21 = new BigDecimal("9.6");
        System.out.println(b21); // 9.6
        BigDecimal res1 = b11.subtract(b21);
        System.out.println(res1); // 0.400
    }

    /**
     * 除法
     * <p>
     * 操作，必须指定精度否则出错。爆异常,
     * 2020-1-2能整除的排除在外。
     */
    @Test
    public void test3() {
        String a = "10";
        String b = "3.00";

        BigDecimal b1 = new BigDecimal(a);
        System.out.println(b1);
        BigDecimal b2 = new BigDecimal(b);
        System.out.println(b2);

        // 除法必须的指定精度之类的东西
        // Non-terminating decimal expansion; no exact representable decimal result.
//        BigDecimal divideRes = b1.divide(b2);---异常
        BigDecimal divideRes = b1.divide(b2, 4, BigDecimal.ROUND_HALF_UP);
        System.out.println("指定了精度的返回结果 divideRes:" + divideRes);

        // ---相当于指定了精度
        BigDecimal c = b1.divide(b2, MathContext.DECIMAL64);
        System.out.println("c = " + c);
    }


    /**
     * 乘法：乘法的精度就是连个操作数字的精度相加
     * <p>
     * 在用bigdecimal的时候你输入几位小数，他就是几位小数。运算之后小数一个都不舍去。
     * 你输入0.2360 * 100 当你 100.00或者 100.000小数点后面的位数都是变化的。 Api里面说是乘数+被乘数 的精度
     */
    @Test
    public void test4() {
        String a = "2.1010";
        String b = "2.10222";

        BigDecimal b1 = new BigDecimal(a);
        System.out.println(b1);
        BigDecimal b2 = new BigDecimal(b);
        System.out.println(b2);

        BigDecimal bd12 = b1.multiply(b2);
        System.out.println("bd12 = " + bd12);
    }

    @Test
    public void test5() {
        String a = "0.2360";
        String b = "100.00";

        BigDecimal b1 = new BigDecimal(a);
        System.out.println(b1);
        BigDecimal b2 = new BigDecimal(b);
        System.out.println(b2);

        BigDecimal bd12 = b1.multiply(b2);
        System.out.println("bd12 = " + bd12);
    }

}
