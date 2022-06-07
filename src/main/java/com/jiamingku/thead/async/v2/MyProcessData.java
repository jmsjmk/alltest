package com.jiamingku.thead.async.v2;

public class MyProcessData implements ProcessData {
    public void process(Object data) {
    //你不管什么时候起初数据data被获取了.
    //你只要规定如果获取到数据了如何处理

        System.out.println(data.toString() + "处理完成...........");
    //insert into dataBase?
    }
}
  