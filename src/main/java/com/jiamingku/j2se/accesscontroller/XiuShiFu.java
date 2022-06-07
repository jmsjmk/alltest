package com.jiamingku.j2se.accesscontroller;

public class XiuShiFu {
    public String public_value = "public_value";
    protected String protected_value = "protected_value";
    String no_xiushifu = "no_xiushifu";
    private String private_value = "private_value";

    /**
     * 自己类完成访问
     */
    public static void main(String[] args) {
        XiuShiFu x = new XiuShiFu();
        System.out.println(x.no_xiushifu);
        System.out.println(x.protected_value);
        System.out.println(x.public_value);
        System.out.println(x.private_value);

        x.public_method();
        x.no_method();
        x.protected_method();
        x.private_method();
    }

    public void public_method() {
        System.out.println("public_method method excute...");
    }

    void no_method() {
        System.out.println("no method excute...");
    }

    private void private_method() {
        System.out.println("public_method method excute...");
    }

    protected void protected_method() {
        System.out.println("protected_method method excute...");
    }
}