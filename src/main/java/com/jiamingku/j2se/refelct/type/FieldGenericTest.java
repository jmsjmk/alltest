package com.jiamingku.j2se.refelct.type;

import com.jiamingku.j2se.refelct.bo.ParameterizedTypeBean;

import java.lang.reflect.*;

/**
 * 就是获取字段的泛型信息., 其实也非常简单，field获取泛型的类型
 * <p>
 * 如果是: ParameterizedType pType = (ParameterizedType) f.getGenericType();
 * 1.获取原生的类型：就是<>前面的类型<p/>
 * <p>
 * 2. getRawType <p/>
 * <p>
 * 3. getActualTypeArguments 获取<>里面的内容.<p/>
 */
public class FieldGenericTest {

    public static void main(String[] args) {
        testParameterizedType();
    }

    public static void testParameterizedType() {
        Field f;
        try {
//            Field[] fields = ParameterizedTypeBean.class.getDeclaredFields();
//            // 打印出所有的 Field 的 TYpe 是否属于 ParameterizedType
//            for (int i = 0; i < fields.length; i++) {
//                f = fields[i];
//                System.out.println(" ====== " + f.getType());
//                System.out.println(f.getName()
//                        + " (getGenericType() instanceof ParameterizedType)       "
//                        + (f.getGenericType() instanceof ParameterizedType)
//                        + " 真正的实现类::::" + f.getGenericType().getClass().getSimpleName());
//            }
//            System.out.println(" ===== ");
//            System.out.println(" ===== ");
//            System.out.println(" ===== ");
//            getParameterizedTypeMes("map");
            System.out.println(" ===== ");
            System.out.println(" ===== ");
            System.out.println(" ===== ");
            getParameterizedTypeMes("entry");


            System.out.println(" ===== ");
            System.out.println(" ===== ");
            System.out.println(" ===== ");
            getParameterizedTypeMes("aList");


            System.out.println(" ===== ");
            System.out.println(" ===== ");
            System.out.println(" ===== ");
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
        System.out.println("fieldName ======= " + fieldName + "==========================================");
        Field f = ParameterizedTypeBean.class.getDeclaredField(fieldName);
        f.setAccessible(true);
        PrintUtils.print("f.getGenericType():" + f.getGenericType());
        PrintUtils.print("f.getType()" + f.getType());
        PrintUtils.print("f.getDeclaringClass()" + f.getDeclaringClass());
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        boolean b = f.getGenericType() instanceof ParameterizedType;
        PrintUtils.print(b);
        if (b) {
            ParameterizedType pType = (ParameterizedType) f.getGenericType();
            PrintUtils.print(pType.getRawType());
            for (Type type : pType.getActualTypeArguments()) {
                PrintUtils.print(type + "  type.class:" + type.getClass().getSimpleName());
            }
            /*
            比如 Map<String,Person> map 这个 ParameterizedType 的 getOwnerType() 为 null，
            而 Map.Entry<String, String>entry 的 getOwnerType() 为 Map 所属于的 Type。
             */
            PrintUtils.print(pType.getOwnerType()); // null
            System.out.println("---");
        }
    }
}