package com.jiamingku.j2se.accesscontroller.sonpackage;

import com.jiamingku.j2se.accesscontroller.methodtest.B;
import com.jiamingku.j2se.accesscontroller.methodtest.son.Bson;

/**
 * 接口回掉的例子
 */
public class TestXiuShiFuMethod {
    public static void main(String[] args) {
        B bson = new Bson();

        // 这句话相当于已经进入了bson的内部了。所以这里面调用protected方法是可以的
        bson.a();
        Bson bson1 = new Bson();
        bson1.a();
    }

}
