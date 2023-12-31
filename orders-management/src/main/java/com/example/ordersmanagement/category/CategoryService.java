package com.example.ordersmanagement.category;


import com.example.ordersmanagement.product.Product;
import com.example.ordersmanagement.product.ProductRepositry;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CategoryService implements CategoryServiceInterface {
    public Category[] getAllCategory() {
        try {
            Set<Integer> ids = CategoryRepositry.categories.keySet();
            Category[] c = new Category[ids.size()];
            int i=0;
            for(Integer id : ids){
                c[i] = CategoryRepositry.categories.get(id);
                i++;
            }
            return c;
        } catch (Exception e) {
            System.out.println("Exception in getAllCategories as" + e.getMessage());
        }
        return null;
    }

    public Category getCategory(int id) {
        try {
            return CategoryRepositry.categories.get(id);
        } catch (Exception e) {
            System.out.println("Exception in get category as" + e.getMessage());
        }
        return null;
    }
}
