package com.jiamingku.j2se.refelct;

import com.jiamingku.j2se.refelct.bo.Son;
import com.jiamingku.j2se.refelct.bo.Th;
import org.junit.Test;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * get系列:包含父类,本类的public + 父类 + 接口的(含静态方法)
 * <p>
 * getDeclared系列:仅本类所有的访问权限的元素(含静态方法)
 * <p>
 * 默认修饰符0，public =1，private=2，protected=4 ，static=8， final=16，如果public static = 1 + 8 =9
 * <p>
 * --------------------------------------------------------------------------------------------
 * 就拿反射来说对于方法来说都想获取到什么信息?好像就是下面的这么多东西
 * 1.方法的名字，访问修饰符，注解，
 * 2.参数，泛型参数，参数的类型，返回值
 * <p>
 * *  1> https://www.jianshu.com/p/7649f86614d3
 * *  2> https://www.jianshu.com/p/cae76008b36b
 */
public class MethodTest {
    /**
     * 构造器是也是方法---********但是区别对待这两个方法, 不会获取到构造器方法.
     * <p>
     * 访问修饰符用一个数字表示
     */
    @Test
    public void testGetMethod() {
        Class<Son> clazz = Son.class;
        // 所有的public方法--父类-直接到object方法都有
        Method[] methods = clazz.getMethods();
        p(methods, "clazz.getMethods()", 3);
        System.out.println("-------");
        System.out.println();
        System.out.println();
        for (Method method : methods) {
            p1(method);
        }
    }

    /**
     * 获取自定的所有方法. 包括私有方法.
     */
    @Test
    public void testGetDeclaredMethod() {
        Class<Son> clazz = Son.class;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        p(declaredMethods, "clazz.getDeclaredMethods()", 3);
    }


    // ------------------------------------------------------------------------------------------------------------

