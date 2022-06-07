package com.jiamingku.j2se.innercalss.instance.sub;

import com.jiamingku.j2se.innercalss.instance.T1;

/**
 * Created by jiamingku on 2017/7/8.
 */
public class GetInstanceofSubPackage {
    public static void main(String[] args) {
        // 访问权限控制
        T1.Content inner = new T1().new Content();

        // 默认的,同包可见
        // T1.defaultClass inner1 = new T1().new defaultClass();

        //T1.protectedClass inner2 = new T1().new protectedClass();


        T1.publicStaticClass publicStaticClass = new T1.publicStaticClass();

        // 权限问题
//        T1.protectedStaticClass protectedClass = new T1.protectedStaticClass();
//        T1.defaultStaticClass defaultStaticClass = new T1.defaultStaticClass();
    }
}
