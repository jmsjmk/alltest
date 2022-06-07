import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

public class P {

    public void test(String whatname) {

    }

    public static void main(String[] args) throws Exception {

        Method test = P.class.getMethod("test", String.class);

        Arrays.stream(test.getParameters()).map(Parameter::getName).forEach(System.out::println);

        for (Parameter parameter : test.getParameters()) {

            String name = parameter.getName();
            System.out.println( );
            System.out.println("name = " + name);
        }

    }
}
