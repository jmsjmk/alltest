package com.jiamingku.lambda.two;

import java.math.BigDecimal;

public class Item {

    private String name;
    private int qty;
    private BigDecimal price;

    //constructors, getter/setters


    public Item(String name,  int qty, BigDecimal price) {
        this.name = name;
        this.price = price;
        this.qty = qty;
    }



    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Item{");
        sb.append("name='").append(name).append('\'');
        sb.append(", qty=").append(qty);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}