package com.jiamingku.jvm.runtime;

import java.util.ArrayList;
import java.util.List;

public class StudentTrace {
    static List<WebPage> webPages = new ArrayList<>();

    public static void createWebPages() {
        for (int i = 0; i < 100; i++) {
            WebPage webPage = new WebPage();
            webPage.setUrl("http://www." + Integer.toString(i) + ".com");
            webPage.setContent(Integer.toString(i));
            webPages.add(webPage);
        }
    }

    public static void main(String[] args) {
        createWebPages();
        Student student3 = new Student(3, "tom3");
        Student student5 = new Student(5, "tom5");
        Student student7 = new Student(7, "tom6");

        for (int i = 0; i < webPages.size(); i++) {
            if (i % student3.getId() == 0) {
                student3.visit(webPages.get(i));
            }

            if (i % student5.getId() == 0) {
                student5.visit(webPages.get(i));
            }

            if (i % student7.getId() == 0) {
                student7.visit(webPages.get(i));
            }
        }
        webPages.clear();
        System.gc();
    }
}

class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    List<WebPage> history = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WebPage> getHistory() {
        return history;
    }

    public void setHistory(List<WebPage> history) {
        this.history = history;
    }

    void visit(WebPage webPage) {
        if (webPage != null)  {
            history.add(webPage);
        }
    }
}

class WebPage {
    private String url;

    private String content;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
