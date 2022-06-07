package com.framework.xml.simple;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class Book {

    @Attribute(name="id",required=false)
    private String isbn;    //书号isbn
    @Element(required=false)
    private String title;     //书名
    @Element(required=false)
    private int price;        //书的价格
    @Element(required=false)
    private Author author;     //书的作者--也是个实体类

    public Book() {
    }
    public Book(String isbn, String title, int price) {
        super();
        this.isbn = isbn;
        this.title = title;
        this.price = price;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    @Override
    public String toString() {
        return "Book [author=" + author + ", isbn=" + isbn + ", price=" + price
                + ", title=" + title + "]";
    }
}

