package com.jiamingku.j2se.refelct.bo;

public class Peo {
    private String tt;
    public int name1;

    public static void main(String[] args) {
        testGetClassName();
    }

    public void t() {
        System.out.println("执行" + this.getClass().getSimpleName() + ":" + new Exception().getStackTrace()[0].getMethodName());
    }

    /**
     * https://www.cnblogs.com/jingle1267/archive/2012/12/04/2801262.html
     * <p>
     * 方法1：219ms  10万次
     * 方法2：953ms
     * 方法3：31ms
     * <p>
     * 1）方法1不知有没有什么使用限制？
     * 2）方法2通过异常机制获取调用栈，性能最差，但能提供其它方法所不具有的功能，还可以获取方法名，行号等等；但这么使用多少有点不太常规；
     * 3）方法3只是简单分析了一下匿名类的名称，显然要简单多，事实上性能也是最高的；
     * <p>
     * https://www.cnblogs.com/jingle1267/archive/2012/12/04/2801262.html
     */
    public static void testGetClassName() {
        // 方法1：通过SecurityManager的保护方法getClassContext()
        String clazzName = new SecurityManager() {
            public String getClassName() {
                return getClassContext()[1].getName();
            }
        }.getClassName();
        System.out.println(clazzName);
        // 方法2：通过Throwable的方法getStackTrace()
        String clazzName2 = new Throwable().getStackTrace()[0].getClassName();
        System.out.println(clazzName2);
        // 方法3：通过分析匿名类名称()
        String clazzName3 = new Object() {
            public String getClassName1() {
                String clazzName = this.getClass().getName();
                return clazzName.substring(0, clazzName.lastIndexOf('$'));
            }
        }.getClassName1();
        System.out.println(clazzName3);
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Peo peo = (Peo) o;
//
//        if (name1 != peo.name1) return false;
//        return tt != null ? tt.equals(peo.tt) : peo.tt == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = tt != null ? tt.hashCode() : 0;
//        result = 31 * result + name1;
//        return result;
//    }


    public boolean equals(Object o) {

        if (o != null && o.getClass() == this.getClass()) {
            Peo p = (Peo) o;
            if (p.tt.equals(this.tt)) {
                return true;
            }
        }

        return false;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Peo peo = (Peo) o;
//        return name1 == peo.name1 && Objects.equal(tt, peo.tt);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(tt, name1);
//    }


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Peo peo = (Peo) o;
//        return name1 == peo.name1 && Objects.equals(tt, peo.tt);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(tt, name1);
//    }
}



