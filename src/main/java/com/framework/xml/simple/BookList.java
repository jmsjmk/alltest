package com.framework.xml.simple;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
@Root(name = "perf-monitor")
public class BookList {
    @ElementList(inline=true)
    private ArrayList<Book> list;

    public BookList() {
        list = new ArrayList<Book>();
    }
    public ArrayList<Book> getList() {
        return list;
    }

    public void add(Book b){
        list.add(b);
    }
    @Override
    public String toString() {
        return "BookList [list=" + list + "]";
    }

}
