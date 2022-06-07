package com.jiamingku.fastjson.test.serializeable;

import com.google.common.base.Objects;
import org.junit.Test;

/**
 * 序列化
 *
 * @link com.jiamingku.fastjson.test.serializeable.base.BaseSerializableTest
 * <p>
 * https://blog.csdn.net/u012965203/article/details/84875568
 */
class CloneBo {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    protected CloneBo clone() throws CloneNotSupportedException {
        return (CloneBo) super.clone();
    }
}

/**
 * 1.实现Cloneable（CloneNotSupportedException）
 * 2.复写clone方法
 */
class CloneBo1 implements Cloneable {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    protected CloneBo1 clone() throws CloneNotSupportedException {
        return (CloneBo1) super.clone();
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", name)
                .add("age", age)
                .toString();
    }
}

class Children implements Cloneable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Children clone() throws CloneNotSupportedException {
        return (Children) super.clone();
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", name)
                .toString();
    }
}

class CloneBo2 implements Cloneable {
    private String name;
    private Integer age;
    private Children children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Children getChildren() {
        return children;
    }

    public void setChildren(Children children) {
        this.children = children;
    }

    @Override
    protected CloneBo2 clone() throws CloneNotSupportedException {
        CloneBo2 c = (CloneBo2) super.clone();
        Children c1 = children.clone();
        c.setChildren(c1);
        return c;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", name)
                .add("age", age)
                .add("children", children)
                .toString();
    }
}

public class CloneTest {        // Object类的子类

    /**
     * 1.通过new. 反射 相对来说代码的冗余比较多，如果创建对象很多的话代码就重复太多
     * <p>
     * 2.object.clone解决这个问题
     * <p>
     * 3.必须实现clone接口才可以使用
     */
    @Test
    public void test1() {
        try {
            CloneBo c0 = new CloneBo();
            c0.setName("c0");
            c0.setAge(10);

            CloneBo c1 = new CloneBo();
            c1.setName("c1");
            c1.setAge(10);

            Class c = Class.forName("com.jiamingku.fastjson.test.serializeable.CloneBo");
            CloneBo c3 = (CloneBo) c.newInstance();

            c3.setName("c3");
            c3.setAge(10);

            System.out.println(c3 + " " + c3.hashCode() + " " + Integer.toHexString(c3.hashCode()));
            System.out.println(c0 + " " + c0.hashCode() + " " + Integer.toHexString(c0.hashCode()));
            System.out.println(c1 + " " + c1.hashCode() + " " + Integer.toHexString(c1.hashCode()));


            try {
                // ---必须实现clone接口
                CloneBo cloneBo1 = new CloneBo();
                Object d = null;
                Object c2 = cloneBo1.clone();
                System.out.println("c2 = " + c2);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 克隆,字符串, Integer这样的引用类型完都可以clone
     * 虽然属于引用类型,但是可以copy
     */
    @Test
    public void test2() {
        try {
            CloneBo1 c0 = new CloneBo1();
            c0.setName("c0");
            c0.setAge(10);
            System.out.println(c0 + " " + c0.hashCode());
            CloneBo1 c1 = c0.clone();
            System.out.println(c1 + " " + c1.hashCode());
            c1.setName("c0000000000000000000000000");
            System.out.println("c1 = " + c1);
            System.out.println("c0 = " + c0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 潜复制：成员变量是引用类型
     * <p>
     * 1.要使用深复制的话，需要挺麻烦的：并且引用类型也的实现conlable接口
     * 如果属性非常多的话，代码太多。可以使用IO的一种方式
     *
     * @Override protected CloneBo2 clone() throws CloneNotSupportedException {
     * CloneBo2 c  =  (CloneBo2)super.clone();
     * Children c1 = children.clone();
     * c.setChildren(c1);
     * return c;
     * }
     * <p>
     * // -----------------采用一种io+序列化的来实现深层次的拷贝
     */
    @Test
    public void test3() {
        try {
            CloneBo2 c0 = new CloneBo2();
            c0.setName("c0");
            c0.setAge(10);
            Children c = new Children();
            c.setName("cname");
            c0.setChildren(c);
            // --------------------------

            System.out.println(c0 + " " + c0.hashCode());
            CloneBo2 c1 = c0.clone();
            System.out.println(c1 + " " + c1.hashCode());
            c1.getChildren().setName("c3");
            System.out.println(c0.getChildren().getName());
            System.out.println(c1.getChildren().getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test4() {
        int[] a = new int[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        int[] a1 = a.clone();
        a[9] = 1000;
        for (int i : a1) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : a) {
            System.out.print(i + " ");
        }
    }

    @Test
    public void test5() {

        Children ccc = new Children();
        ccc.setName("c1");
        try {
            Children c12 = ccc.clone();
            System.out.println(c12 + " " + c12.hashCode() + "   c12.getName()" + c12.getName());
            ccc.setName("dsfsdfsdfsdfsdf");

            System.out.println(ccc + " " + ccc.hashCode() + "   ccc.getName()" + ccc.getName());
            System.out.println("================== ");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


        Children[] children = new Children[10];
        for (int i = 0; i < children.length; i++) {
            Children c = new Children();
            c.setName(i + "");
            children[i] = c;
        }

        Children[] children1 = children.clone();
        children1[9].setName("333333333333333");
        for (Children c11 : children1) {
            System.out.println(c11 + " " + c11.hashCode());
        }
        System.out.println("----------------");
        for (Children c12 : children) {
            System.out.println(c12 + " " + c12.hashCode());
        }
    }
}