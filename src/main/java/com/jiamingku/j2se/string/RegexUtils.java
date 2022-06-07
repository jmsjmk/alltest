package com.jiamingku.j2se.string;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexUtils {


    @Test
    public void test1() {
        // 03-01-052-02-0005
        String seatCodeString = "12-12-12-12-12";
        String regex = "\\d+(-\\d+){4}";
        if (StringUtils.isBlank(seatCodeString) || !RegexUtils.validate(seatCodeString, regex)) {
            throw new IllegalArgumentException("arg 'seatCodeString' is inappropriate.");
        }
        int houseid = Integer.parseInt(seatCodeString.split("-")[0]);
        System.out.println("houseid = " + houseid);
    }

    /**
     * 正则匹配目标字符串.
     *
     * @return true:匹配成功 false:匹配失败
     */
    public static boolean validate(String target, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(target);
        return m.matches();
    }

    public static boolean matches(String regPattern, String target) {
        Pattern pattern = Pattern.compile(regPattern);
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }

    public static boolean matches(String regPattern, int target) {
        return matches(regPattern, target + "");
    }


    private static Pattern WHITESPACE_PATTERN = Pattern.compile(
            "\\p{javaWhitespace}+");

    public RegexUtils() {

    }


    @Test
    public void t() {
        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(\\D*)(\\d+)(.*)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
            System.out.println("Found value: " + m.group(2));
            System.out.println("Found value: " + m.group(3));
        } else {
            System.out.println("NO MATCH");
        }
    }

    private static final String REGEX = "\\p{javaWhitespace}";
    private static final String INPUT = "Bb12 \tc!";

    public static void main(String[] args) {
        // create a pattern
        Pattern pattern = Pattern.compile(REGEX);

        // get a matcher object
        Matcher matcher = pattern.matcher(INPUT);

        while (matcher.find()) {
            //Prints the start index of the match.
            System.out.println("Match String start(): " + matcher.start());
        }
    }//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/javaregex/javaregex_java_class_javawhitespace.html


    @Test
    public void main222() {
        String a = "https://pay.abchina.com/ebusperbank/PaymentModeNewAct.ebf?TOKEN=15753560246988869886";
        String b = "https://mobile.abchina.com/mpay/mobileBank/zh_CN/EBusinessModule/BarcodeH5Act.aspx?token=15753560246988869886";
        String s = getParamByUrl(a, "TOKEN");
        System.out.println("s = " + s);
        String dd = getParamByUrl(b, "token");
        System.out.println("dd = " + dd);
        String t = "<URL>%s</URL>";
        String sss = String.format(t, a);
        System.out.println("sss = " + sss);
    }

    /**
     * 获取指定url中的某个参数
     *
     * @param url
     * @param name
     * @return
     */
    public static String getParamByUrl(String url, String name) {
        url += "&";
        String pattern = "(\\?|&){1}#{0,1}" + name + "=[a-zA-Z0-9]*(&{1})";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(url);
        if (m.find()) {
            return m.group(0).split("=")[1].replace("&", "");
        } else {
            return null;
        }
    }
}
