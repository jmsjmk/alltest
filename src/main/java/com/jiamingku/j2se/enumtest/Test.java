package com.jiamingku.j2se.enumtest;


import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalTime;
import java.util.Date;

/**
 * Created by jiamingku on 2017/6/26.
 */
public class Test {
    public static void main(String[] args) {

        Student student = new Student("1name", "2name");
        student.assignGrade(Grade.A);
        student.assignEnum2(Enum2.A);

        // 枚举值可以直接进行比较,equals相当于==号操作符
        if (student.getGrade() == Grade.A) {
            System.out.println("this is A");
        }

        // 枚举的迭代
        System.out.println("===迭代枚举");
        for (Grade g : Grade.values()) {
            // 枚举类都继承了java.lang.Enum的方法，所有里面有很多方法可以使用 记住下面这两点：
            // 1.正常打印枚举类，他默认是调用他的toString方法，他的toString 方法返回的是枚举的name.\
            // eg: Color c=Color.RED;
            //  System.out.println（c）;//返回结果： RED
            System.out.println(g);
            System.out.println("g.name() = " + g.name());
        }
        System.out.println("===");
        for (Enum2 g : Enum2.values()) {
            System.out.println(g);
            System.out.println("--");
            System.out.println("g.toString() = " + g.toString());
        }
        // 枚举的case子句不用加枚举的类型
        Grade g = Grade.A;
        switch (g) {
            case A:
                System.out.println("dd");
//                break;
            case B:
            case C:
                break;
        }

        switch (Grade.A) {
            case A:
                System.out.println("g = " + g);
                break;
            default:
                System.out.println("OkHttpClientHttpRequestFactory =");
        }
    }


    /**
     * 在这里，枚举值被传递到 switch 语句中（请记住，getGrade() 是作为 Grade 的实例返回的），
     * <p>
     * 而每个 case 子句将处理一个特定的值。该值在提供时没有枚举前缀，这意味着不用将代码写成 case Grade.A，只需将其写成 case A 即可。
     * <p>
     * 如果您不这么做，编译器不会接受有前缀的值。
     *
     * @param out
     * @param student1
     * @throws IOException
     */
    public void tchtestSwitchStatement(PrintStream out, Student student1) throws IOException {
        StringBuffer outputText = new StringBuffer(student1.getFullName());
        switch (student1.getGrade()) {
            case A:
                outputText.append(" excelled with a grade of A");
                break;
            case B: // fall through to C
            case C:
                outputText.append(" passed with a grade of ")
                        .append(student1.getGrade().toString());
                break;
            case D: // fall through to F
            case F:
                outputText.append(" failed with a grade of ")
                        .append(student1.getGrade().toString());
                break;
            case INCOMPLETE:
                outputText.append(" did not complete the class.");
                break;
        }
        out.println(outputText.toString());
    }

    public void testSwitchStatement1(PrintStream out, Student student1) throws IOException {
        StringBuffer outputText = new StringBuffer(student1.getFullName());
        switch (student1.getGrade()) {
            case A:
                outputText.append(" excelled with a grade of A");
                break;
            case B: // fall through to C
            case C:
                outputText.append(" passed with a grade of ")
                        .append(student1.getGrade().toString());
                break;
            case D: // fall through to F
            case F:
                outputText.append(" failed with a grade of ")
                        .append(student1.getGrade().toString());
                break;
            case INCOMPLETE:
                outputText.append(" did not complete the class.");
                break;
            default:
                outputText.append(" has a grade of ")
                        .append(student1.getGrade().toString());
                break;
        }
        out.println(outputText.toString());
    }

    @org.junit.Test
    public void test() {
        int current = LocalTime.now().toSecondOfDay() + 5;
        System.out.println("current = " + current);
        double b = 22.12 * 60 * 60;
        System.out.println("b = " + b);


        long a = new Date().getTime();
        System.out.println("a = " + a);

        current = LocalTime.parse("10:00").toSecondOfDay();
        System.out.println("a = " + current);
        int t = 10 * 60 * 60;
        System.out.println("t = " + t);


    }

    private static int getIntervalIndex(String... args) {
        java.util.Objects.requireNonNull(args);
        if (args.length != 2) {
            throw new IllegalArgumentException("invalid length of time interval args");
        }

        // 获取当前时间在一天之中的秒数，比如每天 16:00 对应的秒数是：57600
        int current = LocalTime.now().toSecondOfDay() + 5;

        /*  比如传入时间是 { "10:00", "16:00" }，那么返回
            - I_BEFORE  表示当前时间在"10:00"之前
            - I_BETWEEN 表示当前时间在"10:00"和"16:00"之间（包括"10:00"、不包括"16:00"）
            - I_AFTER   表示当前时间在"16:00"之后（包括"16:00"）                       */
//		if (current < LocalTime.parse(args[0]).toSecondOfDay()) {
//			return I_BEFORE;
//		} else if (current < LocalTime.parse(args[1]).toSecondOfDay()) {
//			return I_BETWEEN;
//		} else {
//			return I_AFTER;
//		}
        return 1;
    }
}
