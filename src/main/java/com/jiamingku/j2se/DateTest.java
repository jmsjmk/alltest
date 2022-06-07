package com.jiamingku.j2se;


import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by jiamingku on 2019/1/22.
 */
public class DateTest {

    public static void main(String[] args) {
        Date d = new Date(1634870034000l);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s1 = simpleDateFormat.format(d);
        System.out.println(s1 );

        String s = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).toString() + ":00";
        System.out.println("s = " + s);
        String e = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).toString() + ":00";
        System.out.println("e = " + e);
    }

    @Test
    public void test() throws ParseException {
        String b = "2030-10-10 12:12:12";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date d = simpleDateFormat.parse(b);
        Date now = Calendar.getInstance().getTime();

        boolean b1 = d.after(now);

        System.out.println(b1);
    }

    @Test
    public void test1() throws ParseException {
        String b = "2030-12-10";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date d = simpleDateFormat.parse(b);

        Date d1 = simpleDateFormat.parse("2030-10-18");

        boolean b1 = d.after(d1);


        System.out.println(b1);

        System.out.println(" ====================== " );

       boolean b111 =  isRqFormat(b);
        System.out.println("b = " + b111);
    }

    @Test
    public void   bbb () {

        String s  = "2020-9-10";

        boolean b = isRqFormat(s);
        System.out.println("b = " + b);
    }

    public static boolean isRqFormat(String time){
//        String format = "([0-9]{4})(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])";
//        Pattern pattern = Pattern.compile(format);
//        Matcher matcher = pattern.matcher(mes);
//        if (matcher.matches()) {
//            pattern = Pattern.compile("(\\d{4})(\\d{2})(\\d{2})");
//            matcher = pattern.matcher(mes);
//            if (matcher.matches()) {
//                int y = Integer.valueOf(matcher.group(1));
//                int m = Integer.valueOf(matcher.group(2));
//                int d = Integer.valueOf(matcher.group(3));
//                if (d > 28) {
//                    Calendar c = Calendar.getInstance();
//                    c.set(y, m-1, 1);//每个月的最大天数
//                    int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
//                    return (lastDay >= d);
//                }
//            }
//            return true;
//        }
//        return false;

        boolean bool = false;
        Pattern pattern = Pattern.compile("[0-9]*");
        if(time != null && time.length() == 10) {
            //校验前四位是否为数字
            if(pattern.matcher(time.substring(0, 4)).matches()) {
                //校验第五位是-
                if("-".indexOf(time.substring(4, 5))!=-1) {
                    //校验第6,7位是否为数字
                    if(pattern.matcher(time.substring(5,7)).matches()) {
                        //校验第8位是否为-
                        if("-".indexOf(time.substring(7, 8))!=-1) {
                            //校验第9,10位是否为数字
                            if(pattern.matcher(time.substring(8, 10)).matches()) {
                                bool = true;
                            }
                        }
                    }
                }
            }

        }
        return bool;

    }

}
