package com.example.ordersmanagement.order;

public class Product {
    private double price = 0.0;
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{"
                + "\"price\":" + price
                + "}";
    }
}
