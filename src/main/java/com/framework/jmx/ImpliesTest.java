package com.framework.jmx;

import java.security.BasicPermission;
import java.security.PermissionCollection;

public class ImpliesTest {
    public ImpliesTest() {
    }

    public static void testImplies() {
        MyPermission usaBp = new MyPermission("usa.*"); //全美国
        MyPermission chinaBp = new MyPermission("china.*"); //全中国
        MyPermission hubeiBp = new MyPermission("china.hubei.*"); //全湖北省
        MyPermission wuhanBp = new MyPermission("china.hubei.wuhan.*"); //全武汉市
        MyPermission wuchangBp = new MyPermission("china.hubei.wuhan.wuchang.*"); //全武昌区

        System.out.println(chinaBp.implies(usaBp)); //false全美国并不暗含全中国
        System.out.println(hubeiBp.implies(wuchangBp)); //true全湖北暗含了全武昌
        System.out.println(hubeiBp.implies(chinaBp)); //false全湖北并不暗含全中国

        // Java 对于权限还给出一个权限集合类PermissionCollection，它是一组权限的并集。
        // 对任意给定的Permission进行测试权限，只要被这个集合中的任意一个Permission 暗含即可。
        // 需要注意的是，该集合中只能是同种类型的Permission。
        PermissionCollection bpc = usaBp.newPermissionCollection();
        bpc.add(chinaBp);
        System.out.println(bpc.implies(hubeiBp)); //true(全美国 | 全中国)暗含了全湖北
    }

    public static void main(String[] args) {
        testImplies();
    }
}

class MyPermission extends BasicPermission {
    private static final long serialVersionUID = 1L;

    public MyPermission(String name) {
        super(name);
    }
}

//// 通过文件目录读权限测试implies方法
//Permission file = new FilePermission("/tmp/f", "read");
//Permission star= new FilePermission("/tmp/*", "read");
//boolean sif = star.implies(file) // 输出true
//boolean fis= file.implies(star)  // 输出false