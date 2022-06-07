package com.jiamingku;

class TestCharset {
    public static void main(String[] args) {
        new TestCharset().execute();
    }

    private void execute() {
        String s = "你好";
        byte[] bytes = s.getBytes();
        System.out.println("bytes lenght is:" + bytes.length);
    }
}