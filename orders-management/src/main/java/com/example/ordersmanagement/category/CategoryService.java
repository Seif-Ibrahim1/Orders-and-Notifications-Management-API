package com.example.ordersmanagement.category;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CategoryService implements CategoryServiceInterface {

    private final CategoryRepositry categoryRepositry;

    public CategoryService(CategoryRepositry categoryRepositry) {
        this.categoryRepositry = categoryRepositry;
    }

    public Category[] getAllCategory() {
        try {
            Set<Integer> ids = categoryRepositry.getCategoryIds();
            Category[] c = new Category[ids.size()];
            int i=0;
            for(Integer id : ids){
                c[i] = categoryRepositry.getCategory(id);
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
            return categoryRepositry.getCategory(id);
        } catch (Exception e) {
            System.out.println("Exception in get category as" + e.getMessage());
        }
        return null;
    }
}
