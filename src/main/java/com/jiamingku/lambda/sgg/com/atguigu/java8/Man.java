package com.jiamingku.lambda.sgg.com.atguigu.java8;

public class Man {

    private Godness god;

    public Man() {
    }

    public Man(Godness god) {
        this.god = god;
    }

    public Godness getGod() {
        return god;
    }

    public void setGod(Godness god) {
        this.god = god;
    }

    @Override
    public String toString() {
        return "Man [god=" + god + "]";
    }

    public static void main(String[] args)  throws Exception {
        try {
            int[] aa = new int[100];
            int a = aa[-1];
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("======");
            System.out.println(e.getMessage());
        }
    }
}