    /**
     * 获取方法的变量名字.
     * <p>
     * https://www.cnblogs.com/kancy/p/10205036.html
     * javac -parameters
     */
    @Test
    public void getMethodName() {
        try {
            Class<Son> sonClass = Son.class;
            Method setPublicString = sonClass.getMethod("setPublicString", String.class);
            Parameter[] parameters = setPublicString.getParameters();
            for (Parameter parameter : parameters) {
                String name = parameter.getName();
                System.out.println("name = " + name);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    /**
     * 参数的名字:
     * jdk8:https://www.cnblogs.com/kancy/p/10205036.html
     */
    @Test
    public void testGetMethodParameters() {
        Method[] methods = Son.class.getMethods();
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        for (Method m : methods) {
            if (m.getName().equals("getString")) {
                /**
                 * 直接通过反射是拿不到对应的名字的
                 */
                Parameter[] parameters = m.getParameters();
                for (Parameter p : parameters) {
                    System.out.println("type:" + p.getType() + "\t  name:" + p.getName());
                }
                /**
                 * 获取名字通过jdk8新增的特性来进行的(借住spring的包可以实现这个功能)
                 */
                String[] params = u.getParameterNames(m);
                List<String> strings = Arrays.asList(params);
                System.out.println(strings);
            }
        }
    }

    // ---------------------------------------------------------------------------------------------------------------------------

    /**
     * 获取所有的注解.
     */
    @Test
    public void testGetMethodAnnotation() {
        Class<Son> clazz = Son.class;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods) {
            if (Objects.equals(m.getName(), "setIds")) {
                Annotation[] annotations = m.getAnnotations();
                for (Annotation a : annotations) {
                    System.out.println(a);
                }
            }
        }
    }

    /**
     * 参数的注解
     */
    @Test
    @SuppressWarnings("ALL")
    public void testGetMethodParametersAnnotation() {
        Method[] methods = Son.class.getMethods();
        for (Method m : methods) {
            if (m.getName().equals("setIds")) {
                Parameter[] parameters = m.getParameters();
                for (Parameter p : parameters) {
                    System.out.println(p.getType() + "\t\t\t\t\t\t\t" + p.getParameterizedType());
                    // ----------------参数的注解信息----------------------
                    Annotation[] annotations = p.getAnnotations();
                    for (Annotation a : annotations) {
                        System.out.println("\ta.getClass() = " + a.getClass() + "------");
                        System.out.println("\ta.annotationType() = " + a.annotationType() + "------");
                        System.out.println("\ta.toString() = " + a.toString());
                        if (a instanceof Th) {
                            Th ta = (Th) a;
                            System.out.println(ta.toString() + "\t获取a的值:" + ((Th) a).a());
                            System.out.println("===");
                        }
                    }
                }
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 1.方法级别可以获取到泛型参数,参数parameter是获取不到的
     * 2.
     */
    @Test
    @SuppressWarnings("ALL")
    public void testGetMethodParametersAll() {
        Method[] methods = Son.class.getMethods();
        for (Method m : methods) {
            if (m.getName().equals("setIds")) {


                Class[] classes = m.getParameterTypes();
                for (Class c : classes) {
                    System.out.println(c.getSimpleName());
                }

                Type[] genericParameterTypes = m.getGenericParameterTypes();
                for (Type t: genericParameterTypes) {
                    System.out.println(t + "=======" + t.getTypeName() + "    " + t.getClass().getSimpleName());
                }

                System.out.println();
                System.out.println();
                System.out.println();
                Parameter[] parameters = m.getParameters();
                for (Parameter p: parameters) {
                    System.out.println(p + "==========" + p.getName());
                    Class<?> type = p.getType();
                    System.out.println("type = " + type);
                    AnnotatedType annotatedType = p.getAnnotatedType();
                    System.out.println("annotatedType = " + annotatedType.getType());
                    System.out.println("annotatedType = " + annotatedType.getType().getClass().getSimpleName());
                }


            }
        }
    }


    public void p(Method[] methods, String tip, int count) {
        System.out.println(tip + "方法准备执行.........,获取方法长度：" + methods.length);
        for (Method m : methods) {
            System.out.println("\t方法名:" + m.getName()
                    + "   ,[访问修饰符]:"
                    + m.getModifiers()
                    + "  ,[返回值类型]：" + m.getReturnType().getName());
        }

        for (int i = 0; i < count; i++)
            System.out.println("");
    }

    public void p1(Method method) {
        StringBuilder sb = new StringBuilder();
        // 返回类型
        Class<?> returnType = method.getReturnType();
        if (returnType != null) {
            sb.append(returnType.getName()).append('#');
        }
        // 方法名
        sb.append(method.getName());
        // 方法参数
        Class<?>[] parameters = method.getParameterTypes();
        for (int i = 0; i < parameters.length; i++) {
            if (i == 0) {
                sb.append(':');
            } else {
                sb.append(',');
            }
            sb.append(parameters[i].getName());
        }
        String s = sb.toString();
        System.out.println(s);
    }

    // ---------------------------------------------------------------------------------------------------------------------
    // ---------------------------------Use---------------------------------------------------------------------------------
    // ---------------------------------Use---------------------------------------------------------------------------------
    // ---------------------------------Use---------------------------------------------------------------------------------
    @Test
    public void testExcuteMethod() {
        try {
            Son s = Son.class.newInstance();
            Method method = Son.class.getMethod("getMethod", String.class);
            Object r = method.invoke(s, "hhhh");
            System.out.println("r = " + r);


            Method method1 = Son.class.getMethod("publicMethod");
            Object r1 = method1.invoke(s);


            System.out.println("r = " + r1);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 确定方法是那个类定义的
     */
    @Test
    public void testGetMethodParameters2222() {
        Method[] methods = Son.class.getMethods();
        for (Method m : methods) {
            Object o = m.getDeclaringClass();
            System.out.println(o.toString());
        }
    }

    /**
     * 获取声明的方法
     */
    @Test
    public void testGetMethodParameters22232() {
        try {
            Method[] method = Son.class.getDeclaredMethods();
            for (Method m : method) {
                if (Objects.equals(m.getName(), "setIdss")) {
                    Parameter[] parameters = m.getParameters();

                    for (Parameter p : parameters) {
                        System.out.println(p.getType());
                        System.out.println(p.getParameterizedType());
                    }
                }
            }
            // --- 获取应该获取的方法
            Object o = Son.class.getDeclaredMethod("setIdss", null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    // ------------------------------------------------------------------------------------------------------------


    @Test
    public void testGetMethodReturnWWWWW() {
        try {
            Method[] method = Son.class.getDeclaredMethods();
            for (Method m : method) {
                Type returnType = m.getGenericReturnType();
                System.out.println("returnType = " + returnType + " name:  " + m.getName());
                Class<?> declaringClass = m.getDeclaringClass();
                System.out.println("declaringClass = " + declaringClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}