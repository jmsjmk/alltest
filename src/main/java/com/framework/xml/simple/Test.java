package com.framework.xml.simple;

import org.simpleframework.xml.core.Persister;

import java.io.File;

public class Test {
    public static void write(Persister persister) {
        Book bo = new Book("XX021", "复活", 250);
        bo.setAuthor(new Author("adf", "15777"));

        Book bo1 = new Book("XX0212", "死亡之巅", 110);
        bo1.setAuthor(new Author("kk", "189"));

        Book bo2 = new Book("XX0213", "JVM解析", 258);
        bo2.setAuthor(new Author("pou", "142"));

        BookList booklist = new BookList();
        booklist.add(bo);
        booklist.add(bo1);
        booklist.add(bo2);

        try {
            persister.write(booklist, new File("/Users/jiamingku/Desktop/b.xml"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void read(Persister persister) {
        try {
            //读取File("b.xml")，解析成BookList.class类型
            BookList blist = persister.read(BookList.class, new File("/Users/jiamingku/Desktop/b.xml"));
            System.out.println(blist.getList());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        //Persister序列化、持久化---simple-xml-2.7.1.jar里提供的[是其他人已经写好分装好的]
        Persister persister = new Persister();//实例化一个 Persister 对象
        write(persister);    //写
//        read(persister);   //读
    }


}
