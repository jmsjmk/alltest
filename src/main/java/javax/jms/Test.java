package javax.jms;

import com.google.common.base.Objects;

/**
 * java.包开头的不行，
 * javax是可行的，就像使用 jms ：https://blog.csdn.net/u014001866/article/details/50918876
 * jms是java的一个规范，但是没有像jdbc,那样放在rt.jar中.
 */
public class Test {
    private String name;
    private Integer age;
    Object O;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equal(name, test.name) && Objects.equal(age, test.age);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hashCode(name, age);
//    }

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

    public static void main(String[] args) {

        int CAPACITY = (1 << 29) - 1;
        System.out.println(Integer.toBinaryString(CAPACITY));
        System.out.println(Integer.toBinaryString(~CAPACITY));

        System.out.println("d");
        Test t = new Test();
        // 2058534881
        System.out.println(t.hashCode());
        System.out.println(System.identityHashCode(t));
        int a = Integer.SIZE;
        System.out.println("a = " + a);

        int a1 = 12;
        int a2 = 3;
        int a3 = a1 | a2;
        System.out.println(a3);
    }
}
