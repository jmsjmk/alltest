package com.jiamingku.j2se.refelct.type;

import com.jiamingku.j2se.refelct.bo.ParameterizedTypeBean;
import sun.reflect.generics.reflectiveObjects.TypeVariableImpl;

import java.lang.reflect.*;

/**
 * 就是获取字段的泛型信息. 其实也非常简单，field获取泛型的类型
 * <p>
 * 1.如果是: ParameterizedType pType = (ParameterizedType) f.getGenericType();
 * <p>
 * 2. getRawType 获取原生的类型：就是<>前面的类型<p/>
 * <p>
 * 3. getActualTypeArguments 获取<>里面的内容.<p/>
 *
 * ------------------------------------------------
 * 字段的泛型类型是可以获取到的.
 */
public class ParameterizedTypeTest {

    public static void main(String[] args) {
        testParameterizedType();
    }

    public static void testParameterizedType() {
        Field f;
        try {
            Field[] fields = ParameterizedTypeBean.class.getDeclaredFields();
            // 打印出所有的 Field 的 TYpe 是否属于 ParameterizedType
            for (int i = 0; i < fields.length; i++) {
                f = fields[i];
                System.out.println("f.getType : " + f.getType().getSimpleName() + "\t\t\t f.getName():" +f.getName()
                        + "\t\t\t (f.getGenericType() instanceof ParameterizedType):" + (f.getGenericType() instanceof ParameterizedType)
                        + "\t\t\t 真正的实现类型::::" + f.getGenericType().getClass().getSimpleName());
            }
            System.out.println("");
            System.out.println("");
            System.out.println("");

            getParameterizedTypeMes("map");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            getParameterizedTypeMes("set1");
            System.out.println("");
            System.out.println("");
            System.out.println("");

            // ----这个重点关注下就行.
            getParameterizedTypeMes("holder");
            System.out.println("");
            System.out.println("");
            System.out.println("");

            getParameterizedTypeMes("entry");


            System.out.println("");
            System.out.println("");
            System.out.println("");
            getParameterizedTypeMes("aList");


            System.out.println("");
            System.out.println("");
            System.out.println("");
            getParameterizedTypeMes("map1");


        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void getParameterizedTypeMes(String fieldName) throws NoSuchFieldException {
        System.out.println("反射获取字段:fieldName ======= " + fieldName + "==========================================");
        Field f = ParameterizedTypeBean.class.getDeclaredField(fieldName);
        f.setAccessible(true);
        PrintUtils.print("f.getGenericType():" + f.getGenericType());
        PrintUtils.print("f.getType():" + f.getType());
        PrintUtils.print("f.getDeclaringClass()" + f.getDeclaringClass());
        System.out.println("字段的泛型信息是可以获取到的.");
        boolean b = f.getGenericType() instanceof ParameterizedType;
        PrintUtils.print("\t[Is ParameterizedType?]\t\t" + b);
        if (b) {
            ParameterizedType pType = (ParameterizedType) f.getGenericType();
            PrintUtils.print("\tRawType:\t\t" + pType.getRawType());
            for (Type type : pType.getActualTypeArguments()) {
                PrintUtils.print("\t" +type + "\t\t\t\t\t\t\t\t\t\t\t\ttype.class:" + type.getClass().getSimpleName());


            }
            /*
            比如 Map<String,Person> map 这个 ParameterizedType 的 getOwnerType() 为 null，
            而 Map.Entry<String, String>entry 的 getOwnerType() 为 Map 所属于的 Type。
             */
            PrintUtils.print("\t"+pType.getOwnerType()); // null
            System.out.println("---");
        }
    }
}