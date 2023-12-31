package com.example.ordersmanagement.category;

import com.example.ordersmanagement.product.Product;

public interface CategoryServiceInterface {

    public Category[] getAllCategory();

    public Category getCategory(int id);
}
