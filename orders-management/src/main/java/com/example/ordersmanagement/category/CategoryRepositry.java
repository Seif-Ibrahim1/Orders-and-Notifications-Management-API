package com.example.ordersmanagement.category;

import com.example.ordersmanagement.product.Product;
import com.example.ordersmanagement.product.ProductRepositry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.ordersmanagement.category.Category;



public class CategoryRepositry {

    public static Map<Integer, Category> categories = new HashMap<>();


    static {
        Category electronicsCategory = new Category(1, "Electronics");
        Category clothingCategory = new Category(2, "Clothing");
        Category sportsCategory = new Category(3, "Sports");
        Category foodCategory = new Category(4, "Food");

        categories.put(electronicsCategory.getId(), electronicsCategory);
        categories.put(clothingCategory.getId(), clothingCategory);
        categories.put(sportsCategory.getId(), sportsCategory);
        categories.put(foodCategory.getId(), foodCategory);
    }
}
