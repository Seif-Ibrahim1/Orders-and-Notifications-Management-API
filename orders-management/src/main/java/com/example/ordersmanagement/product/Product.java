package com.example.ordersmanagement.product;

import com.example.ordersmanagement.category.Category;

public class Product {

    private int id;

    private String name;

    private Category category;

    private int serialNum;

    private String vendor;

    private double price;

    private int remainingNum;

    public Product(int id, String name, Category category, int serialNum, String vendor, double price, int remainingNum) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.serialNum = serialNum;
        this.vendor = vendor;
        this.price = price;
        this.remainingNum = remainingNum;
    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRemainingNum() {
        return remainingNum;
    }

    public void setRemainingNum(int remainingNum) {
        this.remainingNum = remainingNum;
    }
}
