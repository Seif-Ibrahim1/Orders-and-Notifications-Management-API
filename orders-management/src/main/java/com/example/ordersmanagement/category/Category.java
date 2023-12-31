package com.example.ordersmanagement.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.ordersmanagement.product.Product;

import java.util.*;
public class Category {

    private int id;

    private String name;
    @JsonIgnore
    public List<Product> products=new ArrayList<>();

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
