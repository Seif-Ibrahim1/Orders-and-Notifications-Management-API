package com.example.ordersmanagement.product;

import com.example.ordersmanagement.product.Product;
import com.example.ordersmanagement.category.CategoryRepositry;
import com.example.ordersmanagement.category.Category;

import java.util.HashMap;
import java.util.Map;

public class ProductRepositry {

    public static Map<Integer, Product> products = new HashMap<>();

    static {
        // Adding sports products
        for (int i = 1; i <= 5; i++) {
            Product sportsProduct = new Product(i, "Sports Product " + i, CategoryRepositry.categories.get(3), 1000 + i, "Vendor " + i, 19.99 * i, 50);
            products.put(sportsProduct.getId(), sportsProduct);

            // Linking products to categories
            CategoryRepositry.categories.get(3).getProducts().add(sportsProduct);
        }

        // Adding food products
        for (int i = 6; i <= 10; i++) {
            Product foodProduct = new Product(i, "Food Product " + (i - 5), CategoryRepositry.categories.get(4), 2000 + i, "Vendor " + i, 9.99 * (i - 5), 30);

            products.put(foodProduct.getId(), foodProduct);

            // Linking products to categories
            CategoryRepositry.categories.get(4).getProducts().add(foodProduct);
        }
    }
}
